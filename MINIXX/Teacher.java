import java.util.ArrayList;

public class Teacher {
    private String teacherId;
    private String name;
    private String department;
    private String className;
    private String password;

    public Teacher(String teacherId, String name, String department, String className) {
        this.teacherId = teacherId;
        this.name = name;
        this.department = department;
        this.className = className;
        this.password = "teacher";
    }

    public String getTeacherId() { return teacherId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getClassName() { return className; }
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void displayTeacher() {
        System.out.println("================= TEACHER DETAILS ===============");
        System.out.printf("%-20s : %s\n", "Teacher ID", teacherId);
        System.out.printf("%-20s : %s\n", "Name", name);
        System.out.printf("%-20s : %s\n", "Department", department);
        System.out.printf("%-20s : %s\n", "Class", className);
        System.out.println("=================================================");
    }
    // Add these methods to your existing Teacher class
public boolean saveToDatabase() {
    String sql = String.format(
        "INSERT INTO teachers VALUES('%s', '%s', '%s', '%s', '%s')",
        this.teacherId.replace("'", "''"),
        this.name.replace("'", "''"),
        this.department.replace("'", "''"),
        this.className.replace("'", "''"),
        this.password.replace("'", "''")
    );
    
    return DatabaseManager.executeSQL(sql);
}

public static Teacher loadFromDatabase(String teacherId) {
    String sql = String.format("SELECT * FROM teachers WHERE teacher_id = '%s'", teacherId.replace("'", "''"));
    ArrayList<String> results = DatabaseManager.executeQuery(sql);
    
    if (results.size() > 1) {
        String[] data = results.get(1).split(",");
        if (data.length >= 5) {
            Teacher teacher = new Teacher(
                data[0], // teacher_id
                data[1], // name
                data[2], // department
                data[3]  // class_name
            );
            teacher.setPassword(data[4]); // password
            return teacher;
        }
    }
    return null;
}

public static ArrayList<Teacher> loadAllFromDatabase() {
    ArrayList<Teacher> teachers = new ArrayList<>();
    String sql = "SELECT * FROM teachers";
    ArrayList<String> results = DatabaseManager.executeQuery(sql);
    
    for (int i = 1; i < results.size(); i++) {
        String[] data = results.get(i).split(",");
        if (data.length >= 5) {
            Teacher teacher = new Teacher(
                data[0], // teacher_id
                data[1], // name
                data[2], // department
                data[3]  // class_name
            );
            teacher.setPassword(data[4]); // password
            teachers.add(teacher);
        }
    }
    return teachers;
}
}