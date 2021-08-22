package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Class;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AlterClassDTO implements Serializable {
    private static final long serialVersionUID = 1751960596564698703L;

    @NotNull
    private Integer shift;
    @NotNull
    private Integer slots;
    @NotNull
    private Integer courseId;
    @NotNull
    private Integer teacherId;

    public AlterClassDTO() {
    }

    public AlterClassDTO(Class aClass) {
        this.shift = aClass.getShift().getCod();
        this.slots = aClass.getSlots();
        this.courseId = aClass.getCourse().getId();
        this.teacherId = aClass.getTeacher().getId();
    }


    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
