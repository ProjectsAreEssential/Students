public class Student {
   // Static variable
   private static int enrolledStudents = 0;
   
   // Instance variables
   private int id;
   private String name;
   private String course;
   private double grade;
   
   // Constructor
   public Student(int id, String name, String course, double grade) {
      this.id = id;
      this.name = name;
      this.course = course;
      this.grade = grade;
      
      enrolledStudents++; // Increments the static variable with each student object created
   }
   
   // Gets the id
   public int getId() {
      return id;
   }
   
   // Gets the name
   public String getName() {
      return name;
   }
   
   // Gets the course
   public String getCourse() {
      return course;
   }
   
   // Gets the grade
   public double getGrade() {
      return grade;
   }
   
   // Gets the enrolled students
   public static int getEnrolledStudents() {
      return enrolledStudents;
   }
   
   // Change the name
   public void setName(String newName) {
      name = newName;
   }
   
   // Change the grade
   public void setGrade(double newGrade) {
      grade = newGrade;
   }
   
   // Prints a string representation of the student object
   public String toString() {
      return String.format("ID: %d%nName: %s%nCourse: %s%nGrade: %.2f%n", id, name, course, grade);
   }
}