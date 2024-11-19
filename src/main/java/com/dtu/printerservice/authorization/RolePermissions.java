package com.dtu.printerservice.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RolePermissions {
    public static String[] ADMIN_PERMISSIONS;
    public static String[] JANITOR_PERMISSIONS;
    public static String[] SUPERRUSER_PERMISSIONS;
    public static String[] BASIC_PERMISSIONS;

    public static void configureRolePermissions() {
        try {
            // Path to your JSON file
            File jsonFile = new File("src/main/resources/rbac.permission.json");

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file as a tree
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Navigate to the "roles" array
            JsonNode rolesArray = rootNode.path("roles");

            // Iterate through each role in the roles array
            if (rolesArray.isArray()) {
                for (JsonNode roleNode : rolesArray) {
                    // Iterate through all fields (role names) in the role object
                    roleNode.fieldNames().forEachRemaining(roleName -> {
                        // Get permissions for the role
                        JsonNode permissionsNode = roleNode.path(roleName).get(0).path("Permissions");

                        if (permissionsNode.isArray()) {
                            // Convert JsonNode to a String array
                            ArrayList<String> permissionsList = new ArrayList<>();
                            permissionsNode.forEach(permission -> permissionsList.add(permission.asText()));
                            String[] permissionsArray = permissionsList.toArray(new String[0]);

                            // Assign permissions based on role name
                            switch (roleName.toUpperCase()) {
                                case "ADMIN":
                                    ADMIN_PERMISSIONS = permissionsArray;
                                    break;
                                case "JANITOR":
                                    JANITOR_PERMISSIONS = permissionsArray;
                                    break;
                                case "SUPERUSER":
                                    SUPERRUSER_PERMISSIONS = permissionsArray;
                                    break;
                                case "BASIC":
                                    BASIC_PERMISSIONS = permissionsArray;
                                    break;
                                default:
                                    System.out.println("Unknown role: " + roleName);
                            }
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
