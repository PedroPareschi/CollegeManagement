package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.domain.ProgramCourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoursesByProgramDTO implements Serializable {

    private static final long serialVersionUID = 584913532485179018L;
    private Integer recommendedTerm;
    private String name;
    private Integer credits;
    private Integer totalHours;
    private List<String> prerequisites;

    public CoursesByProgramDTO() {
    }

    public CoursesByProgramDTO(ProgramCourse programCourse) {
        this.recommendedTerm = programCourse.getRecommendedTerm();
        this.name = programCourse.getCourse().getName();
        this.credits = programCourse.getCourse().getCredits();
        this.totalHours = programCourse.getCourse().getTotalHours();
        prerequisites = new ArrayList<>();
        for (Course course : programCourse.getCourse().getPrerequisites()) {
            prerequisites.add(course.getName());
        }
    }

    public Integer getRecommendedTerm() {
        return recommendedTerm;
    }

    public void setRecommendedTerm(Integer recommendedTerm) {
        this.recommendedTerm = recommendedTerm;
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

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
}
