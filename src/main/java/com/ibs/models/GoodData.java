package com.ibs.models;

import java.util.Objects;

public class GoodData {
    private String name;
    private String type;
    private boolean exotic;

    public GoodData(Good good) {
        this.name = good.getName();
        this.type = good.getType().name();
        this.exotic = good.isExotic();
    }

    public GoodData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExotic() {
        return exotic;
    }

    public void setExotic(boolean exotic) {
        this.exotic = exotic;
    }

    @Override
    public String toString() {
        return "GoodData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", exotic=" + exotic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodData goodData = (GoodData) o;
        return exotic == goodData.exotic && Objects.equals(name, goodData.name) && Objects.equals(type, goodData.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, exotic);
    }
}
