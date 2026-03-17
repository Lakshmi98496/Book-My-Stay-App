import java.util.*;

// CLASS: RoomInventory
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    // Reduce inventory when booking
    public void bookRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    // Restore inventory on cancellation
    public void restoreRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("Updated Single Room Availability: " + inventory.get("Single"));
    }
}

// CLASS: CancellationService
class CancellationService {

    private Stack<String> cancellationStack;
    private Map<String, String> reservationMap;

    public CancellationService() {
        cancellationStack = new Stack<>();
        reservationMap = new HashMap<>();
    }

    // Add reservation
    public void addReservation(String reservationId, String roomType) {
        reservationMap.put(reservationId, roomType);
    }

    // Cancel booking and rollback inventory
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        String roomType = reservationMap.get(reservationId);

        // Push to stack (for history)
        cancellationStack.push(reservationId);

        // Restore inventory
        inventory.restoreRoom(roomType);

        // Remove reservation
        reservationMap.remove(reservationId);

        System.out.println("\nBooking cancelled successfully.");
        System.out.println("Inventory restored for room type: " + roomType);

        // Show rollback history
        System.out.println("\nRollback History (Most Recent First):");
        for (int i = cancellationStack.size() - 1; i >= 0; i--) {
            System.out.println(cancellationStack.get(i));
        }
    }
}

// MAIN CLASS
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate booking
        String reservationId = "R101";
        String roomType = "Single";

        service.addReservation(reservationId, roomType);
        inventory.bookRoom(roomType);

        // Cancel booking
        service.cancelBooking(reservationId, inventory);

        // Show updated inventory
        inventory.displayInventory();
    }
}