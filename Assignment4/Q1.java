// Name: Jasleen | Roll No: 1024170091 | Lab Assignment 5 (Generics & Utility Classes)
// Q1: Generic method to sum an array of numeric values
public class Q1 {
    public static <T extends Number> double sum(T[] arr) {
        double total = 0;
        for (T n : arr) total += n.doubleValue();
        return total;
    }

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        Float[] floats = {1.5f, 2.5f, 3.5f};
        Double[] doubles = {10.25, 20.75, 30.5};

        System.out.println("Sum of Integer array: " + sum(ints));
        System.out.println("Sum of Float array:   " + sum(floats));
        System.out.println("Sum of Double array:  " + sum(doubles));
    }
}
