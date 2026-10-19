// Name: Jasleen | Roll No: 1024170091 | Lab Assignment 5 (Generics & Utility Classes)
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

// Q4: Event management using LocalDate and LocalTime
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter event name: ");
            String name = sc.nextLine();

            System.out.print("Enter event date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(sc.nextLine().trim());

            System.out.print("Enter start time (HH:mm): ");
            LocalTime start = LocalTime.parse(sc.nextLine().trim());

            System.out.print("Enter venue (press Enter to skip): ");
            String v = sc.nextLine().trim();
            Optional<String> venue = v.isEmpty() ? Optional.empty() : Optional.of(v);

            LocalTime end = start.plusHours(2);
            DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a");

            System.out.println("\n--- Event Details ---");
            System.out.println("Event:      " + name);
            System.out.println("Date:       " + date);
            System.out.println("Start time: " + start.format(tf));
            System.out.println("End time:   " + end.format(tf) + " (2-hour event)");
            System.out.println("Venue:      " + venue.orElse("Venue not specified"));

            LocalDate today = LocalDate.now();
            if (date.isAfter(today)) {
                System.out.println("Status:     Event is in the future");
                System.out.println("Days remaining: " + ChronoUnit.DAYS.between(today, date));
            } else if (date.isEqual(today)) {
                System.out.println("Status:     Event is today");
                System.out.println("Days remaining: 0");
            } else {
                System.out.println("Status:     Event is in the past");
                System.out.println("Days since event: " + ChronoUnit.DAYS.between(date, today));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
