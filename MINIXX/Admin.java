import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public Admin(ArrayList<Student> students, ArrayList<Teacher> teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    public void showAdminMenu() {
        while (true) {
            Utility.clearScreen();
            System.out.println("================= ADMIN MENU =================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. View All Students");
            System.out.println("4. View All Teachers");
            System.out.println("5. Sort Students by SAP");
            System.out.println("6. Sort Students by CGPA");
            System.out.println("7. Sort Students by Department");
            System.out.println("8. Back to Main Menu");
            System.out.println("=============================================");

            String choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    addTeacher();
                    break;
                case "3":
                    viewAllStudents();
                    break;
                case "4":
                    viewAllTeachers();
                    break;
                case "5":
                    sortStudentsBySAP();
                    break;
                case "6":
                    sortStudentsByCGPA();
                    break;
                case "7":
                    sortStudentsByDepartment();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            Utility.pressAnyKeyToContinue();
        }
    }

    private void addStudent() {
        System.out.println("================= ADD STUDENT =================");
        
        String rollNo = Utility.getInput("Enter Roll No: ");
        for (Student student : students) {
            if (student.getRollNo().equals(rollNo)) {
                System.out.println("Student with this Roll No already exists!");
                return;
            }
        }

        String name = Utility.getInput("Enter Name: ");
        
        String department;
        while (true) {
            Utility.displayAllDepartments();
            department = Utility.getInput("Enter Department: ");
            if (Utility.isValidDepartment(department)) {
                department = Utility.formatDepartment(department);
                break;
            } else {
                System.out.println("Enter valid department!");
            }
        }

        String dateOfBirth;
        while (true) {
            dateOfBirth = Utility.getInput("Enter Date of Birth (dd-MM-yyyy): ");
            if (Utility.isValidDate(dateOfBirth)) {
                break;
            } else {
                System.out.println("Invalid date format. Please enter in dd-MM-yyyy.");
            }
        }

        String gender = Utility.getInput("Enter Gender: ");
        String bloodGroup = Utility.getInput("Enter Blood Group: ");
        
        double cgpa;
        while (true) {
            try {
                cgpa = Double.parseDouble(Utility.getInput("Enter CGPA: "));
                if (Utility.isValidCGPA(cgpa)) {
                    break;
                } else {
                    System.out.println("CGPA must be between 0.0 and 10.0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for CGPA!");
            }
        }

        String className = Utility.getInput("Enter Class (e.g., CSE A): ");

        Student student = new Student(rollNo, name, department, dateOfBirth, gender, bloodGroup, cgpa, className);
        
        // Save to database
        if (student.saveToDatabase()) {
            students.add(student);
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student to database!");
        }
    }

    private void addTeacher() {
        System.out.println("================= ADD TEACHER =================");
        
        String teacherId = Utility.getInput("Enter Teacher ID: ");
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                System.out.println("Teacher with this ID already exists!");
                return;
            }
        }

        String name = Utility.getInput("Enter Name: ");
        
        String department;
        while (true) {
            Utility.displayAllDepartments();
            department = Utility.getInput("Enter Department: ");
            if (Utility.isValidDepartment(department)) {
                department = Utility.formatDepartment(department);
                break;
            } else {
                System.out.println("Enter valid department!");
            }
        }

        String className = Utility.getInput("Enter Class (e.g., CSE A): ");

        Teacher teacher = new Teacher(teacherId, name, department, className);
        
        // Save to database
        if (teacher.saveToDatabase()) {
            teachers.add(teacher);
            System.out.println("Teacher added successfully!");
        } else {
            System.out.println("Failed to add teacher to database!");
        }
    }

    private void viewAllStudents() {
        System.out.println("================= ALL STUDENTS =================");
        if (students.isEmpty()) {
            System.out.println("No students found! Please add students first.");
            return;
        }

        for (Student student : students) {
            student.displayStudent();
            System.out.println();
        }
    }

    private void viewAllTeachers() {
        System.out.println("================= ALL TEACHERS =================");
        if (teachers.isEmpty()) {
            System.out.println("No teachers found! Please add teachers first.");
            return;
        }

        for (Teacher teacher : teachers) {
            teacher.displayTeacher();
            System.out.println();
        }
    }

    private void sortStudentsBySAP() {
        if (students.isEmpty()) {
            System.out.println("No students found! Please add students first.");
            return;
        }

        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s2.getTotalSAP(), s1.getTotalSAP());
            }
        });

        System.out.println("============= STUDENTS SORTED BY SAP =============");
        for (Student student : sortedStudents) {
            System.out.printf("%-15s : %-20s : %d SAP Points\n", 
                student.getRollNo(), student.getName(), student.getTotalSAP());
        }
    }

    private void sortStudentsByCGPA() {
        if (students.isEmpty()) {
            System.out.println("No students found! Please add students first.");
            return;
        }

        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getCgpa(), s1.getCgpa());
            }
        });

        System.out.println("============= STUDENTS SORTED BY CGPA =============");
        for (Student student : sortedStudents) {
            System.out.printf("%-15s : %-20s : %.2f CGPA\n", 
                student.getRollNo(), student.getName(), student.getCgpa());
        }
    }

    private void sortStudentsByDepartment() {
        if (students.isEmpty()) {
            System.out.println("No students found! Please add students first.");
            return;
        }

        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int deptCompare = s1.getDepartment().compareTo(s2.getDepartment());
                if (deptCompare != 0) return deptCompare;
                return s1.getRollNo().compareTo(s2.getRollNo());
            }
        });

        System.out.println("=========== STUDENTS SORTED BY DEPARTMENT ===========");
        String currentDept = "";
        for (Student student : sortedStudents) {
            if (!student.getDepartment().equals(currentDept)) {
                currentDept = student.getDepartment();
                System.out.println("\n--- " + currentDept + " ---");
            }
            System.out.printf("%-15s : %-20s : %s\n", 
                student.getRollNo(), student.getName(), student.getClassName());
        }
    }
}