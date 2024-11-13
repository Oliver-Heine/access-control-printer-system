package com.dtu.printerservice.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;


public class AuthorizationImpl implements Authorization{
    @Override
    public Boolean authorize(Role role, DecodedJWT token) {

        String roleClaim = token.getClaim("Role").asString();

        return roleClaim.equals(role.getRole());
    }
}
