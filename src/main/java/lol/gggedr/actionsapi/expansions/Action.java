package lol.gggedr.actionsapi.expansions;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class Action {

    /**
     * Get the identifier of the action.
     *
     * @return The identifier of the action.
     */
    public abstract String getIdentifier();

    /**
     * Function to trigger the action itself
     *
     * @param player The player who triggers the action
     * @param args The arguments to be passed to the action.
     */
    public abstract void onExecute(@Nullable Player player, String[] args);

}
