package br.com.pedropareschi.collegemanagement.domain.enums;

public enum Shift {
    MORNING(1, "Morning"),
    AFTERNOOON(2, "Afternoon"),
    EVENING(3, "Evening"),
    REMOTE(4, "Remote");

    private final int cod;
    private final String name;

    Shift(int cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public static Shift toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Shift shift: Shift.values()){
            if(cod.equals(shift.getCod())){
                return shift;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
