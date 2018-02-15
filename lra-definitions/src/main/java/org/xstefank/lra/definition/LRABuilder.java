package org.xstefank.lra.definition;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings(value = "unchecked")
public class LRABuilder<T extends LRABuilder, U extends LRADefinition> {

    String name;
    List<Action> actions = new ArrayList<>();
    Object data;
    List<LRADefinition> nested = new ArrayList<>();

    protected LRABuilder() {
    }

    public static LRABuilder lra() {
        return new LRABuilder();
    }

    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    public T withAction(Action action) {
        this.actions.add(action);
        return (T) this;
    }

    public T data(Object data) {
        this.data = data;
        return (T) this;
    }

    public NestedLRABuilder<T, U> nested() {
        return new NestedLRABuilder(this);
    }

    public T nested(LRADefinition lraDefinition) {
        return (T) addNested(lraDefinition);
    }

    public U build() {
        return (U) new LRADefinitionImpl(name, actions, data, nested);
    }

    protected T addNested(LRADefinition lraDefinition) {
        this.nested.add(lraDefinition);
        return (T) this;
    }
}
