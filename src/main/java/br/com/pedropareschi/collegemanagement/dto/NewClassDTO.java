package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Class;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class NewClassDTO extends AlterClassDTO{

    private static final long serialVersionUID = 6464709522931623197L;
    @NotNull
    private List<ClassroomDTO> classrooms = new ArrayList<>();

    public NewClassDTO() {
    }

    public NewClassDTO(Class aClass) {
        super(aClass);
    }

    public List<ClassroomDTO> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<ClassroomDTO> classrooms) {
        this.classrooms = classrooms;
    }
}
