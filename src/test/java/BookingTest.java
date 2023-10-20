import com.holidaymaker.entity.Booking;
import com.holidaymaker.service.BookingService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class BookingTest {

    private static final int main_customer = 1;
    private static final String date = "1890-03-03";
    private static final boolean isPayed = false;
    private static final int travel_package = 1;

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
    public void testFindBooking() {

        //Given
        Booking createdBooking =  createAndGetBooking();

        //When
        Booking foundBooking = bookingService.findBooking(createdBooking);

        //Then
        Assertions.assertEquals(createdBooking.getMainCustomer(), foundBooking.getMainCustomer());
        Assertions.assertEquals(createdBooking.getDate(), foundBooking.getDate());
        Assertions.assertEquals(createdBooking.getIsPayed(), foundBooking.getIsPayed());
        Assertions.assertEquals(createdBooking.getTravel_package(), foundBooking.getTravel_package());
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

    public void clearMockBooking() {

        Booking booking = new Booking(main_customer, date, isPayed, travel_package);
        Booking foundBooking = bookingService.findBooking(createdBooking);

        if(foundBooking != null) {
            bookingService.deleteBooking(foundBooking);
        }
    }


    public Booking createAndGetBooking() {

        Booking booking = new Booking(main_customer, date, isPayed, travel_package);
        BookingService bookingService = new BookingService();
        bookingService.addBooking(booking);
        Booking latestBooking = bookingService.fetchLastBooking();
        return latestBooking;
    }
}
