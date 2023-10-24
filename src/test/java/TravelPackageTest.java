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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        String TYPE = "Hotelssss";
        double PRICE = 500;
        int NUMBER_OF_BEDS = 150;
        int TRAVELPACKAGE = 1;

        AccommodationService accommodationService = new AccommodationService();
        Accommodation accommodation = accommodationService.createAccommodation(TYPE, PRICE, NUMBER_OF_BEDS, TRAVELPACKAGE);
        Accommodation foundAccommodation = accommodationService.findAccommodation(TYPE, PRICE, NUMBER_OF_BEDS, TRAVELPACKAGE);

        //When
        Accommodation accommodation1 = accommodationService.createAccommodation(TYPE, PRICE, NUMBER_OF_BEDS, TRAVELPACKAGE);
        Accommodation accommodation2 = accommodationService.createAccommodation(TYPE, PRICE, NUMBER_OF_BEDS + 2, TRAVELPACKAGE);
        Accommodation accommodation3 = accommodationService.createAccommodation(TYPE, PRICE, NUMBER_OF_BEDS + 4, TRAVELPACKAGE);

        Accommodation foundAccommodation1 = accommodationService.findAccommodation(TYPE, PRICE, NUMBER_OF_BEDS, TRAVELPACKAGE);
        Accommodation foundAccommodation2 = accommodationService.findAccommodation(TYPE, PRICE, NUMBER_OF_BEDS + 2, TRAVELPACKAGE);
        Accommodation foundAccommodation3 = accommodationService.findAccommodation(TYPE, PRICE, NUMBER_OF_BEDS + 4, TRAVELPACKAGE);

        //Then
        assertNotNull(foundAccommodation1);
        assertNotNull(foundAccommodation2);
        assertNotNull(foundAccommodation3);

        assertEquals(TYPE, accommodation1.getType());
        assertEquals(PRICE, accommodation2.getPrice());
        assertEquals(NUMBER_OF_BEDS + 4, accommodation3.getNumberOfBeds());
    }

    public void deleteTravelPackageById(int index) throws SQLException {
        String sql = "DELETE FROM travel_packages WHERE id = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, index);

        statement.executeUpdate();
    }



}
