package br.com.pedropareschi.collegemanagement.domain;

import br.com.pedropareschi.collegemanagement.domain.enums.CollegeSituation;
import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Sex;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends User {
    private static final long serialVersionUID = 7941636334360165045L;

    private Integer currentTerm;
    private Integer situation;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "student")
    private List<Term> terms;

    public Student() {
    }

    public Student(Integer id, String name, String cpf, String rg, String email, Sex sex, Boolean hasSpecialNeeds, String specialNeeds, Date birthdate, String password, Integer currentTerm, CollegeSituation situation, Program program) {
        super(id, name, cpf, rg, email, sex, hasSpecialNeeds, specialNeeds, birthdate, password);
        this.currentTerm = currentTerm;
        this.situation = (situation == null) ? null : situation.getCod();
        this.program = program;
        super.addRole(Role.STUDENT);
    }

    public Integer getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(Integer currentTerm) {
        this.currentTerm = currentTerm;
    }

    public Double getGpa() {
        Double grades = 0.0;
        Integer count = 0;
        for (Term term : terms) {
            for (ClassTerm aClass : term.getClasses()) {
                grades += aClass.getFinalGrade();
                count++;
            }
        }
        return grades / count;
    }

    public CollegeSituation getSituation() {
        return CollegeSituation.toEnum(situation);
    }

    public void setSituation(CollegeSituation situation) {
        this.situation = (situation == null) ? null : situation.getCod();
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

}
