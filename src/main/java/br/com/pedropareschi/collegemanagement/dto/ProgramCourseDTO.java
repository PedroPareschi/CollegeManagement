package br.com.pedropareschi.collegemanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProgramCourseDTO implements Serializable {
    private static final long serialVersionUID = -724100951585895432L;
    @NotNull
    private Integer courseId;

    @NotNull
    private Integer recommendedTerm;

    public ProgramCourseDTO() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getRecommendedTerm() {
        return recommendedTerm;
    }

    public void setRecommendedTerm(Integer recommendedTerm) {
        this.recommendedTerm = recommendedTerm;
    }
}
