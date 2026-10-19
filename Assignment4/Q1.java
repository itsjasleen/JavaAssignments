// Name: Jasleen | Roll No: 1024170091 | Assignment 4
// Q1: Course registration portal (HashMap + HashSet + ArrayList + LinkedList)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Course {
    private final String code;
    private final String title;
    private final int capacity;
    private final Set<String> enrolled = new HashSet<>();              // unique student IDs
    private final List<String> prerequisites = new ArrayList<>();      // ordered prerequisite codes
    private final LinkedList<String> waitlist = new LinkedList<>();    // FCFS waitlist queue

    public Course(String code, String title, int capacity, List<String> prerequisites) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.prerequisites.addAll(prerequisites);
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public Set<String> getEnrolled() { return enrolled; }
    public List<String> getPrerequisites() { return prerequisites; }
    public LinkedList<String> getWaitlist() { return waitlist; }
    public boolean isFull() { return enrolled.size() >= capacity; }

    @Override
    public String toString() {
        return code + " (" + title + ") | Enrolled " + enrolled.size() + "/" + capacity
                + " " + enrolled + " | Waitlist " + waitlist + " | Prerequisites " + prerequisites;
    }
}

class RegistrationPortal {
    private final Map<String, Course> courses = new HashMap<>();                 // fast lookup by code
    private final Map<String, Set<String>> completedCourses = new HashMap<>();   // studentId -> completed codes

    public void addCourse(Course c) {
        courses.put(c.getCode(), c);
    }

    public void markCompleted(String studentId, String courseCode) {
        completedCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseCode);
    }

    private boolean hasPrerequisites(String studentId, Course c) {
        Set<String> done = completedCourses.getOrDefault(studentId, new HashSet<>());
        for (String pre : c.getPrerequisites()) {
            if (!done.contains(pre)) {
                System.out.println("  Missing prerequisite: " + pre);
                return false;
            }
        }
        return true;
    }

    public void register(String studentId, String courseCode) {
        System.out.println("Register " + studentId + " -> " + courseCode);
        Course c = courses.get(courseCode);
        if (c == null) {
            System.out.println("  Course not found.");
            return;
        }
        if (c.getEnrolled().contains(studentId)) {
            System.out.println("  Already enrolled.");
            return;
        }
        if (c.getWaitlist().contains(studentId)) {
            System.out.println("  Already on the waitlist.");
            return;
        }
        if (!hasPrerequisites(studentId, c)) {
            System.out.println("  Registration denied.");
            return;
        }
        if (!c.isFull()) {
            c.getEnrolled().add(studentId);
            System.out.println("  Enrolled successfully.");
        } else {
            c.getWaitlist().addLast(studentId);
            System.out.println("  Course full. Added to waitlist at position " + c.getWaitlist().size() + ".");
        }
    }

    public void drop(String studentId, String courseCode) {
        System.out.println("Drop " + studentId + " <- " + courseCode);
        Course c = courses.get(courseCode);
        if (c == null) {
            System.out.println("  Course not found.");
            return;
        }
        if (c.getEnrolled().remove(studentId)) {
            System.out.println("  Dropped successfully.");
            promoteFromWaitlist(c);
        } else if (c.getWaitlist().remove(studentId)) {
            System.out.println("  Removed from waitlist.");
        } else {
            System.out.println("  Student is not registered in this course.");
        }
    }

    // Automated waitlist promotion: first-come, first-served
    private void promoteFromWaitlist(Course c) {
        while (!c.isFull() && !c.getWaitlist().isEmpty()) {
            String next = c.getWaitlist().pollFirst();
            c.getEnrolled().add(next);
            System.out.println("  Waitlist promotion: " + next + " enrolled in " + c.getCode());
        }
    }

    public void showCourse(String courseCode) {
        Course c = courses.get(courseCode);
        System.out.println(c == null ? "Course not found." : c);
    }
}

public class Q1 {
    public static void main(String[] args) {
        RegistrationPortal portal = new RegistrationPortal();

        portal.addCourse(new Course("CS101", "Intro to Programming", 2, List.of()));
        portal.addCourse(new Course("CS201", "Data Structures", 2, List.of("CS101")));
        portal.addCourse(new Course("CS301", "Algorithms", 1, List.of("CS101", "CS201")));

        // Students who completed prerequisites
        portal.markCompleted("S1", "CS101");
        portal.markCompleted("S2", "CS101");
        portal.markCompleted("S3", "CS101");
        portal.markCompleted("S1", "CS201");

        System.out.println("--- Registrations ---");
        portal.register("S1", "CS101");
        portal.register("S2", "CS101");
        portal.register("S3", "CS101");   // goes to waitlist
        portal.register("S4", "CS101");   // goes to waitlist
        portal.register("S1", "CS101");   // duplicate
        portal.register("S4", "CS201");   // missing prerequisite
        portal.register("S1", "CS301");   // enrolled
        portal.register("S2", "CS999");   // invalid course
        System.out.println();
        portal.showCourse("CS101");

        System.out.println("\n--- Drop and automatic waitlist promotion ---");
        portal.drop("S1", "CS101");       // S3 promoted
        portal.drop("S4", "CS101");       // removed from waitlist
        portal.drop("S2", "CS101");
        System.out.println();
        portal.showCourse("CS101");
    }
}
