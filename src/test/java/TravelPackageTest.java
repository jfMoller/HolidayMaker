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
        Assertions.assertEquals(id, lastTravelPackage.getId());
        Assertions.assertEquals(PRICE, lastTravelPackage.getPrice());
        Assertions.assertEquals(THEME, lastTravelPackage.getTheme());
        Assertions.assertEquals(DESTINATION, lastTravelPackage.getDestination());
        Assertions.assertEquals(AVAILABLE_SPOTS, lastTravelPackage.getAvailableSpots());
        Assertions.assertEquals(START_DATE, lastTravelPackage.getStartDate());
        Assertions.assertEquals(END_DATE, lastTravelPackage.getEndDate());

        deleteTravelPackageById(id);
    }

    @Test
    public void testTypeOfTheme() throws SQLException {

        // Vi vill hämta hem alla travel-packages från databasen och kolla ifall dom har ett tema som stämmer överens med ett tema som finns i tema-tabellen
        //Given
        TravelPackageService travelPackageService = new TravelPackageService();

        //When
        List <TravelPackage> travelPackages = travelPackageService.getAllTravelPackages();
        List<TravelPackage> themeInDatabase = travelPackages;

        for(int i= 0; i < travelPackages.size(); i++) {
            int value = travelPackages.get(i).getTheme();
            boolean containsNumber = false;

            for(TravelPackage travelPackage : themeInDatabase) {
                if(travelPackage.getTheme().compareTo(value == 1) ) {
                    containsNumber = true;
                    break;
                }
            }
            Assertions.assertEquals(containsNumber);
    }


        //Then


    }

    public void deleteTravelPackageById(int index) throws SQLException {
        String sql = "DELETE FROM travel_packages WHERE id = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, index);

        statement.executeUpdate();
    }



}
