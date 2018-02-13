package org.xstefank.lra.definition;

import java.util.ArrayList;
import java.util.List;

public class LRABuilder {

    String name;
    List<Action> actions = new ArrayList<>();
    Object data;
    List<LRADefinition> nested = new ArrayList<>();

    protected LRABuilder() {
    }

    public static LRABuilder lra() {
        return new LRABuilder();
    }

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

    public NestedLRABuilder nested() {
        return new NestedLRABuilder(this);
    }

    public LRADefinition build() {
        return new LRADefinitionImpl(name, actions, data, nested);
    }

    protected LRABuilder addNested(LRADefinition lraDefinition) {
        this.nested.add(lraDefinition);
        return this;
    }
}
