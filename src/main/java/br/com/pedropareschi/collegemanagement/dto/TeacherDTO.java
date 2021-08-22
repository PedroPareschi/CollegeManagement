package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.services.validation.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class TeacherDTO implements Serializable {
    private static final long serialVersionUID = -157157293053537540L;

    @NotEmpty
    @Email
    @Unique
    private String email;

    @NotNull
    private Boolean hasSpecialNeeds;
    private String specialNeeds;

    @NotNull
    private Set<String> telephones;

    @NotEmpty
    private String password;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        this.email = teacher.getEmail();
        this.hasSpecialNeeds = teacher.getHasSpecialNeeds();
        this.specialNeeds = teacher.getSpecialNeeds();
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasSpecialNeeds() {
        return hasSpecialNeeds;
    }

    public void setHasSpecialNeeds(Boolean hasSpecialNeeds) {
        this.hasSpecialNeeds = hasSpecialNeeds;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public Set<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<String> telephones) {
        this.telephones = telephones;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
