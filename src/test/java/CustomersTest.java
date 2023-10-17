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

    private static final String TEST_CUSTOMER_NAME = "TestCustomer keyboardcat-fwrwgwrgwg475226wbwr";

    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
        CustomerService customerService = new CustomerService();
        Customer newCustomer = new Customer(TEST_CUSTOMER_NAME);
        customerService.addCustomer(newCustomer);
    }

    @AfterEach
    public void disconnect() throws SQLException {
        deleteTestCustomer();
        ConnectionProvider.closeConnection();
        // Ta bort samma customer
    }

    @Test
    public void testExistingCustomers() throws SQLException {
        // Given
        CustomerService customerService = new CustomerService();

        // When
        List<Customer> customers = customerService.getCustomers();

        // Then
        Customer lastCustomer = customers.get(customers.size() - 1);
        Assertions.assertEquals(lastCustomer.getName(), TEST_CUSTOMER_NAME);
    }

    public void deleteTestCustomer() throws SQLException {
        String sql = "DELETE FROM customers WHERE name = (?)";

        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, TEST_CUSTOMER_NAME);

        statement.executeUpdate();
    }
}
