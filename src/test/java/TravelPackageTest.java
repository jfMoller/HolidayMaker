import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.service.TravelPackageService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelPackageTest {
    private static int id;
    private static final int PRICE = 10000;
    private static final int THEME = 1;
    private static final String DESTINATION = "Planet Mars";
    private static final int AVAILABLE_SPOTS = 12;
    private static final String START_DATE = "2023-12-01 00:00:00";
    private static final String END_DATE = "2024-01-05 00:00:30";


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }

    public void createTravelPackage() throws SQLException {
        TravelPackageService travelPackageService = new TravelPackageService();
        TravelPackage travelPackage = new TravelPackage(PRICE, THEME, DESTINATION, AVAILABLE_SPOTS, START_DATE, END_DATE);
        travelPackageService.addTravelPackage(travelPackage);
    }


    @Test
    public void isPackageSavedInDB() throws SQLException {

        //Given
        TravelPackageService travelPackageService = new TravelPackageService();
        createTravelPackage();

        //When
        List<TravelPackage> travelPackages = travelPackageService.getAllTravelPackages();
        TravelPackage lastTravelPackage = travelPackages.get(travelPackages.size() -1);
        id = lastTravelPackage.getId();

        //Then
        assertEquals(id, lastTravelPackage.getId());
        assertEquals(PRICE, lastTravelPackage.getPrice());
        assertEquals(THEME, lastTravelPackage.getTheme());
        assertEquals(DESTINATION, lastTravelPackage.getDestination());
        assertEquals(AVAILABLE_SPOTS, lastTravelPackage.getAvailableSpots());
        assertEquals(START_DATE, lastTravelPackage.getStartDate());
        assertEquals(END_DATE, lastTravelPackage.getEndDate());

        deleteTravelPackageById(id);
    }


    @Test
    public void testConversionOfThemeToString() throws SQLException {

        //Given
        TravelPackageService travelPackageService = new TravelPackageService();
        int sportThemeId = 1;
        int cultureThemeId = 2;
        int natureThemeId = 3;

        //When
        String sportTheme = travelPackageService.printThemeAsString(sportThemeId);
        String cultureTheme = travelPackageService.printThemeAsString(cultureThemeId);
        String natureTheme = travelPackageService.printThemeAsString(natureThemeId);

        //Then
        assertEquals("Sport", sportTheme);
        assertEquals("Culture", cultureTheme);
        assertEquals("Nature", natureTheme);

    }





    public void deleteTravelPackageById(int index) throws SQLException {
        String sql = "DELETE FROM travel_packages WHERE id = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, index);

        statement.executeUpdate();
    }



}
