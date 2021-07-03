package org.kviz.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    public void connect() {
        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Ime biblioteke za rad s bazom podataka " + meta.getDriverName());
                System.out.println("Stvorena je nova baza.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
