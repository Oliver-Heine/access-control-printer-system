package com.dtu.printerservice.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dtu.printerservice.authorization.Authorization;
import com.dtu.printerservice.authorization.AuthorizationImpl;
import com.dtu.printerservice.authorization.Role;
import com.dtu.printerservice.client.client;
import com.dtu.printerservice.users.User;
import com.dtu.printerservice.users.UserDTO;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AuthenticationImpl implements Authentication {
    private static AuthenticationImpl authenticationSingleton = null;

    private final int EXPIRATION_TIME_MILLIS = 600000;
    private final String secretKey = "a-very-secrete-key";
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    private Authorization authorization = new AuthorizationImpl();

    public static AuthenticationImpl getInstance() {
        if (authenticationSingleton == null) {
            authenticationSingleton = new AuthenticationImpl();
        }
        return authenticationSingleton;
    }

    private AuthenticationImpl() {
        this.algorithm = Algorithm.HMAC256(this.secretKey); // Use a secure key for signing tokens
        this.verifier = JWT.require(algorithm).build();
    }

    public DecodedJWT AuthenticateUser(Role role, String token, String action) {
        //TODO Maybe extend this to a more complex flow. Maybe call the authorization class (not implemented)
        DecodedJWT decodedJWT = validateToken(token);
        if(decodedJWT == null){
            throw new RuntimeException("Invalid token");
        }
        if (!authorization.authorize(role, action)) {
            throw new RuntimeException("User not authorized");
        }

        return decodedJWT;
    }

    public String login(String username, String password) {
        loadPasswords();
        User user = validateCredentials(username, password);
        if (user != null) {
            System.out.println("User logged in");
            //TODO: Something where we can get and validate the login of the user. Perhaps fetch or create the user object somehow.
            String token = JWTTokenIssuer(user);
            return token; //TODO Potentially update it with the result of the above TODO
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public void newUser(String username, String password) {
        createUser(username, password);
    }

    private User validateCredentials(String username, String password) {
        return validatePassword(username, password);
    }

    private String JWTTokenIssuer(User user){
        return JWT.create()
                .withIssuer("printServer")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
                .withClaim("UserName", user.getName())
                .withClaim("Role", user.getRole())
                .sign(this.algorithm);
    }

    public String getRole(String token) {
        DecodedJWT decodedJWT = validateToken(token);
        if (decodedJWT == null) {
            throw new RuntimeException("Invalid token");
        }
        return decodedJWT.getClaim("Role").asString();
    }

    public String getUserName(String token) {
        DecodedJWT decodedJWT = validateToken(token);
        if (decodedJWT == null) {
            throw new RuntimeException("Invalid token");
        }
        return decodedJWT.getClaim("UserName").asString();
    }

    private DecodedJWT validateToken(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            if (e instanceof TokenExpiredException){
                System.out.println("Token is expired");
                client.userLogin();
            } else {
                System.out.println("Invalid token: " + e.getMessage());
            }
            return null; // Make it call a re-login if the token has expired
        }
    }

    private UserDTO encrypt(String password, byte[] salt) {
        SecureRandom random = new SecureRandom();
        if (salt == null) {
            salt = new byte[16];
            random.nextBytes(salt);
        }


        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Encryption failed with: " + e);
        }

        if (hash == null) {
            System.out.println("Password could not be hashed");
            return null;
        }
        UserDTO userDTO = new UserDTO();

        userDTO.setSalt(salt);
        userDTO.setPassword(hash);

        return userDTO;

    }

    private void createUser(String username, String password) {

        UserDTO userDTO = encrypt(password, null);

        userDTO.setRole("User");
        userDTO.setName(username);

        Map<String, UserDTO> passwords = loadPasswords();

        passwords.put(username, userDTO);

        savePasswords(passwords);
    }

    private Map<String, UserDTO> loadPasswords() {
        Map<String, UserDTO> users = new HashMap<>();
        try {
            String content = new String(this.getClass().getResourceAsStream("/passwords.txt").readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            for (String username : json.keySet()) {
                UserDTO user = new UserDTO();

                JSONObject userData = json.getJSONObject(username);
                ObjectMapper objectMapper = new ObjectMapper();
                user.setPasswordBase64(userData.getString("password"));
                user.setName(username);
                user.setRole(userData.getString("role"));
                user.setSaltBase64(userData.getString("salt"));
                //user.setSalt(objectMapper.writeValueAsBytes(userData.getJSONArray("salt")));

                users.put(username, user);
            }
        } catch (IOException | JSONException e) {
            System.out.println("File not found: " + e);
        }
        return users;
    }

    private User validatePassword(String username, String password) {
        Map<String, UserDTO> users = loadPasswords();
        UserDTO userForLogin = users.get(username);
        if (userForLogin == null) {
            return null;
        }
        UserDTO userPasswordEncrypted = encrypt(password, userForLogin.getSalt());

        if(userPasswordEncrypted == null) System.out.println("Encrypted password is null");

        if (Arrays.equals(userForLogin.getPassword(), userPasswordEncrypted.getPassword())) {
            return new User(username, userForLogin.getRole());
        } else return null;
    }

    private void savePasswords(Map<String, UserDTO> passwords) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, UserDTO> entry : passwords.entrySet()) {
            JSONObject userJson = new JSONObject();
            userJson.put("password", entry.getValue().getPasswordBase64());
            userJson.put("salt", entry.getValue().getSaltBase64());
            userJson.put("role", entry.getValue().getRole());
            userJson.put("name", entry.getValue().getName());
            json.put(entry.getKey(), userJson);
        }
        try (Writer writer = new FileWriter(getClass().getClassLoader().getResource("passwords.txt").getFile())) {
            writer.write(json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










}
