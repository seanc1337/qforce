package nl.qnh.qforce.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MovieMapperTest {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void mapToMovieModel() throws JsonProcessingException {
        String fileLocationSuccess = "./src/test/resources/swapiresultstringsinglemovie";
        String jsonResult = readFileAsString(fileLocationSuccess);
        SWAPIMovie swapiMovie = objectMapper.readValue(jsonResult, SWAPIMovie.class);

        assertNotNull(swapiMovie);
        assertEquals(SWAPIMovie.class, swapiMovie.getClass());
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