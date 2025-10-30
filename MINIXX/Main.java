import java.util.ArrayList;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize database using system SQLite
        DatabaseManager.initializeDatabase();
        
        // Load existing data from database
        loadDataFromDatabase();
        
        while (true) {
            Utility.clearScreen();
            System.out.println("=============================================");
            System.out.println("    STUDENT ACTIVITY RECORD SYSTEM");
            System.out.println("=============================================");
            System.out.println("1. Admin");
            System.out.println("2. Teacher Login");
            System.out.println("3. Student Login");
            System.out.println("4. Exit");
            System.out.println("=============================================");

            String choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case "1":
                    adminLogin();
                    break;
                case "2":
                    teacherLogin();
                    break;
                case "3":
                    studentLogin();
                    break;
                case "4":
                    System.out.println("Thank you for using the system!");
                    System.out.println("Database file: student_activity_system.db");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void loadDataFromDatabase() {
        // Load students from database
        students = Student.loadAllFromDatabase();
        
        // Load teachers from database
        teachers = Teacher.loadAllFromDatabase();
        
        // Load activities for each student
        for (Student student : students) {
            ArrayList<Activity> activities = Activity.loadActivitiesForStudent(student.getRollNo());
            for (Activity activity : activities) {
                student.addActivity(activity);
            }
        }
        
        System.out.println("Data loaded from database successfully!");
        System.out.println("Students: " + students.size());
        System.out.println("Teachers: " + teachers.size());
    }

    private static void adminLogin() {
        Admin admin = new Admin(students, teachers);
        admin.showAdminMenu();
    }

    private static void teacherLogin() {
        String teacherId = Utility.getInput("Enter Teacher ID: ");
        String password = Utility.getInput("Enter Password: ");

        Teacher teacher = findTeacher(teacherId);
        if (teacher != null && teacher.getPassword().equals(password)) {
            TeacherSystem teacherSystem = new TeacherSystem(teacher, students);
            teacherSystem.showTeacherMenu();
        } else {
            System.out.println("Invalid Teacher ID or Password!");
        }
    }

    private static void studentLogin() {
        String rollNo = Utility.getInput("Enter Roll No: ");
        String password = Utility.getInput("Enter Password: ");

        Student student = findStudent(rollNo);
        if (student != null && student.getPassword().equals(password)) {
            StudentSystem studentSystem = new StudentSystem(student, students, teachers);
            studentSystem.showStudentMenu();
        } else {
            System.out.println("Invalid Roll No or Password!");
        }
    }

    private static Student findStudent(String rollNo) {
        for (Student student : students) {
            if (student.getRollNo().equals(rollNo)) {
                return student;
            }
        }
        return null;
    }

    private static Teacher findTeacher(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                return teacher;
            }
        }
        return null;
    }
}