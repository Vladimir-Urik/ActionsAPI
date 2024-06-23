package lol.gggedr.actionsapi.expansions.impl.player.actions;

import lol.gggedr.actionsapi.expansions.Action;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CloseInventoryAction extends Action {

    @Override
    public String getIdentifier() {
        return "close-inventory";
    }

    @Override
    public void onExecute(@Nullable Player player, String[] args) {
        if(player == null) {
            throw new IllegalArgumentException("Action player:close-inventory requires a player to be passed.");
        }

        player.closeInventory();
    }

}
