package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Course;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 7081592560926705174L;
    @NotEmpty
    private String name;
    @NotNull
    @Min(1)
    private Integer credits;
    @NotNull
    private Integer totalHours;
    private String summary;
    private Integer maxAbsencesAllowed;
    private Double minScoreForApproval;
    private List<Integer> prerequisites;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.name = course.getName();
        this.credits = course.getCredits();
        this.totalHours = course.getTotalHours();
        this.summary = course.getSummary();
        this.maxAbsencesAllowed = course.getMaxAbsencesAllowed();
        this.minScoreForApproval = course.getMinScoreForApproval();
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

    public List<Integer> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Integer> prerequisites) {
        this.prerequisites = prerequisites;
    }
}
