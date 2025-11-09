// Employee.java
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String empId;
    private String name;
    private String designation;
    private double salary;

    public Employee(String empId, String name, String designation, double salary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }

    // Convert to CSV line (basic escaping of commas could be improved)
    public String toCSV() {
        return empId + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 4) return null;
        String id = parts[0];
        String name = parts[1];
        String desig = parts[2];
        double sal = 0.0;
        try {
            sal = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            sal = 0.0;
        }
        return new Employee(id, name, desig, sal);
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%s, Name=%s, Designation=%s, Salary=%.2f]",
                              empId, name, designation, salary);
    }
}
