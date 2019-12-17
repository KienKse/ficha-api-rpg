package ks.roleplaying.enums;

import java.math.BigDecimal;
import java.util.Arrays;

public enum MoedaEnum {

    T$(1, BigDecimal.valueOf(1)),
    TP(2, BigDecimal.valueOf(10)),
    TO(3, BigDecimal.valueOf(100)),
    TL(4, BigDecimal.valueOf(1000));

    private Integer codigo;
    private BigDecimal qtdBase;

    MoedaEnum(Integer codigo, BigDecimal qtdBase) {
        this.codigo = codigo;
        this.qtdBase = qtdBase;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public BigDecimal getQtdBase() {
        return qtdBase;
    }

    public static MoedaEnum getByCodigo(int cod) {
        return Arrays.stream(MoedaEnum.values())
                .filter(item -> item.codigo.compareTo(cod) == 0)
                .findAny()
                .orElse(null);
    }

    public static BigDecimal converterTibarNormal(BigDecimal tibaresNormal, MoedaEnum tibar) {
        if(tibar.getQtdBase().compareTo(BigDecimal.ONE) != 0) {
            return tibaresNormal.divide(tibar.getQtdBase());
        } else {
            return tibaresNormal;
        }
    }

}