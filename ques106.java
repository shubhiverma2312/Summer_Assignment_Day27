import java.util.ArrayList;
import java.util.Scanner;

class Employee
{
    int id;
    String name;
    String dept;
    double salary;

    Employee(int id, String name, String dept, double salary)
    {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String toString()
    {
        return "ID: " + id + ", Name: " + name + 
               ", Dept: " + dept + ", Salary: ₹" + salary;
    }
}

public class EmployeeManagementSystem
{
    static ArrayList<Employee> employees = new ArrayList<>();
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
                case 1: addEmployee(); break;
                case 2: viewAllEmployees(); break;
                case 3: searchEmployee(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void showMenu()
    {
        System.out.println("\n--- Employee Management ---");
        System.out.println("1: Add Employee");
        System.out.println("2: View All Employees");
        System.out.println("3: Search Employee by ID");
        System.out.println("4: Update Employee");
        System.out.println("5: Delete Employee");
        System.out.println("6: Exit");
    }

    static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findEmployeeById(id) != null) {
            System.out.println("Employee ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Salary: ₹");
        double salary = sc.nextDouble();

        employees.add(new Employee(id, name, dept, salary));
        System.out.println("Employee added successfully!");
    }

    static void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found!");
            return;
        }
        System.out.println("\n--- All Employee Records ---");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    static Employee findEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.id == id) return e;
        }
        return null;
    }

    static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        Employee e = findEmployeeById(id);
        if (e != null) System.out.println("Found: " + e);
        else System.out.println("Employee with ID " + id + " not found!");
    }

    static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee e = findEmployeeById(id);
        
        if (e == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter new Name: ");
        e.name = sc.nextLine();
        System.out.print("Enter new Department: ");
        e.dept = sc.nextLine();
        System.out.print("Enter new Salary: ₹");
        e.salary = sc.nextDouble();
        System.out.println("Employee updated successfully!");
    }

    static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        Employee e = findEmployeeById(id);
        
        if (e != null) {
            employees.remove(e);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }
}