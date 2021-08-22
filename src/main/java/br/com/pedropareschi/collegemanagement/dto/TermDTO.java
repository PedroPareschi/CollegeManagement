package br.com.pedropareschi.collegemanagement.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TermDTO {

    @NotNull
    private Integer studentId;

    @NotNull
    private List<NewClassTermDTO> classes;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public List<NewClassTermDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<NewClassTermDTO> classes) {
        this.classes = classes;
    }
}
