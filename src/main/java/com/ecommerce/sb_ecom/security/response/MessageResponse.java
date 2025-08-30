package com.ecommerce.sb_ecom.security.response;

public class MessageResponse
{
    private String body;

    public MessageResponse(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
