// Name: Jasleen | Roll No: 1024170091 | Assignment 4
// Q2: Media player queue engine using LinkedList<Song> and ListIterator

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;

class Song {
    private final String title;
    private final String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song other = (Song) o;
        return title.equalsIgnoreCase(other.title) && artist.equalsIgnoreCase(other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), artist.toLowerCase());
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}

class PlaybackQueue {
    private final LinkedList<Song> queue = new LinkedList<>();
    private final LinkedList<Song> history = new LinkedList<>();   // most recent first
    private static final int HISTORY_LIMIT = 3;

    // Add to the end of the queue
    public void addSong(Song s) {
        queue.addLast(s);
    }

    // Priority queueing: add to the front
    public void addToFront(Song s) {
        queue.addFirst(s);
    }

    // Insert at an arbitrary position using a ListIterator
    public void insertAt(int position, Song s) {
        if (position < 0 || position > queue.size()) {
            System.out.println("Invalid position: " + position);
            return;
        }
        ListIterator<Song> it = queue.listIterator(position);
        it.add(s);
    }

    // Remove duplicate entries in a single pass (keeps the first occurrence)
    public int removeDuplicates() {
        Set<Song> seen = new HashSet<>();
        int removed = 0;
        Iterator<Song> it = queue.iterator();
        while (it.hasNext()) {
            if (!seen.add(it.next())) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    // Play the next song from the front of the queue and record it in history
    public void playNext() {
        Song s = queue.pollFirst();
        if (s == null) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Now playing: " + s);
        history.addFirst(s);
        if (history.size() > HISTORY_LIMIT) {
            history.removeLast();
        }
    }

    public void showHistory() {
        System.out.println("Recently played (latest first): " + history);
    }

    // Forward traversal using ListIterator
    public void traverseForward() {
        System.out.print("Forward : ");
        ListIterator<Song> it = queue.listIterator();
        while (it.hasNext()) {
            System.out.print("[" + it.next() + "] ");
        }
        System.out.println();
    }

    // Backward traversal using ListIterator (starts at the end)
    public void traverseBackward() {
        System.out.print("Backward: ");
        ListIterator<Song> it = queue.listIterator(queue.size());
        while (it.hasPrevious()) {
            System.out.print("[" + it.previous() + "] ");
        }
        System.out.println();
    }

    public void show() {
        System.out.println("Queue (" + queue.size() + "): " + queue);
    }
}

public class Q2 {
    public static void main(String[] args) {
        PlaybackQueue player = new PlaybackQueue();

        player.addSong(new Song("Believer", "Imagine Dragons"));
        player.addSong(new Song("Shape of You", "Ed Sheeran"));
        player.addSong(new Song("Believer", "Imagine Dragons"));   // duplicate
        player.addSong(new Song("Perfect", "Ed Sheeran"));
        player.addSong(new Song("shape of you", "ed sheeran"));    // duplicate (case-insensitive)
        System.out.println("--- Initial queue ---");
        player.show();

        System.out.println("\n--- Priority queueing (add to front) ---");
        player.addToFront(new Song("Closer", "The Chainsmokers"));
        player.show();

        System.out.println("\n--- Insert at position 2 ---");
        player.insertAt(2, new Song("Faded", "Alan Walker"));
        player.show();

        System.out.println("\n--- Remove duplicates ---");
        System.out.println("Duplicates removed: " + player.removeDuplicates());
        player.show();

        System.out.println("\n--- Traversal with ListIterator ---");
        player.traverseForward();
        player.traverseBackward();

        System.out.println("\n--- Playback and history ---");
        player.playNext();
        player.playNext();
        player.playNext();
        player.playNext();
        player.showHistory();
        player.show();
    }
}
