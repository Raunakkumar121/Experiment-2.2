// EmployeeManagement.java
import java.io.*;
import java.util.Scanner;

public class EmployeeManagement {
    private static final String DATA_FILE = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addEmployee(sc);
                    break;
                case "2":
                    displayAllEmployees();
                    break;
                case "3":
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Enter 1, 2 or 3.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("Employee Management System");
        System.out.println("1. Add an Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Exit");
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Designation: ");
        String desig = sc.nextLine().trim();

        System.out.print("Enter Salary: ");
        double salary = 0.0;
        try {
            salary = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary, setting to 0.0");
        }

        Employee emp = new Employee(id, name, desig, salary);

        // Append to file (formatted text - CSV line)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            bw.write(emp.toCSV());
            bw.newLine();
            System.out.println("Employee added.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("--- Employee Records ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromCSV(line);
                if (emp != null) {
                    System.out.println(emp);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No valid employee records found.");
            } else {
                System.out.printf("Total employees: %d%n", count);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
