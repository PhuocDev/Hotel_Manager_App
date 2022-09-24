package Connection;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBconnectionTest {
    @Test
    public void testDBconnectionShouldReturnTrue() throws SQLException {
        assertEquals("Wellcome to Database", DBconnection.testDB());
    }
}