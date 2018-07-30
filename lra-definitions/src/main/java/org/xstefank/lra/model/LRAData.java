package org.xstefank.lra.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@ToString
public class LRAData {

    private URL lraId;
    private Object data;
    private Map<String, Object> results = new HashMap<>();

    public LRAData(URL lraId, Object data) {
        this.lraId = lraId;
        this.data = data;
    }

    public URL getLraId() {
        return lraId;
    }

    public Object getData() {
        return data;
    }

    public Map<String, Object> getResults() {
        return Collections.unmodifiableMap(results);
    }
    
    public void addResult(String resultName, Object value) {
        results.put(resultName, value);
    }
}
