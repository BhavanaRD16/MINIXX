import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
private static final String DB_URL = "jdbc:sqlite:C:\\Users\\bhava\\Desktop\\MINIXX\\student_activity_system.db";
    // Initialize database (create tables)
    public static void initializeDatabase() {
        String[] createTables = {
            """
            CREATE TABLE IF NOT EXISTS students (
                roll_no TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                department TEXT NOT NULL,
                date_of_birth TEXT NOT NULL,
                gender TEXT NOT NULL,
                blood_group TEXT NOT NULL,
                cgpa REAL NOT NULL,
                password TEXT NOT NULL,
                class_name TEXT NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS teachers (
                teacher_id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                department TEXT NOT NULL,
                class_name TEXT NOT NULL,
                password TEXT NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS activities (
                activity_id TEXT PRIMARY KEY,
                student_roll_no TEXT NOT NULL,
                category TEXT NOT NULL,
                type TEXT NOT NULL,
                activity_name TEXT NOT NULL,
                activity_date TEXT NOT NULL,
                participation_type TEXT NOT NULL,
                sap_points INTEGER NOT NULL,
                status TEXT NOT NULL,
                verified INTEGER NOT NULL DEFAULT 0,
                FOREIGN KEY (student_roll_no) REFERENCES students (roll_no)
            )
            """
        };

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("✅ Connected to database successfully!");
            for (String sql : createTables) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                }
            }
            System.out.println("✅ Tables created successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }
    }

    // ✅ Equivalent to old executeSQL()
    public static boolean executeSQL(String sql) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("❌ SQL execution failed: " + e.getMessage());
            return false;
        }
    }

    // ✅ Equivalent to old executeQuery()
    public static ArrayList<String> executeQuery(String sql) {
        ArrayList<String> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Add column headers first (like the old -csv -header style)
            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                header.append(meta.getColumnName(i));
                if (i < columnCount) header.append(",");
            }
            results.add(header.toString());

            // Add rows
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i));
                    if (i < columnCount) row.append(",");
                }
                results.add(row.toString());
            }
        } catch (SQLException e) {
            System.out.println("❌ Query failed: " + e.getMessage());
        }
        return results;
    }

    public static void main(String[] args) {
        initializeDatabase();
    }
}
