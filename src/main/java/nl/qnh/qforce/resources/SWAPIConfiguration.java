package nl.qnh.qforce.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swapi")
public class SWAPIConfiguration {
    private String baseURL;
    private String peopleEndpoint;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getPeopleEndpoint() {
        return peopleEndpoint;
    }

    public void setPeopleEndpoint(final String peopleEndpoint) {
        this.peopleEndpoint = peopleEndpoint;
    }
}
