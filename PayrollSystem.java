import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=₹" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee added successfully!");
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();
        int choice;

        do {
            System.out.println("\n========= PAYROLL MANAGEMENT SYSTEM =========");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    sc.nextLine();
                    String ftName = sc.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int ftId = sc.nextInt();
                    System.out.print("Enter Monthly Salary (₹): ");
                    double monthlySalary = sc.nextDouble();
                    payrollSystem.addEmployee(new FullTimeEmployee(ftName, ftId, monthlySalary));
                    break;

                case 2:
                    System.out.print("Enter Employee Name: ");
                    sc.nextLine();
                    String ptName = sc.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int ptId = sc.nextInt();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = sc.nextInt();
                    System.out.print("Enter Hourly Rate (₹): ");
                    double hourlyRate = sc.nextDouble();
                    payrollSystem.addEmployee(new PartTimeEmployee(ptName, ptId, hoursWorked, hourlyRate));
                    break;

                case 3:
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = sc.nextInt();
                    payrollSystem.removeEmployee(removeId);
                    break;

                case 4:
                    System.out.println("\nEmployee Details:");
                    payrollSystem.displayEmployees();
                    break;

                case 5:
                    System.out.println("Exiting Payroll System. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
