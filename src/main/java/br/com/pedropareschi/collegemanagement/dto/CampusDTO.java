package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Campus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CampusDTO implements Serializable {
    private static final long serialVersionUID = 1366546945191569810L;

    @NotNull
    private String name;

    @NotNull
    private AddressDTO addressDTO;

    public CampusDTO() {
    }

    public CampusDTO(Campus campus) {
        this.name = campus.getName();
        this.addressDTO = new AddressDTO(campus.getAddress());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
}
