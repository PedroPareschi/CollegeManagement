package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Program;

import java.io.Serializable;

public class ProgramsByCollegeDTO implements Serializable {

    private static final long serialVersionUID = -224689228139935206L;
    private Integer id;
    private String name;
    private String department;
    private String shift;
    private String campus;

    public ProgramsByCollegeDTO() {
    }

    public ProgramsByCollegeDTO(Program program) {
        this.id = program.getId();
        this.name = program.getName();
        this.department = program.getDepartment();
        this.shift = program.getShift().getName();
        this.campus = program.getCampus().getName();
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
