import com.holidaymaker.service.ThemeService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TravelPackageTest {

    private static double price = 10000;
    private static int theme = 1;
    private static String destination = "Planet Mars";
    private static int availableSpots = 12;
    private static Date startDate = new Date(2023, 11, 10);
    private static Date endDate = new Date(2024, 0, 5);


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }


    //Se om travel package sparas i databasen
    @Test
    public void isPackageSavedInDB() {

        //Given
        TravelPackageService travelPackageService = new TravelPackageService;


        //When
        TravelPackage travelPackage = new TravelPackage(price, theme, destination, availableSpots, startDate, endDate);
        travelPackageService.addTravelPackage();

        //Then
        List<Package> packages = travelPackageService.getTravelPackages();
    }


    // Om travel package kan visas från databasen

    @Test
    public void showTravelPackages(){
        //Given
        //When
        //THen
    }

}
