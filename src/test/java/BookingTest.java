import com.holidaymaker.entity.Booking;
import com.holidaymaker.service.BookingService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private static final int MAIN_CUSTOMER = 1;
    private static final String DATE = "1890-03-03 14:02:01";
    private static final boolean IS_PAYED = false;
    private static final int TRAVEL_PACKAGE = 1;

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        clearMockBooking();
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testCreateBooking() throws SQLException {

        //Given
        Booking booking = new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE);
        BookingService bookingService = new BookingService();

        //When
        bookingService.addBooking(booking);
        Booking latestBooking = bookingService.fetchLastBooking();

        //Then
        assertEquals(booking.getMainCustomer(), latestBooking.getMainCustomer());
        assertEquals(booking.getDate(), latestBooking.getDate());
        assertEquals(booking.getIsPayed(), latestBooking.getIsPayed());
        assertEquals(booking.getTravelPackage(), latestBooking.getTravelPackage());

    }

    @Test
    public void testFindBooking() throws SQLException {

        //Given
        Booking createdBooking = createAndGetBooking();
        BookingService bookingService = new BookingService();

        //When
        Booking foundBooking = bookingService.findBooking(createdBooking);

        //Then
        assertEquals(createdBooking.getMainCustomer(), foundBooking.getMainCustomer());
        assertEquals(createdBooking.getDate(), foundBooking.getDate());
        assertEquals(createdBooking.getIsPayed(), foundBooking.getIsPayed());
        assertEquals(createdBooking.getTravelPackage(), foundBooking.getTravelPackage());
    }

    @Test
    public void testDeleteBooking() throws SQLException {

        //Given
        Booking createdBooking = createAndGetBooking();
        BookingService bookingService = new BookingService();

        //When
        bookingService.deleteBooking(createdBooking);
        Booking foundBooking = bookingService.findBooking(createdBooking);

        //Then
        assertNull(foundBooking);
    }

    @Test
    public void TestGetBookings() throws SQLException {

        //Given
        List<Booking> bookings = new ArrayList<>(List.of(new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE),
                new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE),
                new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE)));

        BookingService bookingService = new BookingService();

        //When
        for (Booking booking : bookings ) {
            bookingService.addBooking(booking);
        }
        List<Booking> fetchBookings = bookingService.getAllBookings();

        //Then

        for ( Booking createdBooking : bookings ) {

            for (Booking fetchBooking : fetchBookings) {
                assertEquals(createdBooking.getMainCustomer(), fetchBooking.getMainCustomer());
                assertEquals(createdBooking.getDate(), fetchBooking.getDate());
                assertEquals(createdBooking.getIsPayed(), fetchBooking.getIsPayed());
                assertEquals(createdBooking.getTravelPackage(), fetchBooking.getTravelPackage());
            }
        }
    }

    @Test
    public void TestVerifyIsPayed() throws SQLException {

        //Given
        Booking createdBooking = createAndGetBooking();
        BookingService bookingService = new BookingService();

        //When
        int bookingId = bookingService.findBookingId(createdBooking);
        bookingService.verifyPayment(bookingId);
        Boolean isPayed = bookingService.getPayStatus(bookingId);

        //Then
        assertTrue(isPayed);

    }

    public void clearMockBooking() throws SQLException {

        Booking booking = new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE);
        BookingService bookingService = new BookingService();
        Booking foundBooking = bookingService.findBooking(booking);

        if (foundBooking != null) {
            bookingService.deleteBooking(foundBooking);
        }
    }


    public Booking createAndGetBooking() throws SQLException {

        Booking booking = new Booking(MAIN_CUSTOMER, DATE, IS_PAYED, TRAVEL_PACKAGE);
        BookingService bookingService = new BookingService();
        bookingService.addBooking(booking);

        return bookingService.fetchLastBooking();
    }
}
