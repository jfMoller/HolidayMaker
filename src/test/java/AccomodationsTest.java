import com.holidaymaker.entity.Accomodation;
import com.holidaymaker.entity.Customer;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccomodationsTest {

    private static int id;
    private static final List<String> requiredTypes = new ArrayList<>(List.of("Hotel", "Hostal", "Cottage"));


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
        //createTestAccomodation();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        //deleteTestAccomodation();
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testAccomodationClass() {

        //GIVEN
        Accomodation accomodation = new Accomodation(-1, 4);

        //WHEN
        List<String> typesInClass = accomodation.getAllTypes();


        //THEN
        for(int i=0; i<3; i++) {
            String requiredType = requiredTypes.get(i);
            String typeInClass = typesInClass.get(i);
            boolean bedsNotZero = accomodation.getNumberOfBeds() > 0;
            boolean priceNotZero = accomodation.getPrice() >= 0;

            Assertions.assertEquals(requiredType, typeInClass);
            Assertions.assertTrue(bedsNotZero);
            Assertions.assertTrue(priceNotZero);
        }

    }

    @Test
    public void testAccomodationDb throw SQLException {
        //GIVEN

        //WHEN

        //THEN

    }



/*
    public void createTestAccomodation() throws SQLException {
        AccomodationService accomodationService = new AccomodationService();
        Accomodation testAccomodation =
                new Accomodation(TYPE, PRICE, PEOPLE_HOUSED, TRAVEL_PACKAGE);
        accomodationService.addAccomodation(testAccomodation);
    }

    public void deleteTestAccomodation() throws SQLException {
        String sql = "DELETE FROM customers WHERE type = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, TYPE);

        statement.executeUpdate();
    }
*/

}

