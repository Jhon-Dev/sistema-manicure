package com.dev.manicure.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum ServiceType {

    MAO("Mão"),
    PE("Pé"),
    BANHO_GEL("Banho de Gel"),
    BLINDAGEM("Blindagem"),
    ALONGAMENTO("Alongamento");

    private final String descricao;

    private ServiceType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    public static List<ServiceType> asList() {
        return Arrays.asList(ServiceType.values());
    }
}
