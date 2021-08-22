package br.com.pedropareschi.collegemanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

@Entity
public class Classroom implements Serializable {

    private static final long serialVersionUID = 4477374899228620945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    private String building;
    private String floor;
    private String room;

    private DayOfWeek dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    private Date start;

    @JsonFormat(pattern = "HH:mm")
    private Date end;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    public Classroom() {
    }

    public Classroom(Integer id, Campus campus, String building, String floor, String room, DayOfWeek dayOfWeek, Date start, Date end, Class aClass) {
        this.id = id;
        this.campus = campus;
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
        this.aClass = aClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
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

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Classroom)) return false;
        Classroom classroom = (Classroom) o;
        return id.equals(classroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
