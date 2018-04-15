package org.xstefank.lra.definition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@NoArgsConstructor
public class LRADefinitionImpl<T extends Action> implements LRADefinition {

    //type information in this class is required only for the JSON processing purposes
    private String name;
    private List<T> actions;
    private Object data;
    private List<LRADefinition> nested;
    private String parentLRA;
    private String clientId;
    private long timelimit;

    public LRADefinitionImpl(String name, List<T> actions, Object data, List<LRADefinition> nested,
                             String parentLRA, String clientId, long timelimit) {

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
        this.timelimit = timelimit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    @JsonIgnore
    public List<T> getTypedActions() {
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
    public long getTimelimit() {
        return timelimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LRADefinitionImpl)) return false;

        LRADefinitionImpl<?> that = (LRADefinitionImpl<?>) o;

        if (getTimelimit() != that.getTimelimit()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getActions() != null ? !getActions().equals(that.getActions()) : that.getActions() != null) return false;
        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) return false;
        if (nested != null ? !nested.equals(that.nested) : that.nested != null) return false;
        if (getParentLRA() != null ? !getParentLRA().equals(that.getParentLRA()) : that.getParentLRA() != null)
            return false;
        return getClientId() != null ? getClientId().equals(that.getClientId()) : that.getClientId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getActions() != null ? getActions().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (nested != null ? nested.hashCode() : 0);
        result = 31 * result + (getParentLRA() != null ? getParentLRA().hashCode() : 0);
        result = 31 * result + (getClientId() != null ? getClientId().hashCode() : 0);
        result = 31 * result + (int) (getTimelimit() ^ (getTimelimit() >>> 32));
        return result;
    }
}
