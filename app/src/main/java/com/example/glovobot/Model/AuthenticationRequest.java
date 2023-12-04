package com.example.glovobot.Model;

import com.google.gson.annotations.SerializedName;

public class AuthenticationRequest {

    @SerializedName("grantType")
    private String grantType;
    @SerializedName("password")
    private String password;
    @SerializedName("userType")
    private String userType;
    @SerializedName("username")
    private String username;

    public AuthenticationRequest(String grantType, String password, String userType, String username) {
        this.grantType = grantType;
        this.password = password;
        this.userType = userType;
        this.username = username;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

/*
          {
        grantType: "password",
        password,
        termsAndConditionsChecked: false,
        userType: "courier",
        username,
      },
     */
}
