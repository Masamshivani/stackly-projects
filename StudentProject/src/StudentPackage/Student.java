package StudentPackage;

import java.io.Serializable;

	public class Student implements Serializable {

	    private static final long serialVersionUID = 1L;

	    private int id;
	    private String name;
	    private String email;
	    private double marks;
	    private int attendance;
	    private StudentStatus status;
	    private Course course;

	    public Student(int id, String name, String email,
	                   double marks, int attendance,
	                   StudentStatus status, Course course) {

	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.marks = marks;
	        this.attendance = attendance;
	        this.status = status;
	        this.course = course;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public double getMarks() {
	        return marks;
	    }

	    public int getAttendance() {
	        return attendance;
	    }

	    public StudentStatus getStatus() {
	        return status;
	    }

	    public Course getCourse() {
	        return course;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setMarks(double marks) {
	        this.marks = marks;
	    }

	    public void setAttendance(int attendance) {
	        this.attendance = attendance;
	    }

	    public void setStatus(StudentStatus status) {
	        this.status = status;
	    }

	    public void setCourse(Course course) {
	        this.course = course;
	    }

	    public String getGrade() {

	        if (marks >= 90)
	            return "A+";
	        else if (marks >= 80)
	            return "A";
	        else if (marks >= 70)
	            return "B";
	        else if (marks >= 60)
	            return "C";
	        else if (marks >= 50)
	            return "D";
	        else
	            return "F";
	    }

	    @Override
	    public String toString() {

	        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Course: " + course.getCourseName() + ", Marks: " + marks + ", Attendance: " + attendance + "%" +
	                ", Grade: " + getGrade() +
	                ", Status: " + status;
	    }
	}


