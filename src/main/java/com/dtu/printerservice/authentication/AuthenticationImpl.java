package com.dtu.printerservice.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dtu.printerservice.users.User;

import java.util.Date;

public class AuthenticationImpl implements Authentication {
    private final int EXPIRATION_TIME_MILLIS = 60000;
    private final String secretKey = "a-very-secrete-key";
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public AuthenticationImpl() {
        this.algorithm = Algorithm.HMAC256(this.secretKey); // Use a secure key for signing tokens
        this.verifier = JWT.require(algorithm).build();
    }

    public DecodedJWT AuthenticateUser(User user) {
        //TODO Maybe extend this to a more complex flow. Maybe call the authorization class (not implemented)
        return validateToken(user.getToken());
    }

    public String login(String username, String password) {
        if (validateCredentials(username, password)) {
            //TODO: Something where we can get and validate the login of the user. Perhaps fetch or create the user object somehow.
            return JWTTokenIssuer(null); //TODO Potentially update it with the result of the above TODO
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    private boolean validateCredentials(String username, String password) {
        return true; //update to actually validate username and password
    }

    private String JWTTokenIssuer(User user){
        return JWT.create()
                .withIssuer("printServer")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
                .withClaim("UserName", user.getName())
                .withClaim("Role", user.getRole())
                .sign(this.algorithm);
    }

    private DecodedJWT validateToken(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            if (e instanceof TokenExpiredException){
                System.err.println("Token is expired");
            } else {
                System.err.println("Invalid token: " + e.getMessage());
            }
            return null; // Make it call a re-login if the token has expired
        }
    }
}
