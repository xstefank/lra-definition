package org.xstefank.lra.definition;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@NoArgsConstructor
public class LRADefinitionImpl implements LRADefinition {

    private String name;
    private List<Action> actions;
    private Object data;

    public LRADefinitionImpl(String name, List<Action> actions, Object data) {
        this.name = name;
        this.actions = actions;
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LRADefinitionImpl)) return false;

        LRADefinitionImpl that = (LRADefinitionImpl) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getActions() != null ? !getActions().equals(that.getActions()) : that.getActions() != null) return false;
        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getActions() != null ? getActions().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }
}
