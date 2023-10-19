import com.holidaymaker.entity.Theme;
import com.holidaymaker.service.ThemeService;
import com.holidaymaker.utility.ConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeTest {
    private static int id;
    private static final List<String> requiredTypes = new ArrayList<>(List.of("Sport", "Culture", "Nature"));


    @BeforeEach
    public void connect() throws SQLException {
        ConnectionProvider.openConnection();
    }

    @AfterEach
    public void disconnect() throws SQLException {
        ConnectionProvider.closeConnection();
    }

    @Test
    public void testThemeClass() {

        //Given
        Theme theme = new Theme();

        //When
        List<String> typesInClass = theme.getAllTypes();

        //Then
        for(int i=0; i<3; i++) {
            String requiredType = requiredTypes.get(i);
            String typeInClass = typesInClass.get(i);
            Assertions.assertEquals(requiredType, typeInClass);
        }

    }

    @Test
    public void testThemes() throws SQLException {

        //Given
        Theme theme = new Theme();
        ThemeService themeService = new ThemeService();

        //When
        List<Theme> themesInDatabase = themeService.getAllThemes();
        List<String> typesInClass = theme.getAllTypes();

        //Then
        for(int i=0; i<3; i++) {
            String themeInDatabase = themesInDatabase.get(i).getType();
            String typeInClass = typesInClass.get(i);
            Assertions.assertEquals(typeInClass, themeInDatabase);
        }
    }
}
