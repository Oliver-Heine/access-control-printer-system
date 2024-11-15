package com.dtu.printerservice.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dtu.printerservice.authentication.Authentication;
import com.dtu.printerservice.authentication.AuthenticationImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;


public class AuthorizationImpl implements Authorization{

    public Boolean authorize(String username, String action) {
        String[] permissions = getUserPermissions(username);
        return Arrays.asList(permissions).contains(action);
    }

    public String[] getUserPermissions (String username) {
        String[] permissions = new String[]{};
        try {
            String content = new String(this.getClass().getResourceAsStream("/accessControls.json").readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);
            JSONObject userData = json.getJSONObject(username);
            permissions =  jsonArrayToStringArray(userData.getJSONArray("permissions"));
        } catch (IOException | JSONException e) {
            System.out.println("File not found: " + e);
        }
        return permissions;
    }

    public String[] jsonArrayToStringArray(JSONArray jsonArray) throws JSONException {
        String[] stringArray = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            stringArray[i] = jsonArray.getString(i);
        }
        return stringArray;
    }

}
