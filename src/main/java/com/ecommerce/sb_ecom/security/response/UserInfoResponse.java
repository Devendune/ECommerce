package com.ecommerce.sb_ecom.security.response;

import java.util.List;

public class UserInfoResponse
{
    private Long id;
    private String jwtToken;
    private String username;

    private List<String>roles;

    public UserInfoResponse(Long id,String jwtToken, String username, List<String> roles) {
        this.id=id;
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
