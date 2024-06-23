package lol.gggedr.actionsapi;

import lol.gggedr.actionsapi.expansions.Action;
import lol.gggedr.actionsapi.expansions.ActionsExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ActionsAPI extends JavaPlugin {

    private static List<ActionsExpansion> expansions = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Get registered expansion by identifier.
     *
     * @param identifier The identifier of the expansion to be retrieved.
     */
    public static ActionsExpansion getExpansion(String identifier) {
        return expansions.stream().filter(expansion -> expansion.getIdentifier().equalsIgnoreCase(identifier)).findFirst().orElse(null);
    }

    /**
     * Register an new expansion to the API.
     *
     * @param expansion The expansion to be registered.
     */
    public static void registerExpansion(ActionsExpansion expansion) {
        ActionsExpansion existing = getExpansion(expansion.getIdentifier());
        if(existing != null) {
            throw new IllegalArgumentException("An expansion with the identifier '" + expansion.getIdentifier() + "' is already registered.");
        }

        expansions.add(expansion);
    }

    /**
     * Run an action from an expansion and an identifier.
     *
     * @param expansionIdentifier The identifier of the expansion to be used.
     * @param identifier The identifier of the action to be performed.
     * @param p The player who triggers the action
     * @param args The arguments to be passed to the action.
     */
    public static void runAction(String expansionIdentifier, String identifier, @Nullable Player p, String[] args) {
        ActionsExpansion expansion = getExpansion(expansionIdentifier);
        if(expansion == null) {
            throw new IllegalArgumentException("An expansion with the identifier '" + expansionIdentifier + "' is not registered.");
        }

        Optional<Action> actionOptional = expansion.getActions().stream().filter(action -> action.getIdentifier().equalsIgnoreCase(identifier)).findFirst();
        if(!actionOptional.isPresent()) {
            throw new IllegalArgumentException("An action with the identifier '" + identifier + "' is not registered.");
        }

        actionOptional.get().onExecute(p, args);
    }

    /**
     * Run an action from a full identifier. In format: expansion:action
     *
     * @param fullIdentifier The full identifier of the action to be performed.
     * @param p The player who triggers the action
     * @param args The arguments to be passed to the action.
     */
    public static void runAction(String fullIdentifier, @Nullable Player p, String[] args) {
        String[] split = fullIdentifier.split(":");
        if(split.length != 2) {
            throw new IllegalArgumentException("Invalid full identifier format.");
        }

        runAction(split[0], split[1], p, args);
    }

    /**
     * Run an action from a string. In format: [expansion:action] args...
     *
     * @param p The player who triggers the action
     * @param actionString The formatted action to be performed.
     */
    public static void runAction(@Nullable Player p, String actionString) {
        if(!actionString.startsWith("[")) {
            throw new IllegalArgumentException("Invalid action format.");
        }

        String identifier = actionString.split(" ")[0].substring(1).substring(actionString.indexOf("]"));
        if(!identifier.contains(":")) {
            throw new IllegalArgumentException("Invalid identifier format.");
        }

        String[] args = actionString.substring(actionString.indexOf(" ") + 1).split(" ");
        String expansionIdentifier = identifier.split(":")[0];
        String actionIdentifier = identifier.split(":")[1];

        runAction(expansionIdentifier, actionIdentifier, p, args);
    }
}
