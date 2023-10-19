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
import java.util.List;

public class AccomodationsTest {

    private static final int ID = 1;

    private static final String TYPE = "Keyboard-qinfioqioqnf";

    private static final String PRICE = "Keyboard-daiamipaf";

    private static final String PEOPLE_HOUSED = "Keyboard-asdasdin";

    private static final int TRAVEL_PACKAGE = 1;

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
        /*createTestAccomodation();*/
    }

    @AfterEach
    public void disconnect() throws SQLException {
        /*deleteTestAccomodation();*/
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testAccomodationClass() throws SQLException {

        //GIVEN
        /*AccomodationService accomodationService = new AccomodationService();*/
        Accomodation accomodation = new Accomodation(TYPE,PRICE,PEOPLE_HOUSED,TRAVEL_PACKAGE);

        //WHEN
       /* List<Accomodation> accomodationList = accomodationService.getAccomodation();
        Accomodation lastAccomodation = accomodations.get(accomodations.size() - 1);*/


        //THEN
        Assertions.assertEquals(TYPE, accomodation.getType());
        Assertions.assertEquals(PRICE, accomodation.getPrice());
        Assertions.assertEquals(PEOPLE_HOUSED, accomodation.getPeopleHoused());
        Assertions.assertEquals(TRAVEL_PACKAGE, accomodation.getTravelPackage());
    }

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


}

