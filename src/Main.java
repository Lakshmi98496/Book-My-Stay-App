/
public abstract class Main {

    /** Number of beds available in the room. */
    protected int numberOfBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to
     * initialize common room attributes.
     *
     * @param numberOfBeds number of beds in the room
     * @param squareFeet total room size
     * @param pricePerNight cost per night
     */
    public Main(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details */
    public void displayRoomDetails() {
        System.out.println("Room Details:");
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Room Size: " + squareFeet + " sq.ft");
        System.out.println("Price per Night: ₹" + pricePerNight);
    }
}


public class Main {


    public static void main(String[] args) {

        // Creating room inventory
        Room room1 = new Room(1, 200, 2500);
        Room room2 = new Room(2, 350, 4000);
        Room room3 = new Room(3, 500, 6000);

        // Centralized inventory (array)
        Room[] inventory = {room1, room2, room3};

        System.out.println("Hotel Room Inventory");
        System.out.println("---------------------");

        // Display all room details
        for (Room room : inventory) {
            room.displayRoomDetails();
            System.out.println();
        }
    }
}
/**
 * =========================================================
 * MAIN CLASS – UseCase4RoomSearch
 * =========================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class demonstrates how guests
 * can view available rooms without
 * modifying inventory data.
 *
 * The system enforces read-only access
 * by design and usage discipline.
 *
 * @version 4.0
 */

public class Room {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Creating room inventory
        Room[] inventory = {
                new Room(1, 200, 2500),
                new Room(2, 350, 4000),
                new Room(3, 500, 6000)
        };

        System.out.println("Available Rooms");
        System.out.println("----------------");

        // Display available rooms
        for (Room room : inventory) {
            room.displayRoomDetails();
            System.out.println();
        }
    }
}