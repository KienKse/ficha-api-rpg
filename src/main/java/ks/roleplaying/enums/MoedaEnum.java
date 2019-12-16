package ks.roleplaying.enums;

import java.util.Arrays;

public enum MoedaEnum {

    //TODO: CONTINUAR MOEDA ENUM APLICAR AO PERSONAGEM

    T$(1, 1, "Leal e Bondoso"),
    TP(2, 10, "Neutro e Bondoso"),
    TO(3,  100,"Caótico e Bondoso"),
    TL(4,  1000,"Leal e Neutro");

    private Integer codigo;
    private Integer qtdBase;
    private String moeda;

    MoedaEnum(Integer codigo, Integer qtdBase, String moeda) {
        this.codigo = codigo;
        this.qtdBase = qtdBase;
        this.moeda = moeda;
    }

    public String getMoeda() {
        return moeda;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getQtdBase() {
        return qtdBase;
    }

    public static MoedaEnum getByCodigo(int cod) {
        return Arrays.stream(MoedaEnum.values())
                .filter(item -> item.codigo.compareTo(cod) == 0)
                .findAny()
                .orElse(null);
    }

}
