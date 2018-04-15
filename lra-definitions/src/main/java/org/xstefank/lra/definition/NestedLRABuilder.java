package org.xstefank.lra.definition;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = "unchecked")
public class NestedLRABuilder<T extends LRABuilder, U extends LRADefinition, V extends Action> {

    private final LRABuilder<T, U, V> parentBuilder;
    private final LRABuilder<T, U, V> delegate;

    public NestedLRABuilder(LRABuilder<T, U, V> parentBuilder) {
        this.parentBuilder = parentBuilder;
        delegate = LRABuilder.lra();
    }

    public NestedLRABuilder<T, U, V> name(String name) {
        delegate.name(name);
        return this;
    }

    public NestedLRABuilder<T, U, V> withAction(V action) {
        delegate.withAction(action);
        return this;
    }

    public NestedLRABuilder<T, U, V> data(Object data) {
        delegate.data(data);
        return this;
    }

    public NestedLRABuilder<T, U, V> parentLRA(String parentLRA) {
        delegate.parentLRA(parentLRA);
        return this;
    }

    public NestedLRABuilder<T, U, V> parentLRA(URL parentLRA) {
        return parentLRA(parentLRA.toString());
    }

    public NestedLRABuilder<T, U, V> clientId(String clientId) {
        delegate.clientId(clientId);
        return this;
    }

    public NestedLRABuilder<T, U, V> timelimit(long millis) {
        delegate.timelimit(millis);
        return this;
    }

    public NestedLRABuilder<T, U, V> timelimit(long timeout, TimeUnit unit) {
        return timelimit(unit.toMillis(timeout));
    }

    public T end() {
        return parentBuilder.addNested(delegate.build());
    }

}
