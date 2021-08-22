package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.College;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CollegeDTO implements Serializable {

    private static final long serialVersionUID = -5121366655744427582L;

    private Integer id;

    @NotEmpty(message = "not empty field")
    @Length(min = 2, max = 85, message = "Name must have between 2 and 85 characters")
    private String name;

    public CollegeDTO() {
    }

    public CollegeDTO(College college) {
        this.id = college.getId();
        this.name = college.getName();
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
}
