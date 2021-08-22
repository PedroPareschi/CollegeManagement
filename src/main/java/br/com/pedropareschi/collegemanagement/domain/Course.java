package br.com.pedropareschi.collegemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = -4139282381380485572L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer credits;
    private Integer totalHours;
    private String summary;
    private Integer maxAbsencesAllowed;
    private Double minScoreForApproval;

    @ManyToMany
    @JoinTable(name = "COURSE_PREREQUISITES",
            joinColumns = @JoinColumn(name = "prerequisite_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisiteTo_id"))
    private List<Course> prerequisites = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "prerequisites")
    private List<Course> prerequisiteTo = new ArrayList<>();


    @OneToMany(mappedBy = "course")
    private Set<ProgramCourse> programs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Class> classes = new ArrayList<>();

    public Course() {
    }

    public Course(Integer id, String name, Integer credits, Integer totalHours, String summary, Integer maxAbsencesAllowed, Double minScoreForApproval) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.totalHours = totalHours;
        this.summary = summary;
        this.maxAbsencesAllowed = maxAbsencesAllowed;
        this.minScoreForApproval = minScoreForApproval;
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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getMaxAbsencesAllowed() {
        return maxAbsencesAllowed;
    }

    public void setMaxAbsencesAllowed(Integer maxAbsencesAllowed) {
        this.maxAbsencesAllowed = maxAbsencesAllowed;
    }

    public Double getMinScoreForApproval() {
        return minScoreForApproval;
    }

    public void setMinScoreForApproval(Double minScoreForApproval) {
        this.minScoreForApproval = minScoreForApproval;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<Course> getPrerequisiteTo() {
        return prerequisiteTo;
    }

    public void setPrerequisiteTo(List<Course> prerequisiteTo) {
        this.prerequisiteTo = prerequisiteTo;
    }

    @JsonIgnore
    public Set<ProgramCourse> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<ProgramCourse> programs) {
        this.programs = programs;
    }

    @JsonIgnore


    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course that = (Course) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
