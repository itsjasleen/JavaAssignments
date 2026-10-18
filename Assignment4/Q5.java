// Name: Jasleen | Roll No: 1024170091 | Lab Assignment 5 (Generics & Utility Classes)
import java.util.Objects;
import java.util.Optional;

// Q5: Generic utility class
class GenericUtils {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5 {
    public static void main(String[] args) {
        // isEqual
        System.out.println("isEqual(\"java\", \"java\"): " + GenericUtils.isEqual("java", "java"));
        System.out.println("isEqual(10, 20): " + GenericUtils.isEqual(10, 20));
        System.out.println("isEqual(3.5, 3.5): " + GenericUtils.isEqual(3.5, 3.5));

        // getDefault
        System.out.println("getDefault(present): " + GenericUtils.getDefault(Optional.of("Hello"), "Default"));
        System.out.println("getDefault(empty):   " + GenericUtils.getDefault(Optional.<Integer>empty(), 99));

        // max
        System.out.println("max(\"apple\", \"banana\"): " + GenericUtils.max("apple", "banana"));
        System.out.println("max(25, 17): " + GenericUtils.max(25, 17));
        System.out.println("max(2.5, 7.1): " + GenericUtils.max(2.5, 7.1));
        System.out.println("max(date1, date2): " + GenericUtils.max(java.time.LocalDate.of(2025, 1, 1), java.time.LocalDate.of(2026, 5, 10)));

        // isNull
        String s = null;
        System.out.println("isNull(null): " + GenericUtils.isNull(s));
        System.out.println("isNull(\"text\"): " + GenericUtils.isNull("text"));
        System.out.println("isNull(123): " + GenericUtils.isNull(123));
    }
}
