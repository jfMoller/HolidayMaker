import com.holidaymaker.service.BookingService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.sql.SQLException;

public class BookingTest {

    private static int main_customer;
    private static String date;
    private static boolean isPayed;
    private static int travel_package;

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testCreateBooking() {

        //Given
        Booking booking = new Booking(main_customer, date, isPayed, travel_package);
        BookingService bookingService = new BookingService();

        //When
        bookingService.addBooking(booking);
        Booking latestBooking = bookingService.fetchLastBooking();

        //Then
        Assertions.assertEquals(booking.getMainCustomer(), latestBooking.getMainCustomer());
        Assertions.assertEquals(booking.getDate(), latestBooking.getDate());
        Assertions.assertEquals(booking.getIsPayed(), latestBooking.getIsPayed());
        Assertions.assertEquals(booking.getTravel_package(), latestBooking.getTravel_package());

    }

    @Test
    public void testDeleteBooking() {

        //Given
        Booking createdBooking =  createAndGetBooking();

        //When
        bookingService.deleteBooking(createdBooking);
        Booking foundBooking = bookingService.findBooking(createdBooking);

        //Then
        assertTrue(foundBooking == null);
        assertTrue(createdBooking == null);
    }

    public Booking createAndGetBooking() {
        Booking booking = new Booking(main_customer, date, isPayed, travel_package);
        BookingService bookingService = new BookingService();
        bookingService.addBooking(booking);
        Booking latestBooking = bookingService.fetchLastBooking();
        return latestBooking;
    }
}
