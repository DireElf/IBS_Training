package com.ibs.utils.constants;

public class QueriesConst {
    public static final String SELECT_ALL = "SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC FROM FOOD";
    public static final String SELECT_ALL_ENTRIES_COUNT = "SELECT COUNT(*) FROM FOOD";
    public static final String SELECT_ENTRY_BY_ID
            = "SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC FROM FOOD WHERE FOOD_ID = ?";
    public static final String ADD_NEW_GOOD
            = "INSERT INTO FOOD(FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)";
    public static final String DELETE_ENTRY_BY_ID = "DELETE FROM FOOD WHERE FOOD_ID = ?";
}
