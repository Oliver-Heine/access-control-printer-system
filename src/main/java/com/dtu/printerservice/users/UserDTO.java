package com.dtu.printerservice.users;

import java.util.Base64;

public class UserDTO {
    private String name;
    private String role;
    private byte[] password;
    private byte[] salt;

    public UserDTO(String name, byte[] password, String role, byte[] salt) {
        this.name = name;
        this.role = role;
        this.password = password;
        this.salt = salt;
    }

    public UserDTO() {}

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPasswordBase64() {
        return Base64.getEncoder().encodeToString(password);
    }

    public void setPasswordBase64(String passwordBase64) {
        this.password = Base64.getDecoder().decode(passwordBase64);
    }

    public String getSaltBase64() {
        return Base64.getEncoder().encodeToString(salt);
    }

    public void setSaltBase64(String saltBase64) {
        this.salt = Base64.getDecoder().decode(saltBase64);
    }


}
