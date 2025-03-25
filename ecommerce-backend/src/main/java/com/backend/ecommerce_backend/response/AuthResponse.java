package com.backend.ecommerce_backend.response;

public class AuthResponse {

    private String jwt;
    private String message;


    public AuthResponse(String jwt, String message){
        super();
        this.jwt=jwt;
        this.message=message;
    }

    public AuthResponse() {
        //TODO Auto-generated constructor stub
    }

    /**
     * @return String return the jwt
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * @param jwt the jwt to set
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
