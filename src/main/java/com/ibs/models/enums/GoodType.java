package com.ibs.models.enums;

public enum GoodType {
    FRUIT("Фрукт"),VEGETABLE("Овощ");

    private String type;

    GoodType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
