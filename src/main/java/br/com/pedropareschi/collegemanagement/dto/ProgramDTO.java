package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Program;

import java.io.Serializable;
import java.util.List;

public class ProgramDTO implements Serializable {

    private static final long serialVersionUID = -8657739990264274291L;
    private String name;
    private String department;
    private Integer shift;
    private Integer minConclusionTime;
    private Integer maxConclusionTime;
    private List<ProgramCourseDTO> courses;
    private Integer campusId;

    public ProgramDTO() {
    }

    public ProgramDTO(Program program) {
        this.name = program.getName();
        this.department = program.getDepartment();
        this.shift = program.getShift().getCod();
        this.minConclusionTime = program.getMinConclusionTime();
        this.maxConclusionTime = program.getMaxConclusionTime();
        this.campusId = program.getCampus().getId();
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

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
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

    public List<ProgramCourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<ProgramCourseDTO> courses) {
        this.courses = courses;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }
}
