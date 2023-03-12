package com.dev.manicure.entity.enums;

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
}
