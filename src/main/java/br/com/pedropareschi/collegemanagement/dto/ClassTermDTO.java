package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.ClassTerm;

import java.io.Serializable;
import java.util.List;

public class ClassTermDTO implements Serializable {

    private static final long serialVersionUID = -2967281455536369638L;
    private Integer situation;
    private Integer absences;
    private List<Double> grades;

    public ClassTermDTO() {
    }

    public ClassTermDTO(ClassTerm classTerm) {
        this.situation = classTerm.getSituation().getCod();
        this.absences = classTerm.getAbsences();
        this.grades = classTerm.getGrades();
    }

    public Integer getSituation() {
        return situation;
    }

    public void setSituation(Integer situation) {
        this.situation = situation;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }
}
