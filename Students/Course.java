import java.util.Arrays;

public class Course {
   // Constant static variable
   private static final int MAX_STUDENTS = 50;
   
   // Instance variables
   private String courseName;
   private Student[] students;
   private int studentCount;
   
   // Constructor
   public Course(String courseName) {
      this.courseName = courseName;
      this.students = new Student[MAX_STUDENTS]; // Initializing the student array to hold up to max students
      this.studentCount = 0; // Initializing the student count to 0
   }
   
   // Gets the course name
   public String getCourseName() {
      return courseName;
   }
   
   // Gets enrolled students for the course
   public int getStudentCount() {
      return studentCount;
   }
   
   // Gets max students
   public static int getMaxStudents() {
      return Course.MAX_STUDENTS;
   }
   
   // Adds a student to the course if theirs space
   public void addStudent(Student s) {
      if (isFull()) {
         System.out.println("Cannot add student. Course " + courseName + " is full.");
         return;
      }
      students[studentCount++] = s; // Add the student in the course, then increment studentCount
   }
   
   // Checks to see if a course is full
   public boolean isFull() {
      return studentCount >= getMaxStudents();
   }
   
   // Calculate the average grades of all students currently enrolled in the course
   public double getAverageGrade() {
      if (studentCount == 0) return -1;
      
      double sum = 0.0;
      for (int i = 0; i < studentCount; i++) {
         sum += students[i].getGrade();
      }
            
      return sum / studentCount;
   }
   
   // Finds the lowest grade in the course
   public double getLowestGrade() {
      if (studentCount == 0) return -1;
      
      double minGrade = students[0].getGrade();
      for (int i = 1; i < studentCount; i++) {
         if (minGrade > students[i].getGrade()) {
            minGrade = students[i].getGrade();
         }
      }
      
      return minGrade;
   }
   
   // Finds the highest grade in the course
   public double getHighestGrade() {
      if (studentCount == 0) return -1;
      
      double maxGrade = students[0].getGrade();
      for (int i = 1; i < studentCount; i++) {
         if (maxGrade < students[i].getGrade()) {
            maxGrade = students[i].getGrade();
         }
      }
      
      return maxGrade;
   }
   
   // Prints a string representation of the class object
   public String toString() {
      return String.format("Course: %s%nStudents enrolled: %d%nAverage Grade:%.2f%nLowest Grade: %.2f%nHighest Grade: %.2f%n",
                            courseName, studentCount, getAverageGrade(), getLowestGrade(), getHighestGrade());
   }
}