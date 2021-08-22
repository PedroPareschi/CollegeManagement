package br.com.pedropareschi.collegemanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Test implements Serializable {

    private static final long serialVersionUID = -4131759967376339220L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String course;

    @JsonFormat(pattern = "dd/MM HH:mm")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "class_Id")
    private Class aClass;


    public Test() {
    }

    public Test(Integer id, String course, Date date, Class aClass) {
        this.id = id;
        this.course = course;
        this.date = date;
        this.aClass = aClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setClass(Class aClass) {
        this.aClass = aClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return id.equals(test.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
