package com.dtu.printerservice.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;


public interface Authorization {

    Boolean authorize(Role role, DecodedJWT Token);
}
