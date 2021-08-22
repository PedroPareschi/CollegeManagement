package br.com.pedropareschi.collegemanagement.domain.enums;

public enum ClassSituation {
    SUBSCRIBED(1, "Inscrito"),
    UNSUBSCRIBED(2, "Não inscrito"),
    ACTIVE(3, "Ativo"),
    PASSED(4, "Aprovado"),
    FAILED(5, "Reprovado");


    private final Integer cod;
    private final String description;

    ClassSituation(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ClassSituation toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(ClassSituation situation: ClassSituation.values()){
            if(cod.equals(situation.getCod())){
                return situation;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
