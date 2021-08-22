package br.com.pedropareschi.collegemanagement.domain.enums;

public enum Role {
    ADMIN(1, "ROLE_ADMIN"),
    COLLEGE_ADMIN(2, "ROLE_COLLEGE_ADMIN"),
    TEACHER(3, "ROLE_TEACHER"),
    STUDENT(4, "ROLE_STUDENT");

    private Integer cod;
    private String description;

    Role(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Role toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Role role : Role.values()){
            if(cod.equals(role.getCod())){
                return role;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
