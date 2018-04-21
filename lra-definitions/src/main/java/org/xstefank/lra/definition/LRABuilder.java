package org.xstefank.lra.definition;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = "unchecked")
public class LRABuilder<T extends LRABuilder, U extends LRADefinition, V extends Action> {

    protected String name;
    protected List<V> actions = new ArrayList<>();
    protected Object data;
    protected List<LRADefinition> nested = new ArrayList<>();
    protected String parentLRA;
    protected String clientId;
    protected long timelimit;

    protected LRABuilder() {
    }

    public static LRABuilder lra() {
        return new LRABuilder();
    }

    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    public T withAction(V action) {
        this.actions.add(action);
        return (T) this;
    }

    public T data(Object data) {
        this.data = data;
        return (T) this;
    }

    public NestedLRABuilder<T, U, V> nested() {
        return new NestedLRABuilder(this);
    }

    public T nested(LRADefinition lraDefinition) {
        return (T) addNested(lraDefinition);
    }

    public T parentLRA(String parentLRA) {
        this.parentLRA = parentLRA;
        return (T) this;
    }

    public T parentLRA(URL parentLRA) {
        this.parentLRA = parentLRA.toString();
        return (T) this;
    }

    public T clientId(String clientId) {
        this.clientId = clientId;
        return (T) this;
    }

    public T timelimit(long millis) {
        this.timelimit = millis;
        return (T) this;
    }

    public T timelimit(long timelimit, TimeUnit unit) {
        return timelimit(unit.toMillis(timelimit));
    }

    public U build() {
        return (U) new LRADefinitionImpl(name, actions, data, nested, parentLRA, clientId, timelimit);
    }

    protected T addNested(LRADefinition lraDefinition) {
        this.nested.add(lraDefinition);
        return (T) this;
    }
}
