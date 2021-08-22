package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Student;

public class StudentExtendedDTO extends StudentDTO{

    private static final long serialVersionUID = 8634563231603876885L;

    private Integer currentTerm;
    private Integer situation;
    private Integer programId;

    public StudentExtendedDTO() {
    }

    public StudentExtendedDTO(Student student) {
        super(student);
        this.currentTerm = student.getCurrentTerm();
        this.situation = student.getSituation().getCod();
        this.programId = student.getProgram().getId();
    }

    public Integer getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(Integer currentTerm) {
        this.currentTerm = currentTerm;
    }

    public Integer getSituation() {
        return situation;
    }

    public void setSituation(Integer situation) {
        this.situation = situation;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
