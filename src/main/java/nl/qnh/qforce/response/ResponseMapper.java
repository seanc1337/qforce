package nl.qnh.qforce.response;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseMapper {

    private final ObjectMapper objectMapper;

    public ResponseMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
