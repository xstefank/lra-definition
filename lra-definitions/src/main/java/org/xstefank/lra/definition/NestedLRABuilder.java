package org.xstefank.lra.definition;

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

    public T end() {
        return parentBuilder.addNested(delegate.build());
    }

}
