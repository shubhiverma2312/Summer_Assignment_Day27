import java.util.ArrayList;
import java.util.Scanner;

class Student
{
    int rollNo;
    String name;
    int age;
    String course;

    Student(int rollNo, String name, int age, String course)
    {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public String toString()
    {
        return "Roll: " + rollNo + ", Name: " + name + 
               ", Age: " + age + ", Course: " + course;
    }
}

public class StudentRecordSystem
{
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int choice;
        do
        {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void showMenu()
    {
        System.out.println("\n--- Student Record Management ---");
        System.out.println("1: Add Student");
        System.out.println("2: View All Students");
        System.out.println("3: Search Student by Roll No");
        System.out.println("4: Delete Student");
        System.out.println("5: Exit");
    }

    static void addStudent()
    {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        // Check if roll no already exists
        for (Student s : students)
        {
            if (s.rollNo == roll)
            {
                System.out.println("Roll No already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        students.add(new Student(roll, name, age, course));
        System.out.println("Student added successfully!");
    }

    static void viewAllStudents()
    {
        if (students.isEmpty())
        {
            System.out.println("No records found!");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        for (Student s : students)
        {
            System.out.println(s);
        }
    }

    static void searchStudent()
    {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();
        for (Student s : students)
        {
            if (s.rollNo == roll)
            {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student with Roll No " + roll + " not found!");
    }

    static void deleteStudent()
    {
        System.out.print("Enter Roll No to delete: ");
        int roll = sc.nextInt();
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).rollNo == roll)
            {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student with Roll No " + roll + " not found!");
    }
}