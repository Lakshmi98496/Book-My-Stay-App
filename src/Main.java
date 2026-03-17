import java.util.*;

// CLASS: RoomInventory (Shared Resource)
class RoomInventory {

    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 2);
        rooms.put("Double", 1);
    }

    // Synchronized booking method (THREAD SAFE)
    public synchronized boolean bookRoom(String roomType, String user) {

        int available = rooms.getOrDefault(roomType, 0);

        if (available > 0) {
            System.out.println(user + " booking confirmed for " + roomType);
            rooms.put(roomType, available - 1);
            return true;
        } else {
            System.out.println(user + " booking failed for " + roomType);
            return false;
        }
    }

    public void display() {
        System.out.println("\nFinal Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

// CLASS: ConcurrentBookingProcessor (Thread)
class ConcurrentBookingProcessor extends Thread {

    private RoomInventory inventory;
    private String user;
    private String roomType;

    public ConcurrentBookingProcessor(RoomInventory inventory, String user, String roomType) {
        this.inventory = inventory;
        this.user = user;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        inventory.bookRoom(roomType, user);
    }
}

// MAIN CLASS
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Multiple users trying to book simultaneously
        Thread t1 = new ConcurrentBookingProcessor(inventory, "User1", "Single");
        Thread t2 = new ConcurrentBookingProcessor(inventory, "User2", "Single");
        Thread t3 = new ConcurrentBookingProcessor(inventory, "User3", "Single");

        Thread t4 = new ConcurrentBookingProcessor(inventory, "User4", "Double");
        Thread t5 = new ConcurrentBookingProcessor(inventory, "User5", "Double");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        // Final inventory
        inventory.display();
    }
}
