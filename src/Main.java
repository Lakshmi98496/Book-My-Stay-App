import java.util.*;

// CLASS 1: BookingHistory
class BookingHistory {

    private List<String> confirmedReservations;

    // Constructor
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add reservation
    public void addReservation(String reservation) {
        confirmedReservations.add(reservation);
    }

    // Get reservations
    public List<String> getReservations() {
        return confirmedReservations;
    }
}

// CLASS 2: BookingReportService
class BookingReportService {

    // Generate report
    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History and Reporting\n");

        for (String reservation : history.getReservations()) {
            System.out.println(reservation);
        }
    }
}

// MAIN CLASS
public class BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Adding reservations
        history.addReservation("Guest: Adhi, Room Type: Single");
        history.addReservation("Guest: John, Room Type: Double");
        history.addReservation("Guest: Vanathi, Room Type: Suite");

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}
