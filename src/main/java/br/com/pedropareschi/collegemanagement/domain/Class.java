package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.Semester;
import br.com.pedropareschi.collegemanagement.domain.enums.Shift;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Class implements Serializable {

    private static final long serialVersionUID = 5150922383894893540L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer shift;

    private Integer semester;

    private Integer year;

    private Integer slots;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "aClass")
    private List<Classroom> classrooms = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "aClass")
    private Set<ClassTerm> students = new HashSet<>();

    public Class() {
    }

    public Class(Integer id, Shift shift, Semester semester, Integer year, Integer slots, Course course, Teacher teacher) {
        this.id = id;
        this.shift = (shift == null) ? null : shift.getCod();
        this.semester = (shift == null) ? null : semester.getCod();
        this.year = year;
        this.slots = slots;
        this.course = course;
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Shift getShift() {
        return Shift.toEnum(shift);
    }

    public void setShift(Shift shift) {
        this.shift = shift.getCod();
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public Set<ClassTerm> getStudents() {
        return students;
    }

    public void setStudents(Set<ClassTerm> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class)) return false;
        Class aClass = (Class) o;
        return id.equals(aClass.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
