import com.holidaymaker.entity.Accommodation;
import com.holidaymaker.entity.ActivitiesList;
import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.service.AccommodationService;
import com.holidaymaker.service.TravelPackageService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelPackageTest {
    private static int id;
    private static final int PRICE = 10000;
    private static final int THEME = 1;
    private static final String DESTINATION = "Planet Mars";
    private static final int AVAILABLE_SPOTS = 12;
    private static final String START_DATE = "2023-12-01 00:00:00";
    private static final String END_DATE = "2024-01-05 00:00:30";
    String ACCOMMODATION_TYPE = "Hotelssss";
    double ACCOMMODATION_PRICE = 500;
    int ACCOMMODATION_NUMBER_OF_BEDS = 150;
    int ACCOMMODATION_TRAVELPACKAGE = 1;


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

    //Write test to see additionalservices
    @Test
    public void seeAdditionalServices() {

    }

    //write test to see activities
    @Test
    public void seeActivitiesInTravelPackage() throws SQLException {
        //Given
        TravelPackage travelPackage = new TravelPackage(PRICE, THEME, DESTINATION, AVAILABLE_SPOTS, START_DATE, END_DATE);
        TravelPackageService travelPackageService = new TravelPackageService();
        ActivitiesList activitiesList = new ActivitiesList();

        //When
        int travelPackageId = 1;
        ActivitiesList activities = travelPackageService.fetchActivities(travelPackageId);

        //Then - check activities
        assertEquals("Drakflygning", activities.getActivities().get(0).type());
        assertEquals("Klättring", activities.getActivities().get(1).type());
        assertEquals("Fallskärmshoppning", activities.getActivities().get(2).type());

        //Then - check prices
        assertEquals(3499, activities.getActivities().get(0).price());
        assertEquals(1399, activities.getActivities().get(1).price());
        assertEquals(3999, activities.getActivities().get(2).price());

    }

    //write test to see accomodations

    @Test
    public void createAccommodationsInTravelPackage() throws SQLException {

        //Given
        AccommodationService accommodationService = new AccommodationService();
        Accommodation accommodation = createMockAccommodation(0);

        //When
        accommodationService.createAccommodation(accommodation);
        int accommodationId = accommodationService.getAccommodationId(accommodation);

        Accommodation foundAccommodation = accommodationService.findAccommodationById(accommodationId);

        //Then
        assertNotNull(foundAccommodation);
        assertEquals(ACCOMMODATION_TYPE, accommodation.getType());
        assertEquals(ACCOMMODATION_NUMBER_OF_BEDS, accommodation.getNumberOfBeds());
        assertEquals(ACCOMMODATION_PRICE, accommodation.getPrice());

        deleteAccommodationByType(ACCOMMODATION_TYPE);
    }

    @Test
    public void createAdditionalServices() {
        //Given
        //When
        //Then
    }


    public Accommodation createMockAccommodation(int extraBeds) {

        return new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS + extraBeds,
                ACCOMMODATION_TRAVELPACKAGE);

    }

    public void deleteTravelPackageById(int index) throws SQLException {
        String sql = "DELETE FROM travel_packages WHERE id = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, index);

        statement.executeUpdate();
    }

    public void deleteAccommodationByType(String type) throws SQLException {
        String sql = "DELETE FROM accommodations WHERE type = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, type);

        statement.executeUpdate();
    }




}
