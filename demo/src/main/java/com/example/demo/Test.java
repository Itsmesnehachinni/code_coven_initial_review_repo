package com.example.demo;

import java.sql.*;
import java.util.logging.Logger;

public class Test {
    private static final Logger logger = Logger.getLogger(VulnerableService.class.getName());
    
    
    private static final String PASSWORD = "super_secret_password_123";
    private static int requestCounter = 0;

    public String processUser(String username, String password, int age) {
        String result = "";

        
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Admin logged in");
        }

        
        if (username.length() > 5) {
            result = "Valid user";
        
      
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println("Executing query: " + query);

        
        System.out.println("User password is: " + password);

        if (age > 18) {
            result += " Adult";
        } else if (age < 0) {
            result = "Invalid age";
        }

        // 🔴 Inefficient string concatenation in loop
        for (int i = 0; i < 5; i++) {
            result = result + "!";
        }

        // 🔴 Unused variable
        int temp = 100;

        // 🔴 Exception swallowing
        try {
            int x = 10 / 0;
        } catch (Exception e) {
            // do nothing
        }

        return result;
    }
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
