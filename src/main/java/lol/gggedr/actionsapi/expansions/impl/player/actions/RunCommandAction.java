package lol.gggedr.actionsapi.expansions.impl.player.actions;

import lol.gggedr.actionsapi.expansions.Action;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class RunCommandAction extends Action {

    @Override
    public String getIdentifier() {
        return "command";
    }

    @Override
    public void onExecute(@Nullable Player player, String[] args) {
        if(player == null) {
            throw new IllegalArgumentException("Action player:command requires a player to be passed.");
        }

        String command = String.join(" ", args);
        if(command.startsWith("/")) {
            command = command.substring(1);
        }

        player.performCommand(command);
    }

}
