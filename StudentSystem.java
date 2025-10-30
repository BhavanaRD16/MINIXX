import java.util.ArrayList;

public class StudentSystem {
    private Student student;
    private ArrayList<Student> allStudents;
    private ArrayList<Teacher> teachers;

    public StudentSystem(Student student, ArrayList<Student> allStudents, ArrayList<Teacher> teachers) {
        this.student = student;
        this.allStudents = allStudents;
        this.teachers = teachers;
    }

    public void showStudentMenu() {
        while (true) {
            Utility.clearScreen();
            System.out.println("================= STUDENT MENU =================");
            System.out.println("Welcome, " + student.getName() + "!");
            System.out.println("Roll No: " + student.getRollNo());
            System.out.println("Total SAP Points: " + student.getTotalSAP());
            System.out.println("=============================================");
            System.out.println("1. Add Activity");
            System.out.println("2. View My Activities");
            System.out.println("3. Update Activity");
            System.out.println("4. Delete Activity");
            System.out.println("5. View My Profile");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.println("=============================================");

            String choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case "1":
                    addActivity();
                    break;
                case "2":
                    viewMyActivities();
                    break;
                case "3":
                    updateActivity();
                    break;
                case "4":
                    deleteActivity();
                    break;
                case "5":
                    viewMyProfile();
                    break;
                case "6":
                    changePassword();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            Utility.pressAnyKeyToContinue();
        }
    }

    private void addActivity() {
        System.out.println("================= ADD ACTIVITY =================");
        
        SAPPoints.displayAvailableCategories();
        String category = Utility.getInput("Enter Category: ");
        
        SAPPoints.displayTypesForCategory(category);
        String type = Utility.getInput("Enter Activity Type: ");
        
        SAPPoints.displayParticipationTypes();
        String participationType;
        while (true) {
            try {
                int choice = Integer.parseInt(Utility.getInput("Choose Participation Type (1-3): "));
                if (choice >= 1 && choice <= 3) {
                    participationType = SAPPoints.getParticipationTypeByNumber(choice);
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        
        String activityName = Utility.getInput("Enter Activity Name: ");
        
        String activityDate;
        while (true) {
            activityDate = Utility.getInput("Enter Activity Date (dd-MM-yyyy): ");
            if (Utility.isValidDate(activityDate)) {
                break;
            } else {
                System.out.println("Invalid date format. Please enter in dd-MM-yyyy.");
            }
        }

        int sapPoints = SAPPoints.getSAPPoints(category, type, participationType);
        if (sapPoints == 0) {
            System.out.println("Invalid category/type combination! Activity not added.");
            return;
        }

        int activityCount = student.getActivities().size();
        String activityId = Utility.generateActivityId(student.getRollNo(), activityCount);

        Activity activity = new Activity(activityId, student.getRollNo(), category, type, activityName, activityDate, participationType, sapPoints);
        
        // Save to database
        if (activity.saveToDatabase()) {
            student.addActivity(activity);
            System.out.println("Activity added successfully!");
            System.out.println("SAP Points for this activity: " + sapPoints);
        } else {
            System.out.println("Failed to add activity to database!");
        }
    }

    private void viewMyActivities() {
        ArrayList<Activity> activities = student.getActivities();
        System.out.println("================= MY ACTIVITIES =================");
        
        if (activities.isEmpty()) {
            System.out.println("No activities found!");
            return;
        }

        for (Activity activity : activities) {
            activity.displayActivity();
            System.out.println();
        }
    }

    private void updateActivity() {
        viewMyActivities();
        if (student.getActivities().isEmpty()) return;

        String activityId = Utility.getInput("Enter Activity ID to update: ");
        Activity activity = student.findActivity(activityId);
        
        if (activity == null) {
            System.out.println("Activity not found!");
            return;
        }

        if (activity.isVerified()) {
            System.out.println("Cannot update verified activity!");
            return;
        }

        System.out.println("Leave field blank to keep current value:");
        
        String newCategory = Utility.getInput("New Category [" + activity.getCategory() + "]: ");
        if (!newCategory.isEmpty()) activity.setCategory(newCategory);
        
        String newType = Utility.getInput("New Type [" + activity.getType() + "]: ");
        if (!newType.isEmpty()) activity.setType(newType);
        
        String newActivityName = Utility.getInput("New Activity Name [" + activity.getActivityName() + "]: ");
        if (!newActivityName.isEmpty()) activity.setActivityName(newActivityName);
        
        String newDate = Utility.getInput("New Date (dd-MM-yyyy) [" + activity.getActivityDate() + "]: ");
        if (!newDate.isEmpty()) {
            if (Utility.isValidDate(newDate)) {
                activity.setActivityDate(newDate);
            } else {
                System.out.println("Invalid date format. Date not changed.");
            }
        }

        String newParticipationType = Utility.getInput("New Participation Type [" + activity.getParticipationType() + "]: ");
        if (!newParticipationType.isEmpty()) {
            activity.setParticipationType(newParticipationType);
            int newSapPoints = SAPPoints.getSAPPoints(activity.getCategory(), activity.getType(), activity.getParticipationType());
            activity.setSapPoints(newSapPoints);
        }

        // Update in database
        if (activity.updateInDatabase()) {
            System.out.println("Activity updated successfully!");
        } else {
            System.out.println("Failed to update activity in database!");
        }
    }

    private void deleteActivity() {
        viewMyActivities();
        if (student.getActivities().isEmpty()) return;

        String activityId = Utility.getInput("Enter Activity ID to delete: ");
        Activity activity = student.findActivity(activityId);
        
        if (activity == null) {
            System.out.println("Activity not found!");
            return;
        }

        if (activity.isVerified()) {
            System.out.println("Cannot delete verified activity!");
            return;
        }

        if (student.removeActivity(activityId) && activity.deleteFromDatabase()) {
            System.out.println("Activity deleted successfully!");
        } else {
            System.out.println("Failed to delete activity from database!");
        }
    }

    private void viewMyProfile() {
        student.displayStudent();
    }

    private void changePassword() {
        String currentPassword = Utility.getInput("Enter current password: ");
        if (!currentPassword.equals(student.getPassword())) {
            System.out.println("Current password is incorrect!");
            return;
        }

        String newPassword = Utility.getInput("Enter new password: ");
        String confirmPassword = Utility.getInput("Confirm new password: ");

        if (newPassword.equals(confirmPassword)) {
            student.setPassword(newPassword);
            // Update password in database
            if (student.updateInDatabase()) {
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Password changed but failed to update in database!");
            }
        } else {
            System.out.println("Passwords do not match!");
        }
    }
}