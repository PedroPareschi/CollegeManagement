package br.com.pedropareschi.collegemanagement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgramCoursePK implements Serializable {
    private static final long serialVersionUID = 7608289419880825924L;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "course_id")
    private Integer courseId;

    public ProgramCoursePK() {
    }

    public ProgramCoursePK(Integer programId, Integer courseId) {
        this.programId = programId;
        this.courseId = courseId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramCoursePK that = (ProgramCoursePK) o;
        return Objects.equals(programId, that.programId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, courseId);
    }
}
