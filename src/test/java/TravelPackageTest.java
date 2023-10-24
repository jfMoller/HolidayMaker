import com.holidaymaker.entity.Accommodation;
import com.holidaymaker.entity.ActivitiesList;
import com.holidaymaker.entity.Booking;
import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.service.AccommodationService;
import com.holidaymaker.service.BookingService;
import com.holidaymaker.service.TravelPackageService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Accommodation accommodation = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.createAccommodation(accommodation);
        Accommodation foundAccommodation =  new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.findAccommodation(foundAccommodation);

        //When
        Accommodation accommodation1 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.createAccommodation(accommodation1);
        Accommodation accommodation2 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS + 2,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.createAccommodation(accommodation2);
        Accommodation accommodation3 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS + 4,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.createAccommodation(accommodation3);

        Accommodation foundAccommodation1 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.findAccommodation(foundAccommodation1);
        Accommodation foundAccommodation2 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS + 2,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.findAccommodation(foundAccommodation2);
        Accommodation foundAccommodation3 = new Accommodation(
                ACCOMMODATION_TYPE,
                ACCOMMODATION_PRICE,
                ACCOMMODATION_NUMBER_OF_BEDS + 4,
                ACCOMMODATION_TRAVELPACKAGE);
        accommodationService.findAccommodation(foundAccommodation3);

        //Then
        assertNotNull(foundAccommodation1);
        assertNotNull(foundAccommodation2);
        assertNotNull(foundAccommodation3);

        assertEquals(ACCOMMODATION_TYPE, accommodation1.getType());
        assertEquals(ACCOMMODATION_PRICE, accommodation2.getPrice());
        assertEquals(ACCOMMODATION_NUMBER_OF_BEDS + 4, accommodation3.getNumberOfBeds());

        deleteAccommodationByType(ACCOMMODATION_TYPE);
    }

    /*@Test
    public void testDeleteAccommodation() throws SQLException {

        //Given
        Accommodation createdAccommodation = createAndGetAccommodation();
        AccommodationService accommodationService = new AccommodationService();

        //When
        accommodationService.deleteAccommodation(createdAccommodation);
        Accommodation foundAccommodation = accommodationService.findAccommodation(createdAccommodation);

        //Then
        assertNull(foundAccommodation);
    }*/

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
