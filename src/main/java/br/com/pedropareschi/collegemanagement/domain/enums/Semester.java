package br.com.pedropareschi.collegemanagement.domain.enums;

import java.util.Calendar;

public enum Semester {
    FIRST(1, "1"),
    SECOND(2, "2");
    
    private final int cod;
    private final String description;

    Semester(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Semester toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Semester semester : Semester.values()){
            if(cod.equals(semester.getCod())){
                return semester;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }

    public static Integer semesterOf(Integer month){
        if(month < 6){
            return 1;
        } else if(month < 12){
            return 2;
        }
        return -1;
    }

    public static Integer getInstance(){
        return semesterOf(Calendar.getInstance().get(Calendar.MONTH));
    }
}
