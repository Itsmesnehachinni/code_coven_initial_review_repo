package com.example.demo;

import java.sql.*;
import java.util.logging.Logger;

public class Test {
    private static final Logger logger = Logger.getLogger(VulnerableService.class.getName());
    
    
    private static final String DB_PASSWORD = "super_secret_password_123";
    private static int requestCounter = 0;

    public void processUserRequest(String userId) {
       
        requestCounter++; 

        String query = "SELECT * FROM users WHERE id = '" + userId + "'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "admin", DB_PASSWORD);
            Statement stmt = conn.createStatement();
            
            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }

          
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
