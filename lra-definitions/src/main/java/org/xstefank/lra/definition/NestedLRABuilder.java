package org.xstefank.lra.definition;

@SuppressWarnings(value = "unchecked")
public class NestedLRABuilder<T extends LRABuilder, U extends LRADefinition> {

    private final LRABuilder<T, U> parentBuilder;
    private final LRABuilder<T, U> delegate;

    public NestedLRABuilder(LRABuilder<T, U> parentBuilder) {
        this.parentBuilder = parentBuilder;
        delegate = LRABuilder.lra();
    }

    public NestedLRABuilder<T, U> name(String name) {
        delegate.name(name);
        return this;
    }

    public NestedLRABuilder<T, U> withAction(Action action) {
        delegate.withAction(action);
        return this;
    }

    public NestedLRABuilder<T, U> data(Object data) {
        delegate.data(data);
        return this;
    }

    public T end() {
        return parentBuilder.addNested(delegate.build());
    }

}
