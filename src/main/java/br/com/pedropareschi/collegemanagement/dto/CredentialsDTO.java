package br.com.pedropareschi.collegemanagement.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 8764070322109036883L;

    private String email;
    private String password;

    public CredentialsDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
