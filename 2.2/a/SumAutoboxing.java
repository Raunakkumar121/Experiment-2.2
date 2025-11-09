import java.util.ArrayList;
import java.util.Scanner;

public class SumAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter integers separated by spaces (or blank line to finish):");

        // Read a whole line so user can input multiple numbers at once
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("No numbers entered. Sum = 0");
            sc.close();
            return;
        }

        String[] tokens = line.split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String t : tokens) {
            try {
                // parse string to int, then autobox to Integer when adding to list
                int value = Integer.parseInt(t);
                numbers.add(value); // autoboxing: int -> Integer
            } catch (NumberFormatException e) {
                System.out.printf("Skipping invalid token: '%s'%n", t);
            }
        }

        // Calculate sum using enhanced for-loop (unboxing happens automatically)
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // unboxing: Integer -> int
        }

        System.out.println("Numbers parsed: " + numbers);
        System.out.println("Sum = " + sum);
        sc.close();
    }
}
