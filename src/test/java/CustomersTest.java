import com.holidaymaker.entity.Customer;
import com.holidaymaker.service.CustomerService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomersTest {

    private static final String FIRST_NAME = "keyboardcat-fwrwgwrgwg475226wbwr";
    private static final String LAST_NAME = "keyboardcat-gw87sgewwgwr8g";
    private static final String PHONE_NUMBER = "keyboardcat-w9g7wrg5egw7eg";
    private static final String PERSONAL_NUMBER = "keyboardcat-kwgeq6gw74w6";
    private static final String EMAIL = "keyboardcat-fwrceq824t2fwbwr";
    private static final String PASSPORT_NUMBER = "keyboardcat-flf2jwvnew4356wbwr";

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
        createTestCustomer();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        deleteTestCustomer();
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testLastCustomer() throws SQLException {
        // Given
        CustomerService customerService = new CustomerService();

        // When
        List<Customer> customers = customerService.getCustomers();
        Customer lastCustomer = customers.get(customers.size() - 1);

        // Then
        Assertions.assertEquals(FIRST_NAME, lastCustomer.getFirstName());
        Assertions.assertEquals(LAST_NAME, lastCustomer.getLastName());
        Assertions.assertEquals(PHONE_NUMBER, lastCustomer.getPhoneNumber());
        Assertions.assertEquals(PERSONAL_NUMBER, lastCustomer.getPersonalNumber());
        Assertions.assertEquals(EMAIL, lastCustomer.getEmail());
        Assertions.assertEquals(PASSPORT_NUMBER, lastCustomer.getPassportNumber());
    }

    public void createTestCustomer() throws SQLException {
        CustomerService customerService = new CustomerService();
        Customer testCustomer =
                new Customer(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PERSONAL_NUMBER, EMAIL, PASSPORT_NUMBER);
        customerService.addCustomer(testCustomer);
    }

    public void deleteTestCustomer() throws SQLException {
        String sql = "DELETE FROM customers WHERE personal_number = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, PERSONAL_NUMBER);

        statement.executeUpdate();
    }
}
