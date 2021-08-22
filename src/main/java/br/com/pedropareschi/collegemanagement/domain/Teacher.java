package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Sex;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Teacher extends User{
    private static final long serialVersionUID = -5998799818393562142L;

    private String registration;

    @OneToMany(mappedBy = "teacher")
    private List<Class> classes;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String cpf, String rg, String email, Sex sex, Boolean hasSpecialNeeds, String specialNeeds, Date birthdate, String password, String registration) {
        super(id, name, cpf, rg, email, sex, hasSpecialNeeds, specialNeeds, birthdate, password);
        this.registration = registration;
        super.addRole(Role.TEACHER);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
