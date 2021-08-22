package br.com.pedropareschi.collegemanagement.dto;

import br.com.pedropareschi.collegemanagement.domain.Classroom;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ClassroomDTO implements Serializable {

    private Integer id;

    @NotNull
    private Integer campusId;
    @NotEmpty
    private String building;
    @NotEmpty
    private String floor;
    @NotEmpty
    private String room;

    @NotNull
    @Min(1)
    @Max(7)
    private Integer dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    private Date start;

    @JsonFormat(pattern = "HH:mm")
    private Date end;

    public ClassroomDTO() {
    }

    public ClassroomDTO(Classroom classroom) {
        this.id = classroom.getId();
        this.campusId = classroom.getCampus().getId();
        this.building = classroom.getBuilding();
        this.floor = classroom.getFloor();
        this.room = classroom.getRoom();
        this.dayOfWeek = classroom.getDayOfWeek().getValue();
        this.start = classroom.getStart();
        this.end = classroom.getEnd();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
