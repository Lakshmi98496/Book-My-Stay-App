/import java.util.*;

// CUSTOM EXCEPTION CLASS
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// VALIDATOR CLASS
class ReservationValidator {

    public void validate(String guestName, String roomType, int inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!(roomType.equalsIgnoreCase("Single") ||
                roomType.equalsIgnoreCase("Double") ||
                roomType.equalsIgnoreCase("Suite"))) {
            throw new InvalidBookingException("Invalid room type.");
        }

        if (inventory <= 0) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

// MAIN CLASS
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Simulated inventory
        int inventory = 1;

        ReservationValidator validator = new ReservationValidator();

        try {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(name, roomType, inventory);

            System.out.println("\nBooking successful!");

        } catch (InvalidBookingException e) {
            System.out.println("\nBooking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}