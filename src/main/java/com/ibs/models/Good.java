package com.ibs.models;

import com.ibs.models.enums.GoodType;

public class Good {
    private String name;
    private GoodType type;
    private boolean isExotic;

    public Good(String name, GoodType type, boolean isExotic) {
        this.name = name;
        this.type = type;
        this.isExotic = isExotic;
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
}
