import com.holidaymaker.entity.Activity;
import com.holidaymaker.service.ActivityService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityTest {

    private static int id;
    private static int price;
    private static int travel_package;
    private static final List<String> type = new ArrayList<>(List.of("Fallskärmshoppning", "Klättring", "Drakflygning"));


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testActivityClass() throws SQLException {
        //Given
        ActivityService activityService = new ActivityService();


        //When
        List<Activity> typeInList = activityService.getActivities();

        //Then
        List<Activity> activityInDatabase = typeInList;

        for (int i = 0; i < type.size(); i++) {
            String localType = type.get(i);
            boolean containsType = false;

            for (Activity activity : activityInDatabase) {
                if (activity.getType().equals(localType)) {
                    containsType = true;
                    break;
                }
            }

            Assertions.assertTrue(containsType);
        }

    }
}
