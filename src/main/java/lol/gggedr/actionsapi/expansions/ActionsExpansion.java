package lol.gggedr.actionsapi.expansions;

import lol.gggedr.actionsapi.ActionsAPI;

import java.util.List;

public abstract class ActionsExpansion {

    /**
     * The author of this expansion
     *
     * @return author's name
     */
    public abstract String getAuthor();

    /**
     * Identifier of this expansion
     *
     * @return expansion's identifier
     */
    public abstract String getIdentifier();

    /**
     * The version of this expansion
     *
     * @return current version
     */
    public abstract String getVersion();

    /**
     * List of actions to be registered
     *
     * @return List of actions
     */
    public abstract List<Action> getActions();

    /**
     * Function to register this expansion
     */
    public void register() {
        ActionsAPI.registerExpansion(this);
    }

}
