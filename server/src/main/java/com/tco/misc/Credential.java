package com.tco.misc;

public class Credential {
    // shared user with read-only access
    public final static String USER = "cs314-db";
    public final static String PASSWORD = "eiK5liet1uej";
    
    // connection information when using port forwarding from localhost
    final static String URL = "jdbc:mariadb://127.0.0.1:56247/cs314";
    public static String url() {
        return URL;
    }
}
