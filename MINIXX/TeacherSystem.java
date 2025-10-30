import java.util.ArrayList;

public class TeacherSystem {
    private Teacher teacher;
    private ArrayList<Student> allStudents;

    public TeacherSystem(Teacher teacher, ArrayList<Student> allStudents) {
        this.teacher = teacher;
        this.allStudents = allStudents;
    }

    public void showTeacherMenu() {
        while (true) {
            Utility.clearScreen();
            System.out.println("================= TEACHER MENU =================");
            System.out.println("Welcome, " + teacher.getName() + "!");
            System.out.println("Class: " + teacher.getClassName());
            System.out.println("=============================================");
            System.out.println("1. View My Class Students");
            System.out.println("2. View Pending Activities");
            System.out.println("3. Approve/Reject Activities");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.println("=============================================");

            String choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case "1":
                    viewMyClassStudents();
                    break;
                case "2":
                    viewPendingActivities();
                    break;
                case "3":
                    approveRejectActivities();
                    break;
                case "4":
                    changePassword();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            Utility.pressAnyKeyToContinue();
        }
    }

    private void viewMyClassStudents() {
        System.out.println("============= MY CLASS STUDENTS =============");
        boolean found = false;
        
        for (Student student : allStudents) {
            if (student.getClassName().equals(teacher.getClassName())) {
                student.displayStudent();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found in your class!");
            System.out.println("Please ask admin to add students to class: " + teacher.getClassName());
        }
    }

    private void viewPendingActivities() {
        System.out.println("============= PENDING ACTIVITIES =============");
        boolean found = false;
        
        for (Student student : allStudents) {
            if (student.getClassName().equals(teacher.getClassName())) {
                for (Activity activity : student.getActivities()) {
                    if ("Pending".equals(activity.getStatus())) {
                        activity.displayActivity();
                        System.out.println("Student: " + student.getName() + " (" + student.getRollNo() + ")");
                        System.out.println();
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No pending activities found!");
        }
    }

    private void approveRejectActivities() {
        viewPendingActivities();
        
        String activityId = Utility.getInput("Enter Activity ID to approve/reject: ");
        Activity foundActivity = null;
        Student activityStudent = null;

        for (Student student : allStudents) {
            if (student.getClassName().equals(teacher.getClassName())) {
                for (Activity activity : student.getActivities()) {
                    if (activity.getActivityId().equals(activityId) && "Pending".equals(activity.getStatus())) {
                        foundActivity = activity;
                        activityStudent = student;
                        break;
                    }
                }
            }
            if (foundActivity != null) break;
        }

        if (foundActivity == null) {
            System.out.println("Activity not found or already processed!");
            return;
        }

        System.out.println("1. Approve");
        System.out.println("2. Reject");
        String action = Utility.getInput("Choose action: ");

        if ("1".equals(action)) {
            foundActivity.setStatus("Approved");
            foundActivity.setVerified(true);
        } else if ("2".equals(action)) {
            foundActivity.setStatus("Rejected");
            foundActivity.setVerified(true);
        } else {
            System.out.println("Invalid action!");
            return;
        }

        // Update in database
        if (foundActivity.updateInDatabase()) {
            System.out.println("Activity " + ("1".equals(action) ? "approved" : "rejected") + " successfully!");
        } else {
            System.out.println("Failed to update activity in database!");
        }
    }

    private void changePassword() {
        String currentPassword = Utility.getInput("Enter current password: ");
        if (!currentPassword.equals(teacher.getPassword())) {
            System.out.println("Current password is incorrect!");
            return;
        }

        String newPassword = Utility.getInput("Enter new password: ");
        String confirmPassword = Utility.getInput("Confirm new password: ");

        if (newPassword.equals(confirmPassword)) {
            teacher.setPassword(newPassword);
            // Update password in database
            if (teacher.saveToDatabase()) { // Using saveToDatabase which does INSERT OR REPLACE
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Password changed but failed to update in database!");
            }
        } else {
            System.out.println("Passwords do not match!");
        }
    }
}