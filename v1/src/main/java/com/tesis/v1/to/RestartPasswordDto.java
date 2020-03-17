package com.tesis.v1.to;

import javax.validation.constraints.NotEmpty;

public class RestartPasswordDto {

    @NotEmpty
    String tokenDescription;
    @NotEmpty
    String password;

    public String getTokenDescription() {
        return tokenDescription;
    }

    public void setTokenDescription(String tokenDescription) {
        this.tokenDescription = tokenDescription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
