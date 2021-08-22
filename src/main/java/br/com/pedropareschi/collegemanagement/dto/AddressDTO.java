package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.services.validation.CEP;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1652544830229767258L;

    @NotEmpty
    private String street;

    @NotEmpty
    private String number;

    private String addressLine2;

    @NotEmpty
    private String district;

    @CEP
    private String cep;

    @NotEmpty
    private String town;

    @NotEmpty

    private String state;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.addressLine2 = address.getAddressLine2();
        this.district = address.getDistrict();
        this.cep = address.getCep();
        this.town = address.getTown();
        this.state = address.getState();
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
