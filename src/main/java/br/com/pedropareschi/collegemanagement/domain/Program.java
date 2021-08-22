package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.Shift;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Program implements Serializable {

    private static final long serialVersionUID = -2119964984916879940L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String department;
    private Integer shift;
    private Integer minConclusionTime;
    private Integer maxConclusionTime;

    @JsonIgnore
    @OneToMany(mappedBy = "program")
    private List<Student> students = new ArrayList<>();


    @OneToMany(mappedBy = "program")
    private Set<ProgramCourse> courses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    public Program() {
    }

    public Program(Integer id, String name, String department, Shift shift, Integer minConclusionTime, Integer maxConclusionTime, Campus campus) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.shift = (shift == null) ? null : shift.getCod();
        this.minConclusionTime = minConclusionTime;
        this.maxConclusionTime = maxConclusionTime;
        this.campus = campus;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Shift getShift() {
        return Shift.toEnum(shift);
    }

    public void setShift(Shift shift) {
        this.shift = shift.getCod();
    }

    public Integer getMinConclusionTime() {
        return minConclusionTime;
    }

    public void setMinConclusionTime(Integer minConclusionTime) {
        this.minConclusionTime = minConclusionTime;
    }

    public Integer getMaxConclusionTime() {
        return maxConclusionTime;
    }

    public void setMaxConclusionTime(Integer maxConclusionTime) {
        this.maxConclusionTime = maxConclusionTime;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Set<ProgramCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<ProgramCourse> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program)) return false;
        Program program = (Program) o;
        return id.equals(program.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
