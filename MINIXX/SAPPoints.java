import java.util.HashMap;

public class SAPPoints {
    private static HashMap<String, HashMap<String, HashMap<String, Integer>>> pointsMap;

    static {
        pointsMap = new HashMap<>();
        
        // 1. Academic Activities
        HashMap<String, HashMap<String, Integer>> academicActivities = new HashMap<>();
        
        HashMap<String, Integer> seminarsWorkshops = new HashMap<>();
        seminarsWorkshops.put("Participation", 5);
        seminarsWorkshops.put("Organizer", 8);
        seminarsWorkshops.put("Winner", 8);
        academicActivities.put("Seminars & Workshops", seminarsWorkshops);

        HashMap<String, Integer> guestLectures = new HashMap<>();
        guestLectures.put("Participation", 3);
        guestLectures.put("Organizer", 5);
        guestLectures.put("Winner", 6);
        academicActivities.put("Guest Lectures", guestLectures);

        HashMap<String, Integer> technicalSymposiums = new HashMap<>();
        technicalSymposiums.put("Participation", 10);
        technicalSymposiums.put("Organizer", 10);
        technicalSymposiums.put("Winner", 10);
        academicActivities.put("Technical Symposiums", technicalSymposiums);

        HashMap<String, Integer> paperPresentations = new HashMap<>();
        paperPresentations.put("Participation", 12);
        paperPresentations.put("Organizer", 14);
        paperPresentations.put("Winner", 12);
        academicActivities.put("Paper Presentations", paperPresentations);

        HashMap<String, Integer> researchConferences = new HashMap<>();
        researchConferences.put("Participation", 8);
        researchConferences.put("Organizer", 8);
        researchConferences.put("Winner", 8);
        academicActivities.put("Research Conferences", researchConferences);

        pointsMap.put("Academic Activities", academicActivities);

        // 2. Co Curricular Activities
        HashMap<String, HashMap<String, Integer>> coCurricularActivities = new HashMap<>();
        
        HashMap<String, Integer> projectExpos = new HashMap<>();
        projectExpos.put("Participation", 8);
        projectExpos.put("Organizer", 10);
        projectExpos.put("Winner", 12);
        coCurricularActivities.put("Project Expos", projectExpos);

        HashMap<String, Integer> hackathons = new HashMap<>();
        hackathons.put("Participation", 4);
        hackathons.put("Organizer", 6);
        hackathons.put("Winner", 6);
        coCurricularActivities.put("Hackathons", hackathons);

        HashMap<String, Integer> codingQuiz = new HashMap<>();
        codingQuiz.put("Participation", 4);
        codingQuiz.put("Organizer", 6);
        codingQuiz.put("Winner", 6);
        coCurricularActivities.put("Coding/Quiz Competitions", codingQuiz);

        HashMap<String, Integer> departmentFests = new HashMap<>();
        departmentFests.put("Participation", 8);
        departmentFests.put("Organizer", 8);
        departmentFests.put("Winner", 8);
        coCurricularActivities.put("Department Fests", departmentFests);

        pointsMap.put("Co Curricular Activities", coCurricularActivities);

        // 3. Cultural Activities
        HashMap<String, HashMap<String, Integer>> culturalActivities = new HashMap<>();
        
        HashMap<String, Integer> culturalFest = new HashMap<>();
        culturalFest.put("Participation", 6);
        culturalFest.put("Organizer", 8);
        culturalFest.put("Winner", 6);
        culturalActivities.put("Cultural Fest", culturalFest);

        HashMap<String, Integer> danceMusicDrama = new HashMap<>();
        danceMusicDrama.put("Participation", 6);
        danceMusicDrama.put("Organizer", 10);
        danceMusicDrama.put("Winner", 12);
        culturalActivities.put("Dance/Music/Drama Competitions", danceMusicDrama);

        HashMap<String, Integer> fashionShow = new HashMap<>();
        fashionShow.put("Participation", 4);
        fashionShow.put("Organizer", 10);
        fashionShow.put("Winner", 10);
        culturalActivities.put("Fashion Show", fashionShow);

        HashMap<String, Integer> talentHunt = new HashMap<>();
        talentHunt.put("Participation", 5);
        talentHunt.put("Organizer", 8);
        talentHunt.put("Winner", 6);
        culturalActivities.put("Talent Hunt", talentHunt);

        HashMap<String, Integer> artPhotography = new HashMap<>();
        artPhotography.put("Participation", 6);
        artPhotography.put("Organizer", 10);
        artPhotography.put("Winner", 12);
        culturalActivities.put("Art & Photography Exhibitions", artPhotography);

        HashMap<String, Integer> literaryClub = new HashMap<>();
        literaryClub.put("Participation", 5);
        literaryClub.put("Organizer", 6);
        literaryClub.put("Winner", 6);
        culturalActivities.put("Literary Club Activities", literaryClub);

        HashMap<String, Integer> debatePoetryJAM = new HashMap<>();
        debatePoetryJAM.put("Participation", 4);
        debatePoetryJAM.put("Organizer", 10);
        debatePoetryJAM.put("Winner", 10);
        culturalActivities.put("Debate/Poetry/JAM", debatePoetryJAM);

        pointsMap.put("Cultural Activities", culturalActivities);

        // 4. Sports & Fitness Activities
        HashMap<String, HashMap<String, Integer>> sportsActivities = new HashMap<>();
        
        HashMap<String, Integer> annualSports = new HashMap<>();
        annualSports.put("Participation", 6);
        annualSports.put("Organizer", 8);
        annualSports.put("Winner", 8);
        sportsActivities.put("Annual Sports Meet", annualSports);

        HashMap<String, Integer> collegeTournaments = new HashMap<>();
        collegeTournaments.put("Participation", 6);
        collegeTournaments.put("Organizer", 10);
        collegeTournaments.put("Winner", 10);
        sportsActivities.put("College Tournaments", collegeTournaments);

        HashMap<String, Integer> athletics = new HashMap<>();
        athletics.put("Participation", 3);
        athletics.put("Organizer", 6);
        athletics.put("Winner", 8);
        sportsActivities.put("Athletics/Track Activities", athletics);

        pointsMap.put("Sports & Fitness Activities", sportsActivities);

        // 5. Career & Professional Development
        HashMap<String, HashMap<String, Integer>> careerActivities = new HashMap<>();
        
        HashMap<String, Integer> careerSeminars = new HashMap<>();
        careerSeminars.put("Participation", 3);
        careerSeminars.put("Organizer", 8);
        careerSeminars.put("Winner", 6);
        careerActivities.put("Career Guidance Seminars", careerSeminars);

        HashMap<String, Integer> alumniActivities = new HashMap<>();
        alumniActivities.put("Participation", 3);
        alumniActivities.put("Organizer", 3);
        alumniActivities.put("Winner", 3);
        careerActivities.put("Alumni Networking Activities", alumniActivities);

        HashMap<String, Integer> entrepreneurship = new HashMap<>();
        entrepreneurship.put("Participation", 6);
        entrepreneurship.put("Organizer", 3);
        entrepreneurship.put("Winner", 6);
        careerActivities.put("Entrepreneurship Summits", entrepreneurship);

        pointsMap.put("Career & Professional Development", careerActivities);

        // 6. Awards & Recognition
        HashMap<String, HashMap<String, Integer>> awards = new HashMap<>();
        
        HashMap<String, Integer> academicAwards = new HashMap<>();
        academicAwards.put("Participation", 10);
        academicAwards.put("Organizer", 10);
        academicAwards.put("Winner", 10);
        awards.put("Academic Excellence Awards", academicAwards);

        HashMap<String, Integer> sportsAwards = new HashMap<>();
        sportsAwards.put("Participation", 10);
        sportsAwards.put("Organizer", 10);
        sportsAwards.put("Winner", 10);
        awards.put("Sports Awards", sportsAwards);

        pointsMap.put("Awards & Recognition", awards);
    }

    public static int getSAPPoints(String category, String type, String participationType) {
        if (pointsMap.containsKey(category)) {
            HashMap<String, HashMap<String, Integer>> categoryMap = pointsMap.get(category);
            if (categoryMap.containsKey(type)) {
                HashMap<String, Integer> typeMap = categoryMap.get(type);
                if (typeMap.containsKey(participationType)) {
                    return typeMap.get(participationType);
                }
            }
        }
        return 0;
    }

    public static void displayAvailableCategories() {
        System.out.println("Available Activity Categories:");
        int i = 1;
        for (String category : pointsMap.keySet()) {
            System.out.println(i + ". " + category);
            i++;
        }
    }

    public static void displayTypesForCategory(String category) {
        if (pointsMap.containsKey(category)) {
            System.out.println("Available Activity Types for " + category + ":");
            int i = 1;
            for (String type : pointsMap.get(category).keySet()) {
                System.out.println(i + ". " + type);
                i++;
            }
        }
    }

    public static void displayParticipationTypes() {
        System.out.println("Available Participation Types:");
        System.out.println("1. Participation");
        System.out.println("2. Organizer/Volunteer");
        System.out.println("3. Winner");
    }

    public static String getParticipationTypeByNumber(int choice) {
        switch (choice) {
            case 1: return "Participation";
            case 2: return "Organizer";
            case 3: return "Winner";
            default: return "Participation";
        }
    }
}