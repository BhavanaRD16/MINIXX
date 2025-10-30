import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static boolean isValidDepartment(String department) {
        String[] validDepartments = {
            "CSE", "IT", "ECE", "EEE", "MECH", "CIVIL", 
            "MTS", "AUTO", "CHEM", "AI&DS", "AI&ML", "FT"
        };
        for (String dept : validDepartments) {
            if (dept.equalsIgnoreCase(department)) {
                return true;
            }
        }
        return false;
    }

    public static String formatDepartment(String department) {
        if (department.equalsIgnoreCase("AI&DS")) return "AI&DS";
        if (department.equalsIgnoreCase("AI&ML")) return "AI&ML";
        return department.toUpperCase();
    }

    public static void displayAllDepartments() {
        System.out.println("Available Departments:");
        System.out.println("CSE, IT, ECE, EEE, MECH, CIVIL, MTS, AUTO, CHEM, AI&DS, AI&ML, FT");
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidCGPA(double cgpa) {
        return cgpa >= 0.0 && cgpa <= 10.0;
    }

    public static String generateActivityId(String rollNo, int count) {
        return rollNo + "-ACT-" + (count + 1);
    }

    public static void pressAnyKeyToContinue() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}