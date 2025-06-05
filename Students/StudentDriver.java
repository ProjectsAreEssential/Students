import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

public class StudentDriver {
   public static void main(String[] args) {
      try {
         String[] courses = StudentModule.readCourses("courses.txt"); // Read courses
         Student[] students = StudentModule.readStudents("students.txt"); // Read students
         
         PrintStream output = new PrintStream(new File("output.txt"));
         
         // Build the 2D grades matrix
         double[][] grades = StudentModule.buildGradesMatrix(students, courses);
         
         // Print formatted report to the output file
         StudentModule.printGradesMatrix(students, courses, grades, output);
         StudentModule.printCourseSummary(courses, grades, output);
         
         output.close(); // Close the PrintStream
      } catch (FileNotFoundException e) {
         System.out.println("Error: one or more input files not found.");
      }
   }
}