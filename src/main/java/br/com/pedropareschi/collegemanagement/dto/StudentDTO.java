package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.services.validation.Unique;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class StudentDTO implements Serializable {
    private static final long serialVersionUID = -8650116971354996096L;

    private Integer id;

    @NotEmpty
    @Length(min = 2, max = 85, message = "Name must have between 2 and 85 characters")
    private String name;

    @NotEmpty
    @Email
    @Unique
    private String email;

    @NotNull
    private Boolean hasSpecialNeeds;
    private String specialNeeds;

    @NotNull
    private String password;

    @NotNull
    private List<String> telephones;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
        this.hasSpecialNeeds = student.getHasSpecialNeeds();
        this.specialNeeds = student.getSpecialNeeds();
        this.password = student.getPassword();
        this.telephones = student.getTelephones();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }
}
