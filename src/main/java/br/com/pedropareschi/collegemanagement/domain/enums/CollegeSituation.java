package br.com.pedropareschi.collegemanagement.domain.enums;

public enum CollegeSituation {
    ACTIVE(1, "Active"),
    PAUSED(2, "Paused"),
    CANCELED(3, "Canceled"),
    CONCLUDED(4, "Concluded");

    private final Integer cod;
    private final String descricao;

    CollegeSituation(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CollegeSituation toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(CollegeSituation situation: CollegeSituation.values()){
            if(cod.equals(situation.getCod())){
                return situation;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
