package br.com.pedropareschi.collegemanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 2977139845416755455L;
    @NotEmpty
    @Email
    private String email;

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
