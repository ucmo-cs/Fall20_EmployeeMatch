package TestFiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucmo.spring_example.model.EmployeePreferences;
import edu.ucmo.spring_example.service.DataFromApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataFromApiTest {
    String url = "http://localhost:8080/employeepreferences";
    DataFromApi api = new DataFromApi(url);


    DataFromApiTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getDataString() {
        assertEquals("[{\"userid\":507,\"ew1\":1,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":1,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":508,\"ew1\":0,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":2,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":509,\"ew1\":0,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":2,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":0,\"ew1\":1,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":2,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":44,\"ew1\":1,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":2,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":45,\"ew1\":5,\"ew2\":1,\"ew3\":2,\"ew4\":3,\"ew5\":4,\"eo1\":2,\"eo2\":2,\"eo3\":3,\"eo4\":4,\"eo5\":5},{\"userid\":48,\"ew1\":1,\"ew2\":2,\"ew3\":3,\"ew4\":4,\"ew5\":5,\"eo1\":1,\"eo2\":1,\"eo3\":2,\"eo4\":3,\"eo5\":5}]",
                api.getDataString());
    }

    @Test
    void getDataToEmployeePreferences() {
        assertEquals(48,api.getDataToEmployeePreferences()[6].getUserid());
    }
}