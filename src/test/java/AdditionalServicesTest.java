import com.holidaymaker.entity.AdditionalService;
import com.holidaymaker.service.AdditionalServicesService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionalServicesTest {

    private static final double PRICE = 666.666;
    private static final String DESCRIPTION = "keyboardcat-gwFE8FW7W98efsg";

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
        createTestAdditionalService();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        deleteTestAdditionalService();
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testAdditionalServices() throws SQLException {
        // Given
        AdditionalServicesService additionalServicesService = new AdditionalServicesService();

        // When
        List<AdditionalService> additionalServices = additionalServicesService.getAdditionalServices();
        AdditionalService lastAdditionalService =
                additionalServices.get(additionalServices.size() - 1);

        // Then
        assertEquals(PRICE, lastAdditionalService.getPrice());
        assertEquals(DESCRIPTION, lastAdditionalService.getDescription());
    }

    public void createTestAdditionalService() throws SQLException {
        AdditionalServicesService additionalServicesService = new AdditionalServicesService();
        AdditionalService testAdditionalService =
                new AdditionalService(PRICE, DESCRIPTION);
        additionalServicesService.addAdditionalService(testAdditionalService);
    }

    public void deleteTestAdditionalService() throws SQLException {
        String sql = "DELETE FROM additional_services WHERE description = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, DESCRIPTION);

        statement.executeUpdate();
    }
}
