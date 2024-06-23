package lol.gggedr.actionsapi.expansions.impl.player;

import lol.gggedr.actionsapi.expansions.Action;
import lol.gggedr.actionsapi.expansions.ActionsExpansion;
import lol.gggedr.actionsapi.expansions.impl.player.actions.CloseInventoryAction;
import lol.gggedr.actionsapi.expansions.impl.player.actions.RunCommandAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerActionsExpansion extends ActionsExpansion {

    @Override
    public String getAuthor() {
        return "GGGEDR";
    }

    @Override
    public String getIdentifier() {
        return "player";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();

        actions.add(new RunCommandAction());
        actions.add(new CloseInventoryAction());

        return Collections.unmodifiableList(actions);
    }

}
