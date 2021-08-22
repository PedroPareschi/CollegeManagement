package br.com.pedropareschi.collegemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Campus implements Serializable {

    private static final long serialVersionUID = 872371850169365189L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    @JsonIgnore
    @OneToMany(mappedBy = "campus")
    private List<Program> programs;

    @JsonIgnore
    @OneToMany(mappedBy = "campus")
    private List<Classroom> classrooms;

    public Campus() {
    }

    public Campus(Integer id, String name, Address address, College college) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.college = college;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> cours) {
        this.programs = cours;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campus)) return false;
        Campus campus = (Campus) o;
        return id.equals(campus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
