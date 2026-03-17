import java.io.*;
import java.util.*;

// CLASS: RoomInventory
class RoomInventory {

    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
    }

    public void setRoom(String type, int count) {
        rooms.put(type, count);
    }

    public Map<String, Integer> getAllRooms() {
        return rooms;
    }

    public void display() {
        System.out.println("\nCurrent Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

// CLASS: FilePersistenceService
class FilePersistenceService {

    // Save inventory to file
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // Load inventory from file
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String type = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    inventory.setRoom(type, count);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }
}

// MAIN CLASS
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        System.out.println("System Recovery");

        // Load previous data
        service.loadInventory(inventory, filePath);

        // If empty → initialize default
        if (inventory.getAllRooms().isEmpty()) {
            inventory.setRoom("Single", 5);
            inventory.setRoom("Double", 3);
            inventory.setRoom("Suite", 2);
        }

        // Display inventory
        inventory.display();

        // Save current state
        service.saveInventory(inventory, filePath);
    }
}