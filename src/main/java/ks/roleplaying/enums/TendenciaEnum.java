package ks.roleplaying.enums;

public enum TendenciaEnum {

    LB("Leal e Bondoso"),
    NB("Neutro e Bondoso"),
    CB("Caótico e Bondoso"),
    LN("Leal e Neutro"),
    N("Neutro"),
    CN("Caótico e Neutro"),
    LM("Leal e Maligno"),
    CM("Caótico e Maligno"),
    NM("Neutro e Maligno");


    String tendencia;

    TendenciaEnum(String tendencia) {
        this.tendencia = tendencia;
    }

    public String getTendencia() {
        return tendencia;
    }
}
