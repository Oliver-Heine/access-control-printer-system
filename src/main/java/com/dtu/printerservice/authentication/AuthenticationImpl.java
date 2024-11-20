package com.dtu.printerservice.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dtu.printerservice.exceptions.InvalidTokenException;
import com.dtu.printerservice.exceptions.UnauthenticatedException;
import com.dtu.printerservice.exceptions.UnauthorizedException;
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

    private final int EXPIRATION_TIME_MILLIS = 300000;
    private final String secretKey = "a-very-secrete-key";
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

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

    @Override
    public void AuthenticateUser(String token) {
        validateToken(token);
    }

    @Override
    public String login(String username, String password) {
        User user = validateCredentials(username, password);
        if (user != null) {
            System.out.println("User logged in");
            return JWTTokenIssuer(user);
        } else {
            throw new InvalidTokenException("Invalid username or password");
        }
    }

    @Override
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
                .sign(this.algorithm);
    }

    @Override
    public String getUserName(String token) {
        DecodedJWT decodedJWT = validateToken(token);
        return decodedJWT.getClaim("UserName").asString();
    }

    private DecodedJWT validateToken(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            if (e instanceof TokenExpiredException){
                throw new UnauthenticatedException("Token is expired");
            } else {
                throw new InvalidTokenException("Invalid token");
            }
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

        assert userDTO != null;
        userDTO.setRole("User");
        userDTO.setName(username);

        Map<String, UserDTO> passwords = loadPasswords();
        passwords.put(username, userDTO);
        savePasswords(passwords);
    }

    private Map<String, UserDTO> loadPasswords() {
        Map<String, UserDTO> users = new HashMap<>();
        try {
            String content = new String(Objects.requireNonNull(this.getClass().getResourceAsStream("/passwords.txt")).readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            for (String username : json.keySet()) {
                UserDTO user = new UserDTO();

                JSONObject userData = json.getJSONObject(username);
                user.setPasswordBase64(userData.getString("password"));
                user.setName(username);
                user.setSaltBase64(userData.getString("salt"));

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

        assert userPasswordEncrypted != null;
        if (Arrays.equals(userForLogin.getPassword(), userPasswordEncrypted.getPassword())) {
            return new User(username);
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
        try (Writer writer = new FileWriter(Objects.requireNonNull(getClass().getClassLoader().getResource("passwords.txt")).getFile())) {
            writer.write(json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
