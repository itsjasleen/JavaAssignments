// Name: Jasleen | Roll No: 1024170091 | Lab Assignment 5 (Generics & Utility Classes)
import java.util.Optional;

// Q2: Optional<String> for a student's email address
public class Q2 {
    public static void main(String[] args) {
        // 1. Optional containing an email
        Optional<String> email = Optional.of("jasleen@example.com");
        // 2. Empty Optional
        Optional<String> noEmail = Optional.empty();

        // 3. ifPresent()
        System.out.print("Email (ifPresent): ");
        email.ifPresent(System.out::println);
        noEmail.ifPresent(e -> System.out.println("This will not print: " + e));

        // 4. orElse()
        System.out.println("Email with default: " + email.orElse("default@college.edu"));
        System.out.println("Empty with default: " + noEmail.orElse("default@college.edu"));

        // 5. orElseThrow()
        try {
            System.out.println(email.orElseThrow(() -> new IllegalStateException("Email missing")));
            System.out.println(noEmail.orElseThrow(() -> new IllegalStateException("Email missing")));
        } catch (IllegalStateException ex) {
            System.out.println("Exception handled: " + ex.getMessage());
        }
    }
}
