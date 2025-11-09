// StudentSerializationDemo.java
import java.io.*;
import java.util.Scanner;

public class StudentSerializationDemo {
    private static final String FILENAME = "student.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student ID:");
        String id = sc.nextLine().trim();

        System.out.println("Enter student name:");
        String name = sc.nextLine().trim();

        System.out.println("Enter student grade (numeric):");
        double grade = 0.0;
        try {
            grade = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade input. Using 0.0");
        }

        Student s = new Student(id, name, grade);

        // Serialize the student
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(s);
            System.out.println("Student object serialized to " + FILENAME);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
            e.printStackTrace();
        }

        // Deserialize the student (read back)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Object obj = ois.readObject();
            if (obj instanceof Student) {
                Student deserialized = (Student) obj;
                System.out.println("Deserialized Student: " + deserialized);
                System.out.println("ID: " + deserialized.getStudentID());
                System.out.println("Name: " + deserialized.getName());
                System.out.println("Grade: " + deserialized.getGrade());
            } else {
                System.err.println("File did not contain a Student object.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
