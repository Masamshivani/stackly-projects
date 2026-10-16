package StudentPackage;

import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();

        // Loading existing students from file
        HashMap<Integer, Student> loadedStudents =StudentFileService.loadStudents();

        service.setStudents(loadedStudents);
     
        if (service.getStudents().isEmpty()) {

            service.addStudent( new Student( 101,"Shivani","shivani@gmail.com",85,92, StudentStatus.ACTIVE,new Course(1, "Java")) );

            service.addStudent( new Student( 102, "Anu", "anu@gmail.com", 92, 95, StudentStatus.ACTIVE, new Course(2, "Python")));

            service.addStudent( new Student(103,"Priya", "priya@gmail.com",78, 88,StudentStatus.ACTIVE,new Course(1, "Java")));
                   

            service.addStudent( new Student( 104,"Keerthi", "keerthi@gmail.com", 88, 90,StudentStatus.COMPLETED,new Course(3, "Full Stack")));
            
          

            service.addStudent(
                    new Student( 105,"Sneha","sneha@gmail.com", 95, 98, StudentStatus.ACTIVE, new Course(2, "Python"  ) ));

            StudentFileService.saveStudents(
                    service.getStudents()
            );
        }


        System.out.println( "====================================");
        System.out.println(  "     STUDENT MANAGEMENT SYSTEM");
        System.out.println( "====================================");

        while (true) {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search by Student ID");
            System.out.println("4. Search by Name");
            System.out.println("5. Update Marks");
            System.out.println("6. Update Attendance");
            System.out.println("7. Calculate Grade");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Save Students");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {

                System.out.println(  "Please enter a valid number." );

                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:

                    try {

                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();

                        System.out.print("Enter Attendance: ");
                        int attendance = sc.nextInt();
                        sc.nextLine();

                        System.out.print( "Enter Course ID: " );
                        int courseId = sc.nextInt();
                        sc.nextLine();

                        System.out.print( "Enter Course Name: " );
                        String courseName = sc.nextLine();

                        System.out.println( "Select Status:");
                        System.out.println("1. ACTIVE" );
                        System.out.println( "2. INACTIVE" );
                        System.out.println( "3. COMPLETED");

                        System.out.print( "Enter status: " );

                        int statusChoice = sc.nextInt();
                        sc.nextLine();

                        StudentStatus status;

                        if (statusChoice == 1) {
                            status = StudentStatus.ACTIVE;
                        } else if (statusChoice == 2) {
                            status = StudentStatus.INACTIVE;
                        } else if (statusChoice == 3) {
                            status = StudentStatus.COMPLETED;
                        } else {
                            System.out.println( "Invalid status.");
                            continue;
                        }

                        Course course =
                                new Course( courseId, courseName );

                        Student student =
                                new Student(  id, name, email,marks, attendance, status,course );

                        service.addStudent(student);

                    } catch (Exception e) {

                        System.out.println("Invalid input: "+ e.getMessage());

                        sc.nextLine();
                    }

                    break;

                case 2:

                    service.displayAllStudents();

                    break;

                case 3:

                    System.out.print("Enter Student ID: ");
                    

                    int searchId = sc.nextInt();
                    sc.nextLine();

                    Student student =
                            service.searchById(searchId);

                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                               
                    }

                    break;

                case 4:

                    System.out.print( "Enter Student Name: ");

                    String searchName = sc.nextLine();

                    service.searchByName(searchName);

                    break;

                case 5:

                    System.out.print( "Enter Student ID: ");
                   
                    int marksId = sc.nextInt();

                    System.out.print( "Enter new marks: ");
                    

                    double newMarks = sc.nextDouble();
                    sc.nextLine();

                    service.updateMarks( marksId,newMarks);

                    break;

                case 6:

                    System.out.print( "Enter Student ID: " );

                    int attendanceId = sc.nextInt();

                    System.out.println("Enter new attendance: " );

                    int newAttendance = sc.nextInt();

                    sc.nextLine();

                    service.updateAttendance( attendanceId,newAttendance);
                    

                    break;

                case 7:

                    System.out.print( "Enter Student ID: ");

                    int gradeId = sc.nextInt();
                    sc.nextLine();

                    Student gradeStudent =
                            service.searchById(gradeId);

                    if (gradeStudent != null) {

                        System.out.println( "Student Name: " + gradeStudent.getName());
                        

                        System.out.println("Marks: "  + gradeStudent.getMarks() );
                        

                        System.out.println( "Grade: "  + gradeStudent.getGrade() );

                    } else {

                        System.out.println( "Student not found." );
                    }

                    break;

                case 8:

                    System.out.print("Enter Student ID to remove: ");

                    int removeId = sc.nextInt();
                    sc.nextLine();

                    service.removeStudent(removeId);

                    break;

                case 9:

                    service.displayTopper();

                    break;

                case 10:

                    StudentFileService.saveStudents( service.getStudents() );
               break;

                case 11:

                  
                    StudentFileService.saveStudents( service.getStudents() );

                    System.out.println("Thank you for using Student Management System!"  );

                    sc.close();

                    return;

                default:

                    System.out.println( "Invalid choice. Please try again." );
            }
        }
    }
}
