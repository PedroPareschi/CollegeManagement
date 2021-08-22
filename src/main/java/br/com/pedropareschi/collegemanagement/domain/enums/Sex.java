package br.com.pedropareschi.collegemanagement.domain.enums;

public enum Sex {
    MASCULINE('M', "Masculino"),
    FEMININE('F', "Feminino"),
    OTHER('O', "Outro");

    private char code;
    private String name;

    Sex(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Sex toEnum(Character character){
        if(character == null){
            return null;
        }
        for(Sex Sex : Sex.values()){
            if(character.equals(Sex.getCode())){
                return Sex;
            }
        }
        throw new IllegalArgumentException("Caractere invalido: " + character);
    }
}
