package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.ClassSituation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ClassTerm implements Serializable {

    private static final long serialVersionUID = -6858576291026976550L;

    @EmbeddedId
    @JsonIgnore
    private ClassTermPK id;

    @ManyToOne
    @MapsId("termId")
    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    private Class aClass;

    private Integer situation;

    private Integer absences;

    @ElementCollection
    @CollectionTable(name = "GRADES")
    private List<Double> grades;

    public ClassTerm() {
    }

    public ClassTerm(Term term, Class aClass, ClassSituation situation, Integer absences) {
        this.term = term;
        this.aClass = aClass;
        this.situation = (situation == null) ? null : situation.getCod();
        this.absences = absences;
        this.id = new ClassTermPK(term.getId(), aClass.getId());
    }

    public ClassTermPK getId() {
        return id;
    }

    public void setId(ClassTermPK id) {
        this.id = id;
    }

    @JsonIgnore
    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public ClassSituation getSituation() {
        return ClassSituation.toEnum(situation);
    }

    public void setSituation(ClassSituation situation) {
        this.situation = situation.getCod();
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

    public Double getFinalGrade(){
        Double finalGrade = 0.0;
        for(Double grade: grades){
            finalGrade+=grade;
        }
        finalGrade /= grades.size();
        return finalGrade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassTerm{");
        sb.append(aClass.getCourse().getName());
        sb.append(", ").append(aClass.getSemester().getDescription()).append("/").append(aClass.getYear());
        sb.append(", Term=").append(term.getNumber());
        sb.append(", situation=").append(ClassSituation.toEnum(situation));
        sb.append(", absences=").append(absences);
        sb.append(", grades=").append(grades);
        sb.append(", final grade=").append(getFinalGrade());
        return sb.toString();
    }
}

