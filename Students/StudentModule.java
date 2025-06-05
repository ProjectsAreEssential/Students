import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

public class StudentModule {

    // Counts how many lines are given in the file to determine how many courses there are
    public static int countLines(String filename) {
        int count = 0;
        try {
            Scanner lineCounter = new Scanner(new File(filename));
            while (lineCounter.hasNextLine()) {
                lineCounter.nextLine();
                count++; // Increment the counter each line
            }
            lineCounter.close(); // Close the line counter
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return count; // Returns how much classes there are        
    }

    // Read the course.txt file and return an array of courses
    public static String[] readCourses(String filename) throws FileNotFoundException {
        int numCourses = countLines(filename); // Calls the countLines method to see how many courses there are
        String[] courses = new String[numCourses]; // Create a String array based on how many courses there are

        // Create a new Scanner object to read the courses
        Scanner courseScanner = new Scanner(new File(filename));

        // Fill the array with the courses
        for (int i = 0; i < courses.length; i++) {
            courses[i] = courseScanner.nextLine().trim(); // Trim to remove accidental spaces or newlines
        }
        courseScanner.close(); // Close the course scanner 
        return courses; // Returns the array filled with all the courses from the course.txt file
    }

    // Read the student.txt file and return an array of student objects
    public static Student[] readStudents(String filename) throws FileNotFoundException {
        int numStudents = countLines(filename); // Calls the countLines method to see how many students there are
        Student[] students = new Student[numStudents]; // Create a student array based on how many students there are

        // Create a new Scanner object to read the student object info
        Scanner studentScanner = new Scanner(new File(filename));

        // Fill the array with the student object
        for (int i = 0; i < students.length; i++) {
            int id = studentScanner.nextInt();
            String name = studentScanner.next();
            String course = studentScanner.next();
            double score = studentScanner.nextDouble();

            // studentScanner.nextLine();

            students[i] = new Student(id, name, course, score); // Use the info above to create a new student object
        }
        studentScanner.close(); // Close the student scanner
        return students; // Returns the array filled with student objects from the student.txt file
    }

    // Build the 2D matrix 
    public static double[][] buildGradesMatrix(Student[] students, String[] courses) {
        double[][] grades = new double[students.length][courses.length];

        // Initialize all cells to -1.0 to represent no enrollment
        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades[i].length; j++) {
                grades[i][j] = -1.0;
            }
        }

        // Fill in grades where student is enrolled in a course
        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < courses.length; j++) {
                if (students[i].getCourse().equalsIgnoreCase(courses[j])) {
                    grades[i][j] = students[i].getGrade();
                }
            }
        }

        return grades;
    }

    // Prints the 2D matrix in a nice formatted fashion
    public static void printGradesMatrix(Student[] students, String[] courses, double[][] grades, PrintStream output) {
        // Print header row with course names
        output.print(String.format("%-20s", "Student (ID)"));
        for (int j = 0; j < courses.length; j++) {
            output.print(String.format("%-10s", courses[j]));
        }
        output.println();

        // Print each student's row
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            output.print(String.format("%-20s", s.getName() + " (" + s.getId() + ")"));
            for (int j = 0; j < courses.length; j++) {
                if (grades[i][j] == -1.0) {
                    output.print(String.format("%-10s", "N/A")); // Not enrolled
                } else {
                    output.print(String.format("%-10.2f", grades[i][j]));
                }
            }
            output.println();
        }
    }

    // Prints summary stats
    public static void printCourseSummary(String[] courses, double[][] grades, PrintStream output) {
        output.println();
        output.println("Course Summary:");
        output.println(String.format("%-10s %-10s %-10s %-10s %-10s", 
                         "Course", "Enrolled", "Average", "Highest", "Lowest"));

        for (int j = 0; j < courses.length; j++) {
            int count = 0;
            double sum = 0.0;
            double highest = -1.0;
            double lowest = 101.0;

            for (int i = 0; i < grades.length; i++) {
                if (grades[i][j] != -1.0) {
                    double grade = grades[i][j];
                    sum += grade;
                    count++;
                    if (grade > highest) highest = grade;
                    if (grade < lowest) lowest = grade;
                }
            }

            if (count == 0) {
                output.println(String.format("%-10s %-10s %-10s %-10s %-10s", 
                                 courses[j], "0", "N/A", "N/A", "N/A"));
            } else {
                double avg = sum / count;
                output.println(String.format("%-10s %-10d %-10.2f %-10.2f %-10.2f", 
                                 courses[j], count, avg, highest, lowest));
            }
        }

        output.println(); // Blank line for separation
        output.println("Total Students Enrolled: " + Student.getEnrolledStudents());
    }
}