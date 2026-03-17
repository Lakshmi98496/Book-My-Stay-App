import java.util.*;

// CLASS 1: AddOnService
class AddOnService {

    private String serviceName;
    private double cost;

    // Constructor
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// CLASS 2: AddOnServiceManager
class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    // Constructor
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        if (servicesByReservation.containsKey(reservationId)) {
            for (AddOnService s : servicesByReservation.get(reservationId)) {
                total += s.getCost();
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        System.out.println("Services for Reservation ID: " + reservationId);

        if (servicesByReservation.containsKey(reservationId)) {
            for (AddOnService s : servicesByReservation.get(reservationId)) {
                System.out.println(s.getServiceName() + " - ₹" + s.getCost());
            }
        } else {
            System.out.println("No services added.");
        }
    }
}

// MAIN CLASS
public class AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 200));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 500));
        manager.addService(reservationId, new AddOnService("Spa Access", 800));

        // Display services
        manager.displayServices(reservationId);

        // Total cost
        double total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-on Cost: ₹" + total);
    }
}