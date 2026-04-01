import java.sql.*;
import java.util.*;

public class VulnerableService {

    // 1. HARDCODED CREDENTIALS (Critical Security)
    private static final String DB_PASS = "admin_password_12345";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prod_db";

    public List<String> getUserNames(String departmentId) {
        List<String> names = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL, "admin", DB_PASS);
            Statement stmt = conn.createStatement();

            // 2. SQL INJECTION (Critical Security)
            // Using string concatenation instead of PreparedStatement
            String query = "SELECT name FROM users WHERE dept_id = '" + departmentId + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                names.add(rs.getString("name"));
            }
            // 3. RESOURCE LEAK (Performance/Best Practice)
            // Connection, Statement, and ResultSet are never closed!
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return names;
    }

    // 4. POTENTIAL NULL POINTER & BAD NAMING (Best Practice)
    public int do_stuff(Integer a, Integer b) {
        // Unboxing 'a' or 'b' here will crash if they are null
        return a + b;
    }

    // 5. CONCURRENCY ISSUE (Performance)
    // Using a non-thread-safe static variable in a multi-threaded context
    private static int requestCounter = 0;
    public void incrementCounter() {
        requestCounter++; // Not atomic! Will cause race conditions
    }
}
