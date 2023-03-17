package com.dev.manicure.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum TipoServico {

    MAO("Mão"),
    PE("Pé"),
    BANHO_GEL("Banho de Gel"),
    BLINDAGEM("Blindagem"),
    ALONGAMENTO("Alongamento");

    private final String descricao;

    private TipoServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    public static List<TipoServico> asList() {
        return Arrays.asList(TipoServico.values());
    }
}
