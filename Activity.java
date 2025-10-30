import java.util.ArrayList;

public class Activity {
    private String activityId;
    private String studentRollNo;
    private String category;
    private String type;
    private String activityName;
    private String activityDate;
    private String participationType;
    private int sapPoints;
    private String status;
    private boolean verified;

    public Activity(String activityId, String studentRollNo, String category, String type, 
                   String activityName, String activityDate, String participationType, int sapPoints) {
        this.activityId = activityId;
        this.studentRollNo = studentRollNo;
        this.category = category;
        this.type = type;
        this.activityName = activityName;
        this.activityDate = activityDate;
        this.participationType = participationType;
        this.sapPoints = sapPoints;
        this.status = "Pending";
        this.verified = false;
    }

    public String getActivityId() { return activityId; }
    public String getStudentRollNo() { return studentRollNo; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public String getActivityName() { return activityName; }
    public String getActivityDate() { return activityDate; }
    public String getParticipationType() { return participationType; }
    public int getSapPoints() { return sapPoints; }
    public String getStatus() { return status; }
    public boolean isVerified() { return verified; }

    public void setCategory(String category) { this.category = category; }
    public void setType(String type) { this.type = type; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    public void setActivityDate(String activityDate) { this.activityDate = activityDate; }
    public void setParticipationType(String participationType) { this.participationType = participationType; }
    public void setSapPoints(int sapPoints) { this.sapPoints = sapPoints; }
    public void setStatus(String status) { this.status = status; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public void displayActivity() {
        System.out.println("================= ACTIVITY DETAILS =================");
        System.out.printf("%-20s : %s\n", "Activity ID", activityId);
        System.out.printf("%-20s : %s\n", "Category", category);
        System.out.printf("%-20s : %s\n", "Type", type);
        System.out.printf("%-20s : %s\n", "Activity Name", activityName);
        System.out.printf("%-20s : %s\n", "Date", activityDate);
        System.out.printf("%-20s : %s\n", "Participation Type", participationType);
        System.out.printf("%-20s : %d\n", "SAP Points", sapPoints);
        System.out.printf("%-20s : %s\n", "Status", status);
        System.out.println("====================================================");
    }
    // Add these methods to your existing Activity class
public boolean saveToDatabase() {
    String sql = String.format(
        "INSERT INTO activities VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, '%s', %d)",
        this.activityId.replace("'", "''"),
        this.studentRollNo.replace("'", "''"),
        this.category.replace("'", "''"),
        this.type.replace("'", "''"),
        this.activityName.replace("'", "''"),
        this.activityDate.replace("'", "''"),
        this.participationType.replace("'", "''"),
        this.sapPoints,
        this.status.replace("'", "''"),
        this.verified ? 1 : 0
    );
    
    return DatabaseManager.executeSQL(sql);
}

public static ArrayList<Activity> loadActivitiesForStudent(String studentRollNo) {
    ArrayList<Activity> activities = new ArrayList<>();
    String sql = String.format("SELECT * FROM activities WHERE student_roll_no = '%s'", studentRollNo.replace("'", "''"));
    ArrayList<String> results = DatabaseManager.executeQuery(sql);
    
    for (int i = 1; i < results.size(); i++) {
        String[] data = results.get(i).split(",");
        if (data.length >= 10) {
            Activity activity = new Activity(
                data[0], // activity_id
                data[1], // student_roll_no
                data[2], // category
                data[3], // type
                data[4], // activity_name
                data[5], // activity_date
                data[6], // participation_type
                Integer.parseInt(data[7]) // sap_points
            );
            activity.setStatus(data[8]); // status
            activity.setVerified(data[9].equals("1")); // verified
            activities.add(activity);
        }
    }
    return activities;
}

public boolean updateInDatabase() {
    String sql = String.format(
        "UPDATE activities SET category='%s', type='%s', activity_name='%s', activity_date='%s', participation_type='%s', sap_points=%d, status='%s', verified=%d WHERE activity_id='%s'",
        this.category.replace("'", "''"),
        this.type.replace("'", "''"),
        this.activityName.replace("'", "''"),
        this.activityDate.replace("'", "''"),
        this.participationType.replace("'", "''"),
        this.sapPoints,
        this.status.replace("'", "''"),
        this.verified ? 1 : 0,
        this.activityId.replace("'", "''")
    );
    
    return DatabaseManager.executeSQL(sql);
}

public boolean deleteFromDatabase() {
    String sql = String.format("DELETE FROM activities WHERE activity_id = '%s'", this.activityId.replace("'", "''"));
    return DatabaseManager.executeSQL(sql);
}
}