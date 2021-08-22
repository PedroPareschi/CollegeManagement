package br.com.pedropareschi.collegemanagement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassTermPK implements Serializable {

    private static final long serialVersionUID = 3204510604650877767L;

    @Column(name = "term_id")
    private Integer termId;

    @Column(name = "class_id")
    private Integer classId;

    public ClassTermPK() {
    }

    public ClassTermPK(Integer termId, Integer classId) {
        this.termId = termId;
        this.classId = classId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer term_id) {
        this.termId = term_id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer class_id) {
        this.classId = class_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassTermPK that = (ClassTermPK) o;
        return Objects.equals(termId, that.termId) && Objects.equals(classId, that.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId, classId);
    }
}
