package org.xstefank.lra.definition;

public class NestedLRABuilder {

    private final LRABuilder parentBuilder;
    private final LRABuilder delegate;

    public NestedLRABuilder(LRABuilder parentBuilder) {
        this.parentBuilder = parentBuilder;
        delegate = LRABuilder.lra();
    }

    public NestedLRABuilder name(String name) {
        delegate.name(name);
        return this;
    }

    public NestedLRABuilder withAction(Action action) {
        delegate.withAction(action);
        return this;
    }

    public NestedLRABuilder data(Object data) {
        delegate.data(data);
        return this;
    }

    public NestedLRABuilder nested() {
        return new NestedLRABuilder(this.delegate);
    }

    public LRABuilder end() {
        return parentBuilder.addNested(delegate.build());
    }

}
