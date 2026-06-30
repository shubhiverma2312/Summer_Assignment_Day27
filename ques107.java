import java.util.ArrayList;
import java.util.Scanner;

class EmployeeSalary {
    int empId;
    String name;
    double basicSalary;
    double hra;      // House Rent Allowance
    double da;       // Dearness Allowance  
    double pf;       // Provident Fund
    double tax;      // Income Tax
    double netSalary;

    EmployeeSalary(int empId, String name, double basicSalary) {
        this.empId = empId;
        this.name = name;
        this.basicSalary = basicSalary;
        calculateSalary();
    }

    void calculateSalary() {
        hra = 0.20 * basicSalary;        // 20% of basic
        da = 0.10 * basicSalary;         // 10% of basic
        double gross = basicSalary + hra + da;
        
        pf = 0.12 * basicSalary;         // 12% of basic
        tax = calculateTax(gross);       // Tax on gross
        
        netSalary = gross - pf - tax;
    }

    double calculateTax(double gross) {
        // Simple tax slab
        if (gross <= 300000) return 0;
        else if (gross <= 600000) return 0.05 * (gross - 300000);
        else if (gross <= 900000) return 15000 + 0.10 * (gross - 600000);
        else return 45000 + 0.20 * (gross - 900000);
    }

    void displaySlip() {
        System.out.println("\n--- SALARY SLIP ---");
        System.out.println("Emp ID: " + empId + " | Name: " + name);
        System.out.println("-------------------");
        System.out.println("Basic Salary : ₹" + basicSalary);
        System.out.println("HRA (20%)    : ₹" + hra);
        System.out.println("DA (10%)     : ₹" + da);
        System.out.println("Gross Salary : ₹" + (basicSalary + hra + da));
        System.out.println("-------------------");
        System.out.println("PF (12%)     : ₹" + pf);
        System.out.println("Tax Deducted : ₹" + tax);
        System.out.println("Total Deductions: ₹" + (pf + tax));
        System.out.println("-------------------");
        System.out.println("Net Salary   : ₹" + netSalary);
    }
}

public class SalaryManagementSystem {
    static ArrayList<EmployeeSalary> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewAllSlips(); break;
                case 3: viewSingleSlip(); break;
                case 4: updateBasicSalary(); break;
                case 5: System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void showMenu() {
        System.out.println("\n--- Salary Management ---");
        System.out.println("1: Add Employee");
        System.out.println("2: View All Salary Slips");
        System.out.println("3: View Salary Slip by ID");
        System.out.println("4: Update Basic Salary");
        System.out.println("5: Exit");
    }

    static void addEmployee() {
        System.out.print("Enter Emp ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (EmployeeSalary e : employees) {
            if (e.empId == id) {
                System.out.println("Employee ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Basic Salary: ₹");
        double basic = sc.nextDouble();

        employees.add(new EmployeeSalary(id, name, basic));
        System.out.println("Employee added and salary calculated!");
    }

    static void viewAllSlips() {
        if (employees.isEmpty()) {
            System.out.println("No employee records!");
            return;
        }
        for (EmployeeSalary e : employees) {
            e.displaySlip();
        }
    }

    static void viewSingleSlip() {
        System.out.print("Enter Emp ID: ");
        int id = sc.nextInt();
        for (EmployeeSalary e : employees) {
            if (e.empId == id) {
                e.displaySlip();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    static void updateBasicSalary() {
        System.out.print("Enter Emp ID: ");
        int id = sc.nextInt();
        for (EmployeeSalary e : employees) {
            if (e.empId == id) {
                System.out.print("Enter new Basic Salary: ₹");
                e.basicSalary = sc.nextDouble();
                e.calculateSalary(); // Recalculate everything
                System.out.println("Salary updated!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }
}