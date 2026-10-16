package StudentPackage;

 import java.io.*;
 import java.util.HashMap;

 public class StudentFileService {

     private static final String FILE_NAME =
             "students.dat";

     // Save students
     public static void saveStudents(
             HashMap<Integer, Student> students) {

         try (ObjectOutputStream output = new ObjectOutputStream(  new FileOutputStream(FILE_NAME))) {

             output.writeObject(students);

             System.out.println( "Student details saved successfully." );

         } catch (IOException e) {

             System.out.println("Error saving students: "  + e.getMessage() );
         }
     }

    
     @SuppressWarnings("unchecked")
     public static HashMap<Integer, Student> loadStudents() {

         File file = new File(FILE_NAME);

         if (!file.exists()) {
             return new HashMap<>();
         }

         try (ObjectInputStream input = new ObjectInputStream( new FileInputStream(FILE_NAME))) {

             return (HashMap<Integer, Student>)
                     input.readObject();

         } catch (IOException | ClassNotFoundException e) {

             System.out.println( "Error loading students: " + e.getMessage() );
           return new HashMap<>();
         }
     }
 }