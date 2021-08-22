package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.services.validation.CEP;
import br.com.pedropareschi.collegemanagement.services.validation.RG;
import br.com.pedropareschi.collegemanagement.services.validation.Unique;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class NewTeacherDTO implements Serializable {
    private static final long serialVersionUID = -8476004397236216436L;

    @NotEmpty
    @Length(min = 2, max = 85, message = "Name must have between 2 and 85 characters")
    private String name;

    @NotEmpty
    @CPF
    @Unique
    private String cpf;

    @NotEmpty
    @RG
    private String rg;

    @NotEmpty
    @Email
    @Unique
    private String email;

    @NotNull
    private Character sex;

    @NotNull
    private Boolean hasSpecialNeeds;
    private String specialNeeds;

    @NotNull
    private Date birthdate;

    @NotEmpty
    private String telephone;

    @NotEmpty
    @Length(min = 2, max = 85, message = "Street name must have between 2 and 85 characters")
    private String street;

    @NotEmpty
    @Length(min = 1, max = 10, message = "Number must have 1 to 10 digits")
    private String number;

    private String addressLine2;

    @NotEmpty
    @Length(min = 2, max = 85, message = "District name must have between 2 and 85 characters")
    private String district;

    @CEP
    private String cep;

    @NotEmpty
    private String town;

    @NotEmpty
    private String state;

    private String registration;

    @NotEmpty
    private String password;

    private Set<Integer> roles;

    public NewTeacherDTO() {
    }

    public NewTeacherDTO(Teacher teacher) {
        this.name = teacher.getName();
        this.cpf = teacher.getCpf();
        this.rg = teacher.getRg();
        this.email = teacher.getEmail();
        this.sex = teacher.getSex().getCode();
        this.hasSpecialNeeds = teacher.getHasSpecialNeeds();
        this.specialNeeds = teacher.getSpecialNeeds();
        this.birthdate = teacher.getBirthdate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Integer> getRoles() {
        return roles;
    }

    public void setRoles(Set<Integer> roles) {
        this.roles = roles;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
