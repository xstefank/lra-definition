package org.xstefank.lra.definition;

import java.util.ArrayList;
import java.util.List;

public class LRABuilder {

    private String name;
    private List<Action> actions = new ArrayList<>();
    private Object data;

    public LRABuilder name(String name) {
        this.name = name;
        return this;
    }

    public LRABuilder withAction(Action action) {
        this.actions.add(action);
        return this;
    }

    public LRABuilder data(Object data) {
        this.data = data;
        return this;
    }

    public LRADefinition build() {
        return new LRADefinitionImpl(name, actions, data);
    }

}
