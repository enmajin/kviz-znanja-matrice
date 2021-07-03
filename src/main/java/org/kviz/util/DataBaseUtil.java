package org.kviz.util;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    /**
     * @param args the command line arguments
     */
    public void connect(String[] args) {
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
