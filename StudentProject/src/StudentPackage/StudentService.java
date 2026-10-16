package StudentPackage;

import java.util.*;

public class StudentService {

    private HashMap<Integer, Student> students = new HashMap<>();

    
    public void addStudent(Student student) {

        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException(
                    "Student ID already exists!"
            );
        }

        if (student.getMarks() < 0 || student.getMarks() > 100) {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100!"
            );
        }

        if (student.getAttendance() < 0 ||
                student.getAttendance() > 100) {

            throw new IllegalArgumentException(
                    "Attendance must be between 0 and 100!"
            );
        }

        students.put(student.getId(), student);

        System.out.println("Student added successfully.");
    }

    
    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students.values()) {
            System.out.println("--------------------------------");
            System.out.println(student);
        }
    }

   
    public Student searchById(int id) {

        return students.get(id);
    }

   
    public void searchByName(String name) {

        boolean found = false;

        for (Student student : students.values()) {

            if (student.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    
    public void updateMarks(int id, double marks) {

        if (marks < 0 || marks > 100) {
            System.out.println( "Marks must be between 0 and 100.");
            return;
        }

        Student student = students.get(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.setMarks(marks);

        System.out.println("Marks updated successfully.");
    }

   
    public void updateAttendance(int id, int attendance) {

        if (attendance < 0 || attendance > 100) {
            System.out.println("Attendance must be between 0 and 100." );
            return;
        }

        Student student = students.get(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.setAttendance(attendance);

        System.out.println(
                "Attendance updated successfully."
        );
    }

    
    public void removeStudent(int id) {

        if (students.remove(id) != null) {
            System.out.println(
                    "Student removed successfully."
            );
        } else {
            System.out.println("Student not found.");
        }
    }

    
    public void displayTopper() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student topper = Collections.max(
                students.values(),
                Comparator.comparingDouble(Student::getMarks)
        );

        System.out.println("---------- TOPPER ----------");
        System.out.println(topper);
    }

    
    public HashMap<Integer, Student> getStudents() {
        return students;
    }

    
    public void setStudents(HashMap<Integer, Student> students) {
        this.students = students;
    }
}
