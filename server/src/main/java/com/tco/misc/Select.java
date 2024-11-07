package com.tco.misc;

public class Select {

    private final static String TABLE = "world";
    private final static String COLUMNS = "id,name,municipality,iso_region,iso_country,continent,latitude,longitude,altitude";
    public static String match(String match, int limit) {
        return statement(match, "DISTINCT " + COLUMNS, "LIMIT " + limit);
    }

    public static String found(String match) {
        return statement(match, "COUNT(*) AS count ", "");
    }

    public static String statement(String match, String data, String limit) {
        return "SELECT "
            + data
            + " FROM " + TABLE
            + " WHERE name LIKE \"%" + match + "%\" "
            + limit
            + " ;";
    }
}
