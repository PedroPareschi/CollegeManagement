package br.com.pedropareschi.collegemanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewClassTermDTO implements Serializable {

    private static final long serialVersionUID = -3685347619609335132L;
    @NotNull
    private Integer classId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
