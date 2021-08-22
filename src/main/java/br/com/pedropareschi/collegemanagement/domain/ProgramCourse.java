package br.com.pedropareschi.collegemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProgramCourse implements Serializable {
    private static final long serialVersionUID = 2713240919201090186L;

    @EmbeddedId
    @JsonIgnore
    private ProgramCoursePK id;

    @ManyToOne
    @MapsId("programId")
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer recommendedTerm;

    public ProgramCourse() {
    }

    public ProgramCourse(Program program, Course course, Integer recommendedTerm) {
        this.id = new ProgramCoursePK(program.getId(), course.getId());
        this.program = program;
        this.course = course;
        this.recommendedTerm = recommendedTerm;
    }

    public ProgramCoursePK getId() {
        return id;
    }

    public void setId(ProgramCoursePK id) {
        this.id = id;
    }

    @JsonIgnore
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getRecommendedTerm() {
        return recommendedTerm;
    }

    public void setRecommendedTerm(Integer recommendedTerm) {
        this.recommendedTerm = recommendedTerm;
    }
}
