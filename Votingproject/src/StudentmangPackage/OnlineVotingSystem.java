package StudentmangPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineVotingSystem {

   
    static Map<String, User> users = new HashMap<>();

    // Student ID -> true/false
    // true means the student has already voted
    static Map<String, Boolean> userVoted = new HashMap<>();
     static Map<String, Candidate> candidates = new HashMap<>();

    // Candidate ID -> Number of votes
    static Map<String, Integer> candidateVotes = new HashMap<>();

    static Scanner sc = new Scanner(System.in);
    

    public static void addDefaultCandidates() {

        candidates.put( "C101",new Candidate("C101", "Rahul"));

        candidates.put( "C102",  new Candidate("C102", "Priya") );

        candidates.put( "C103",  new Candidate("C103", "Arjun") );

        candidateVotes.put("C101", 0);
        candidateVotes.put("C102", 0);
        candidateVotes.put("C103", 0);
    }


    public static void registerUser() {

        System.out.println("\n========== REGISTER ==========");

        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();

        // Checking whether already registered
        if (users.containsKey(studentId)) {

            System.out.println( "Student ID already registered!" );

            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();


        // Check empty fields
        if (studentId.isEmpty() || name.isEmpty() || password.isEmpty()) {

            System.out.println( "All fields are required!" );

            return;
        }


        // Creating User object
        User user =new User(studentId,name, password );


        // Storing the user in HashMap
        users.put(studentId, user);


        // Initially user has not voted
        userVoted.put(studentId, false);


        System.out.println(
                "Registration successful!"
        );
    }

    public static User login() {

        System.out.println("\n========== LOGIN ==========");

        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();


        // Checking Student ID
        if (!users.containsKey(studentId)) {

            System.out.println( "Student ID not found!" );

            return null;
        }


        User user = users.get(studentId);


        // Checking password
        if (!user.getPassword().equals(password)) {

            System.out.println("Incorrect password!");

            return null;
        }


        System.out.println( "Login successful!" );

        System.out.println( "Welcome " + user.getName() );
          return user;
    }

    public static void displayCandidates() {

        System.out.println(
                "\n========== CANDIDATES =========="
        );

        for (Candidate candidate :
                candidates.values()) {

            System.out.println( candidate.getCandidateId()+ " - " + candidate.getCandidateName());
        }
    }


    public static void castVote(User user)
            throws VotingException {


        // Check login
        if (user == null) {

     throw new VotingException( "Please login first!");
        }


        String studentId = user.getStudentId();
          // Checking whether user already voted
        if (userVoted.getOrDefault( studentId, false)) {

            throw new VotingException( "You have already voted!");
            
        }


        // Showing candidates
        displayCandidates();


        System.out.print( "Enter Candidate ID: " );

        String candidateId = sc.nextLine();


        // Checking candidate ID
        if (!candidates.containsKey(candidateId)) {

            throw new VotingException( "Invalid Candidate ID!" );
        }


        // Getting current votes
        int currentVotes = candidateVotes.getOrDefault(candidateId,  0 );


        // Increasing the vote
        candidateVotes.put(candidateId, currentVotes + 1);
    // Marking user as voted
        userVoted.put( studentId, true );
 System.out.println( "Vote cast successfully!");
                
     }


    public static void displayTotalVotes() {

        System.out.println( "\n========== TOTAL VOTES ==========");
       

        for (String candidateId :candidateVotes.keySet()) {

            Candidate candidate =  candidates.get(candidateId);

            int votes = candidateVotes.get(candidateId);

            System.out.println( candidate.getCandidateName() + " : " + votes+ " votes");
            
        }
    }


    public static void displayResult() {

        System.out.println( "\n========== ELECTION RESULT ==========" );


        int totalVotes = 0;
        for (int votes : candidateVotes.values()) {

            totalVotes = totalVotes + votes;
        }


        
        if (totalVotes == 0) {

            System.out.println( "No votes have been cast yet.");

            return;
        }


        String winnerId = null;
        String runnerUpId = null;


        int highestVotes = -1;
        int secondHighestVotes = -1;

        for (String candidateId : candidateVotes.keySet()) {

            int votes =  candidateVotes.get(candidateId);


            if (votes > highestVotes) {

                secondHighestVotes = highestVotes;

                runnerUpId = winnerId;

                highestVotes = votes;

                winnerId =candidateId;

            } else if (votes > secondHighestVotes) {

                secondHighestVotes =votes;

                runnerUpId =  candidateId;
            }
        }

        Candidate winner = candidates.get(winnerId);


        System.out.println( "Winner    : "+ winner.getCandidateName());
       

        System.out.println( "Votes     : " + highestVotes);

        if (runnerUpId != null) {

            Candidate runnerUp = candidates.get(runnerUpId);

            System.out.println( "Runner-Up : " + runnerUp.getCandidateName() );

            System.out.println( "Votes     : " + secondHighestVotes);
        }
    }


    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        // Add candidates when program starts
        addDefaultCandidates();


        User loggedInUser = null;


        while (true) {

            System.out.println("\n================================" );

            System.out.println(
                    "       ONLINE VOTING SYSTEM"
            );

            System.out.println( "================================");
           

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Candidate List");
            System.out.println("4. Cast Vote");
            System.out.println("5. View Total Votes");
            System.out.println("6. View Result");
            System.out.println("7. Logout");
            System.out.println("8. Exit");


            System.out.print( "Enter your choice: " );
          int choice;


            try {

                choice = Integer.parseInt(sc.nextLine() );

            } catch (NumberFormatException e) {

                System.out.println( "Please enter a number!" );

                continue;
            }


            switch (choice) {


                // REGISTER
                case 1:

                    registerUser();

                    break;


                // LOGIN
                case 2:

                    loggedInUser =
                            login();

                    break;


                // CANDIDATE LIST
                case 3:

                    displayCandidates();

                    break;


                // CAST VOTE
                case 4:

                    if (loggedInUser == null) {

                        System.out.println("Please login first!" );

                        loggedInUser = login();


                        if (loggedInUser == null) {
                            break;
                        }
                    }


                    try {

                        castVote( loggedInUser);
                        
                    } catch (VotingException e) {

                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
   // TOTAL VOTES
                case 5:

                    displayTotalVotes();

                    break;


                // RESULT
                case 6:

                    displayResult();

                    break;


                // LOGOUT
                case 7:

                    if (loggedInUser != null) {

                        System.out.println("Logout successful!" );

                        loggedInUser = null;

                    } else {

                        System.out.println("No user is logged in.");
                        
                    }

                    break;

                case 8:

                    System.out.println("Thank you for using "  + "Online Voting System!" );
                    sc.close();

                    return;
                    default:

                    System.out.println(  "Invalid choice!" );
            }
        }
    }
}