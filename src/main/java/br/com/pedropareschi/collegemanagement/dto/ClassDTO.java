package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.Classroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassDTO implements Serializable {
    private static final long serialVersionUID = 6617910669246132000L;

    private Integer id;
    private Integer shift;
    private Integer semester;
    private Integer year;
    private Integer slots;
    private Integer courseId;
    private Integer teacherId;
    private List<Integer> classroomsIds = new ArrayList<>();

    public ClassDTO() {
    }

    public ClassDTO(Class aClass) {
        this.id = aClass.getId();
        this.shift = aClass.getShift().getCod();
        this.semester = aClass.getSemester().getCod();
        this.year = aClass.getYear();
        this.slots = aClass.getSlots();
        this.courseId = aClass.getCourse().getId();
        this.teacherId = aClass.getTeacher().getId();
        for(Classroom classroom: aClass.getClassrooms()){
            this.classroomsIds.add(classroom.getId());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public List<Integer> getClassroomsIds() {
        return classroomsIds;
    }

    public void setClassroomsIds(List<Integer> classroomsIds) {
        this.classroomsIds = classroomsIds;
    }
}
