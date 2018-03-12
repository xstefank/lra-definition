package org.xstefank.lra.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.URL;

@NoArgsConstructor
@ToString
public class LRAData {

    private URL lraId;
    private Object data;

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
}
