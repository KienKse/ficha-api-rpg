package ks.roleplaying.enums;

import java.util.Arrays;

public enum TendenciaEnum {

    LB(1, "Leal e Bondoso"),
    NB(2, "Neutro e Bondoso"),
    CB(3, "Caótico e Bondoso"),
    LN(4, "Leal e Neutro"),
    N(5,"Neutro"),
    CN(6,"Caótico e Neutro"),
    LM(7,"Leal e Maligno"),
    CM(8,"Caótico e Maligno"),
    NM(9,"Neutro e Maligno");


    private Integer codigo;
    private String tendencia;

    TendenciaEnum(Integer codigo , String tendencia) {
        this.codigo = codigo;
        this.tendencia = tendencia;
    }

    public String getTendencia() {
        return tendencia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static TendenciaEnum getByCodigo(int cod) {
        return Arrays.stream(TendenciaEnum.values())
                .filter(item -> item.codigo.compareTo(cod) == 0)
                .findAny()
                .orElse(null);
    }
}
