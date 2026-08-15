abstract class LibraryResource {

    private int resourceId;
    private String title;
    private String author;

    static String libraryName = "Thapar Smart Library";
    static int resourceCount = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        resourceCount++;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    protected void displayBasicDetails() {
        System.out.println("Resource ID: " + resourceId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }

    public abstract double calculateFine(int overdueDays);

    public static void displayTotalResources() {
        System.out.println("Total Resources Created: " + resourceCount);
    }
}


interface Printable {
    void printDetails();
}


class Book extends LibraryResource implements Printable {

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    public double calculateFine(int overdueDays) {
        return overdueDays * 5;
    }

    public void printDetails() {
        System.out.println("Resource Type: Book");
        displayBasicDetails();
    }
}


class DigitalResource extends LibraryResource implements Printable {

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 2;
    }

    @Override
    public void printDetails() {
        System.out.println("Resource Type: Digital Resource");
        displayBasicDetails();
    }
}


class InputValidator {

    public static boolean validateResourceId(int id) {
        return id > 0;
    }

    public static boolean validateFineDays(int days) {
        return days >= 0;
    }
}


public class Main {

    public static void main(String[] args) {

        System.out.println("Library: " + LibraryResource.libraryName);
        System.out.println();

        LibraryResource[] resources = new LibraryResource[5];
        int[] overdueDays = new int[5];

        resources[0] = new Book(101, "Java Programming", "Herbert Schildt");
        overdueDays[0] = 4;

        resources[1] = new DigitalResource(102, "Data Structures PDF", "Mark Allen");
        overdueDays[1] = 3;

        resources[2] = new Book(103, "Operating Systems", "Galvin");
        overdueDays[2] = 6;

        resources[3] = new DigitalResource(104, "Database Systems", "Korth");
        overdueDays[3] = 5;

        resources[4] = new Book(105, "Computer Networks", "Andrew Tanenbaum");
        overdueDays[4] = 2;

        double totalFine = 0;

        for (int i = 0; i < resources.length; i++) {

            if (InputValidator.validateResourceId(
                    resources[i].getResourceId())
                    && InputValidator.validateFineDays(overdueDays[i])) {

                System.out.println("----------------------------");

                Printable p = (Printable) resources[i];
                p.printDetails();

                double fine = resources[i].calculateFine(overdueDays[i]);

                System.out.println("Overdue Days: " + overdueDays[i]);
                System.out.println("Fine: Rs. " + fine);

                totalFine = totalFine + fine;
            }
        }

        System.out.println("----------------------------");
        System.out.println("Total Fine of All Resources: Rs. " + totalFine);

        LibraryResource.displayTotalResources();
    }
}
