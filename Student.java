import java.util.ArrayList;

public class Student {
    private String rollNo;
    private String name;
    private String department;
    private String dateOfBirth;
    private String gender;
    private String bloodGroup;
    private double cgpa;
    private String password;
    private String className;
    private ArrayList<Activity> activities;

    public Student(String rollNo, String name, String department, String dateOfBirth,
                   String gender, String bloodGroup, double cgpa, String className) {
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.cgpa = cgpa;
        this.password = "student";
        this.className = className;
        this.activities = new ArrayList<>();
    }

    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getBloodGroup() { return bloodGroup; }
    public double getCgpa() { return cgpa; }
    public String getPassword() { return password; }
    public String getClassName() { return className; }
    public ArrayList<Activity> getActivities() { return activities; }

    public void setPassword(String password) { this.password = password; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public boolean removeActivity(String activityId) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getActivityId().equals(activityId)) {
                activities.remove(i);
                return true;
            }
        }
        return false;
    }

    public Activity findActivity(String activityId) {
        for (Activity activity : activities) {
            if (activity.getActivityId().equals(activityId)) {
                return activity;
            }
        }
        return null;
    }

    public int getTotalSAP() {
        int total = 0;
        for (Activity activity : activities) {
            if ("Approved".equals(activity.getStatus())) {
                total += activity.getSapPoints();
            }
        }
        return total;
    }

    public void displayStudent() {
        System.out.println("================= STUDENT DETAILS ===============");
        System.out.printf("%-20s : %s\n", "Roll No", rollNo);
        System.out.printf("%-20s : %s\n", "Name", name);
        System.out.printf("%-20s : %s\n", "Department", department);
        System.out.printf("%-20s : %s\n", "Class", className);
        System.out.printf("%-20s : %s\n", "Date of Birth", dateOfBirth);
        System.out.printf("%-20s : %s\n", "Gender", gender);
        System.out.printf("%-20s : %s\n", "Blood Group", bloodGroup);
        System.out.printf("%-20s : %.2f\n", "CGPA", cgpa);
        System.out.printf("%-20s : %d\n", "Total SAP Points", getTotalSAP());
        System.out.println("=================================================");
    }
    // Add these methods to your existing Student class
public boolean saveToDatabase() {
    String sql = String.format(
        "INSERT INTO students VALUES('%s', '%s', '%s', '%s', '%s', '%s', %.2f, '%s', '%s')",
        this.rollNo.replace("'", "''"),
        this.name.replace("'", "''"),
        this.department.replace("'", "''"),
        this.dateOfBirth.replace("'", "''"),
        this.gender.replace("'", "''"),
        this.bloodGroup.replace("'", "''"),
        this.cgpa,
        this.password.replace("'", "''"),
        this.className.replace("'", "''")
    );
    
    return DatabaseManager.executeSQL(sql);
}

public static Student loadFromDatabase(String rollNo) {
    String sql = String.format("SELECT * FROM students WHERE roll_no = '%s'", rollNo.replace("'", "''"));
    ArrayList<String> results = DatabaseManager.executeQuery(sql);
    
    if (results.size() > 1) {
        String[] data = results.get(1).split(",");
        if (data.length >= 9) {
            return new Student(
                data[0], // roll_no
                data[1], // name
                data[2], // department
                data[3], // date_of_birth
                data[4], // gender
                data[5], // blood_group
                Double.parseDouble(data[6]), // cgpa
                data[8]  // class_name
            );
        }
    }
    return null;
}

public static ArrayList<Student> loadAllFromDatabase() {
    ArrayList<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM students";
    ArrayList<String> results = DatabaseManager.executeQuery(sql);
    
    for (int i = 1; i < results.size(); i++) {
        String[] data = results.get(i).split(",");
        if (data.length >= 9) {
            Student student = new Student(
                data[0], // roll_no
                data[1], // name
                data[2], // department
                data[3], // date_of_birth
                data[4], // gender
                data[5], // blood_group
                Double.parseDouble(data[6]), // cgpa
                data[8]  // class_name
            );
            student.setPassword(data[7]); // password
            students.add(student);
        }
    }
    return students;
}

public boolean updateInDatabase() {
    String sql = String.format(
        "UPDATE students SET name='%s', department='%s', date_of_birth='%s', gender='%s', blood_group='%s', cgpa=%.2f, password='%s', class_name='%s' WHERE roll_no='%s'",
        this.name.replace("'", "''"),
        this.department.replace("'", "''"),
        this.dateOfBirth.replace("'", "''"),
        this.gender.replace("'", "''"),
        this.bloodGroup.replace("'", "''"),
        this.cgpa,
        this.password.replace("'", "''"),
        this.className.replace("'", "''"),
        this.rollNo.replace("'", "''")
    );
    
    return DatabaseManager.executeSQL(sql);
}

public boolean deleteFromDatabase() {
    String sql = String.format("DELETE FROM students WHERE roll_no = '%s'", this.rollNo.replace("'", "''"));
    return DatabaseManager.executeSQL(sql);
}
}