package com.dtu.printerservice.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;


public class AuthorizationImpl implements Authorization{
    @Override
    public Boolean authorize(Role role, DecodedJWT token) {

        int claim = token.getClaim("Role").asInt();

        return claim >= role.getRole();
    }
}
