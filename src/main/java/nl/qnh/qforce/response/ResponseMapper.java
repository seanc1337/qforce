package nl.qnh.qforce.response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Mapper class for mapping Star Wars API result String to SWAPIResponse
 * @author Sean
 */
public class ResponseMapper {

    private final ObjectMapper objectMapper;

    public ResponseMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Mappping Star Wars API result String to SWAPIResponse
     * @param result the Star Wars API result string
     * @return the SWAPIResponse
     */
    public SWAPIResponse mapToSWAPIResponse(String result){
        SWAPIResponse response = new SWAPIResponse();
        try {
            response = objectMapper.readValue(result, SWAPIResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
