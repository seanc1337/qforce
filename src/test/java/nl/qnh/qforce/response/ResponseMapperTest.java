package nl.qnh.qforce.response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseMapperTest {

    @Autowired
    ResponseMapper responseMapper;

    @Test
    void mapToSWAPIResponse() {
        String fileLocationSuccess = "./src/test/resources/swapiresultstring";
        String jsonResult = readFileAsString(fileLocationSuccess);
        SWAPIResponse swapiResponse = responseMapper.mapToSWAPIResponse(jsonResult);

        assertNotNull(swapiResponse);
        assertEquals(swapiResponse.getClass(), SWAPIResponse.class);
        assertEquals(swapiResponse.getResults().size(), 10);
        assertEquals(swapiResponse.getCount(), 82);
        assertEquals(swapiResponse.getNext(), "https://swapi.dev/api/people/?page=2");
        assertNull(swapiResponse.getPrevious());
    }

    public String readFileAsString(String file) {
        String fileResult = "";
        try {
            fileResult = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileResult;
    }
}