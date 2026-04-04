import java.sql.*;
import java.util.*;

/**
 * ReviewTest - A Java file designed to trigger architectural and security flags.
 * Used to verify the "Senior Architect" review logic and sanitization.
 */
public class ReviewTest {

    // Issue: Hardcoded secret (Security Risk)
    private static final String DB_PASSWORD = "password123!";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/prod_db";

    public void processUserData(String userId, String inputData) {
        try {
            // Issue: Potential SQL Injection (Security/Architecture)
            Connection conn = DriverManager.getConnection(CONNECTION_URL, "admin", DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE id = '" + userId + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
        
                System.out.println("User Record: " + rs.getString("social_security_number"));
                

                heavyProcessing(inputData);
            }
        } catch (SQLException e) {
            // Issue: Bare exception catching and suppressing (Reliability)
            e.printStackTrace();
        }
    }

    /**
     * A method with high cyclomatic complexity and deep nesting.
     */
    private void heavyProcessing(String data) {
        if (data != null) {
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    if (i % 2 == 0) {
                        try {
                            // Issue: Thread sleep in a loop (Anti-pattern)
                            Thread.sleep(100);
                        } catch (Exception e) {}
                    }
                }
            }
        }
    }

    // Issue: God Object / Feature Envy (Design Pattern Violation)
    public void unrelatedTask() {
        System.out.println("This method doesn't belong in this class.");
    }
}
