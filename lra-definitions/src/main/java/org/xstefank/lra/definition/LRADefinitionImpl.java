package org.xstefank.lra.definition;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@NoArgsConstructor
public class LRADefinitionImpl<T extends Action> implements LRADefinition {

    private String name;
    private List<T> actions;
    private Object data;
    private List<LRADefinition> nested;
    private String parentLRA;
    private String clientId;
    private long timeout;

    public LRADefinitionImpl(String name, List<T> actions, Object data, List<LRADefinition> nested,
                             String parentLRA, String clientId, long timeout) {

        if (clientId == null) {
            clientId = "";
        }

        if (actions == null || actions.size() == 0) {
            throw new IllegalArgumentException("Cannot create LRA without any action specification");
        }

        this.name = name;
        this.actions = actions;
        this.data = data;
        this.nested = nested;
        this.parentLRA = parentLRA;
        this.clientId = clientId;
        this.timeout = timeout;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<T> getActions() {
        return Collections.unmodifiableList(actions);
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public List<LRADefinition> getNestedLRAs() {
        return nested != null ? nested : Collections.emptyList();
    }

    @Override
    public String getParentLRA() {
        return parentLRA;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LRADefinitionImpl)) return false;

        LRADefinitionImpl that = (LRADefinitionImpl) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (!getActions().equals(that.getActions())) return false;
        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) return false;
        return nested != null ? nested.equals(that.nested) : that.nested == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getActions().hashCode();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (nested != null ? nested.hashCode() : 0);
        return result;
    }
}
