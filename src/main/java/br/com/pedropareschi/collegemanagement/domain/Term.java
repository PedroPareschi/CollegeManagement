package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.Semester;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Term implements Serializable {

    private static final long serialVersionUID = -6705141889468886402L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer semester;

    private Integer year;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "term")
    private Set<ClassTerm> classes = new HashSet<>();

    public Term() {
    }

    public Term(Integer id, Integer number, Semester semester, Integer year, Student student) {
        this.id = id;
        this.number = number;
        this.semester = (semester == null) ? null : semester.getCod();
        this.year = year;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Semester getSemester() {
        return Semester.toEnum(semester);
    }

    public void setSemester(Semester semester) {
        this.semester = semester.getCod();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<ClassTerm> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassTerm> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        Term term = (Term) o;
        return id.equals(term.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
