// Name: Jasleen | Roll No: 1024170091 | Lab Assignment 5 (Generics & Utility Classes)
import java.util.Objects;
import java.util.Optional;

// Q3: Generic Result class
class Result<T extends Number> {
    private final String studentName;
    private final T marks;          // may be null (not available)
    private final T totalMarks;

    public Result(String studentName, T marks, T totalMarks) {
        this.studentName = Objects.requireNonNull(studentName, "Student name must not be null");
        this.totalMarks = Objects.requireNonNull(totalMarks, "Total marks must not be null");
        this.marks = marks;
    }

    public Optional<T> getMarks() {
        return Optional.ofNullable(marks);
    }

    public Optional<Double> getPercentage() {
        return getMarks().map(m -> m.doubleValue() * 100.0 / totalMarks.doubleValue());
    }

    public boolean hasPassed(double thresholdPercent) {
        return getPercentage().map(p -> p >= thresholdPercent).orElse(false);
    }

    public void display(double thresholdPercent) {
        System.out.println("Student: " + studentName);
        getMarks().ifPresentOrElse(
            m -> {
                System.out.println("Marks: " + m + " / " + totalMarks);
                System.out.printf("Percentage: %.2f%%%n", getPercentage().get());
                System.out.println("Status: " + (hasPassed(thresholdPercent) ? "PASS" : "FAIL"));
            },
            () -> System.out.println("Marks not available")
        );
        System.out.println();
    }
}

public class Q3 {
    public static void main(String[] args) {
        double threshold = 40.0;

        new Result<Integer>("Aman", 78, 100).display(threshold);
        new Result<Double>("Riya", 32.5, 100.0).display(threshold);
        new Result<Float>("Simran", 90f, 120f).display(threshold);
        new Result<Integer>("Karan", null, 100).display(threshold);

        try {
            new Result<Integer>(null, 50, 100);
        } catch (NullPointerException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
}
