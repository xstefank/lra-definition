package org.xstefank.lra.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LRAData {

    private String lraId;
    private Object data;

    public LRAData(String lraId, Object data) {
        this.lraId = lraId;
        this.data = data;
    }

    public String getLraId() {
        return lraId;
    }

    public Object getData() {
        return data;
    }
}
