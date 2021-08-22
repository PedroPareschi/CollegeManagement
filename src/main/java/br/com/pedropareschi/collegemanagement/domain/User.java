package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {

    private static final long serialVersionUID = -5562036392856704832L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String rg;
    @Column(unique = true)
    private String email;
    private Character sex;
    private Boolean hasSpecialNeeds;
    private String specialNeeds;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

    @JsonIgnore
    private String password;

    @ElementCollection
    @CollectionTable(name = "TELEPHONES")
    private List<String> telephones = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="ROLES")
    private Set<Integer> roles = new HashSet<>();

    public User() {
    }

    public User(Integer id, String name, String cpf, String rg, String email, Sex sex, Boolean hasSpecialNeeds, String specialNeeds, Date birthdate, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.sex = (sex == null)? null : sex.getCode();
        this.hasSpecialNeeds = hasSpecialNeeds;
        this.specialNeeds = specialNeeds;
        this.birthdate = birthdate;
        this.password = password;
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

    public Sex getSex() {
        return Sex.toEnum(sex);
    }

    public void setSex(Sex sex) {
        this.sex =  (sex == null)? null : sex.getCode();
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Role> getRoles() {
        return roles.stream().map(Role::toEnum).collect(Collectors.toSet());
    }

    public void addRole(Role role) {
        this.roles.add(role.getCod());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
