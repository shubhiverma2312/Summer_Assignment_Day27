import java.util.ArrayList;
import java.util.Scanner;

class StudentMarksheet {
    int rollNo;
    String name;
    int[] marks = new int[5]; // 5 subjects
    int total;
    double percentage;
    String grade;
    String result;
    static String[] subjects = {"Physics", "Chemistry", "Maths", "English", "Computer"};

    StudentMarksheet(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        calculateResult();
    }

    void calculateResult() {
        total = 0;
        boolean fail = false;
        for (int mark : marks) {
            total += mark;
            if (mark < 33) fail = true; // Passing marks = 33
        }
        
        percentage = total / 5.0;
        
        if (fail) {
            result = "FAIL";
            grade = "F";
        } else {
            result = "PASS";
            if (percentage >= 90) grade = "A+";
            else if (percentage >= 80) grade = "A";
            else if (percentage >= 70) grade = "B+";
            else if (percentage >= 60) grade = "B";
            else if (percentage >= 50) grade = "C";
            else if (percentage >= 33) grade = "D";
            else grade = "F";
        }
    }

    void displayMarksheet() {
        System.out.println("\n========= MARKSHEET =========");
        System.out.println("Roll No: " + rollNo + " | Name: " + name);
        System.out.println("-----------------------------");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-10s : %3d / 100\n", subjects[i], marks[i]);
        }
        System.out.println("-----------------------------");
        System.out.println("Total : " + total + " / 500");
        System.out.printf("Percentage : %.2f%%\n", percentage);
        System.out.println("Grade : " + grade);
        System.out.println("Result : " + result);
        System.out.println("=============================");
    }
}

public class MarksheetManagementSystem {
    static ArrayList<StudentMarksheet> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllMarksheets(); break;
                case 3: searchMarksheet(); break;
                case 4: showClassToppers(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice!= 5);
    }

    static void showMenu() {
        System.out.println("\n--- Marksheet Management ---");
        System.out.println("1: Add Student Marksheet");
        System.out.println("2: View All Marksheets");
        System.out.println("3: Search Marksheet by Roll No");
        System.out.println("4: Show Class Toppers");
        System.out.println("5: Exit");
    }

    static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (StudentMarksheet s : students) {
            if (s.rollNo == roll) {
                System.out.println("Roll No already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int[] marks = new int[5];
        System.out.println("Enter marks out of 100:");
        for (int i = 0; i < 5; i++) {
            System.out.print(StudentMarksheet.subjects[i] + ": ");
            marks[i] = sc.nextInt();
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Enter 0-100");
                i--; // re-enter same subject
            }
        }

        students.add(new StudentMarksheet(roll, name, marks));
        System.out.println("Marksheet created successfully!");
    }

    static void viewAllMarksheets() {
        if (students.isEmpty()) {
            System.out.println("No records found!");
            return;
        }
        for (StudentMarksheet s : students) {
            s.displayMarksheet();
        }
    }

    static void searchMarksheet() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        for (StudentMarksheet s : students) {
            if (s.rollNo == roll) {
                s.displayMarksheet();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void showClassToppers() {
        if (students.isEmpty()) {
            System.out.println("No records!");
            return;
        }
        
        StudentMarksheet topper = students.get(0);
        for (StudentMarksheet s : students) {
            if (s.percentage > topper.percentage && s.result.equals("PASS")) {
                topper = s;
            }
        }
        
        System.out.println("\n--- CLASS TOPPER ---");
        topper.displayMarksheet();
    }
}