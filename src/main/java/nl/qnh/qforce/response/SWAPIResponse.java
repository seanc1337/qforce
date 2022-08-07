package nl.qnh.qforce.response;

import nl.qnh.qforce.person.SWAPIPerson;

import java.util.ArrayList;
import java.util.List;

public class SWAPIResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<SWAPIPerson> results = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SWAPIPerson> getResults() {
        return results;
    }

    public void setResults(List<SWAPIPerson> results) {
        this.results = results;
    }
}
