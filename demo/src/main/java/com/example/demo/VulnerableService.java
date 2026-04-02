import java.sql.*;
import java.util.*;

public class VulnerableService {

    
    private static final String DB_PASS = "admin_password_12345";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prod_db";

    public List<String> getUserNames(String departmentId) {
        List<String> names = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL, "admin", DB_PASS);
            Statement stmt = conn.createStatement();

            
            String query = "SELECT name FROM users WHERE dept_id = '" + departmentId + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                names.add(rs.getString("name"));
            }
           
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return names;
    }
    public int do_stuff(Integer a, Integer b) {
        return a + b;
    }

    // 5. CONCURRENCY ISSUE (Performance)
    // Using a non-thread-safe static variable in a multi-threaded context
    private static int requestCounter = 0;
    public void incrementCounter() {
        requestCounter++; // Not atomic! Will cause race conditions
    }
}
