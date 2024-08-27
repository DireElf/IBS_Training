package com.ibs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibs.models.enums.GoodType;

public class Good {
    private int id;
    private String name;
    private GoodType type;
    private boolean isExotic;

    public Good(int id, String name, GoodType type, boolean isExotic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isExotic = isExotic;
    }

    public Good(String name, GoodType type, boolean isExotic) {
        this.name = name;
        this.type = type;
        this.isExotic = isExotic;
    }

    public Good() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GoodType getType() {
        return type;
    }

    public boolean isExotic() {
        return isExotic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(GoodType type) {
        this.type = type;
    }

    public void setExotic(boolean exotic) {
        isExotic = exotic;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isExotic=" + isExotic +
                '}';
    }
}
