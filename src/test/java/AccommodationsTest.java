import com.holidaymaker.entity.Accommodation;
import com.holidaymaker.service.AccommodationService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccommodationsTest {

    private static int id;
    private static final List<String> requiredTypes = new ArrayList<>(List.of("Hotel", "Hostel", "Cottage"));


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testAccommodationClass() {

        //GIVEN
        Accommodation accommodation = new Accommodation(1, 4);

        //WHEN
        List<String> typesInClass = accommodation.getAllTypes();


        //THEN
        for(int i=0; i<3; i++) {
            String requiredType = requiredTypes.get(i);
            String typeInClass = typesInClass.get(i);
            boolean bedsNotZero = accommodation.getNumberOfBeds() > 0;
            boolean priceNotZero = accommodation.getPrice() >= 0;

            Assertions.assertEquals(requiredType, typeInClass);
            Assertions.assertTrue(bedsNotZero);
            Assertions.assertTrue(priceNotZero);
        }

    }

    @Test
    public void testAccommodationsDb() throws SQLException {

        //GIVEN
        AccommodationService accommodationService = new AccommodationService();

        //WHEN
        List<Accommodation> accommodationsInDatabase = accommodationService.getAllAccommodations();

        //THEN
        for(int i=0; i<3; i++) {
            String accommodationInDatabase = accommodationsInDatabase.get(i).getType();
            String requiredType = requiredTypes.get(i);
            Assertions.assertEquals(requiredType, accommodationInDatabase);
        }

    }

}

