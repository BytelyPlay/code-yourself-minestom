package net.bytelyplay.codeyourselfminestom.listeners;

import net.bytelyplay.codeyourselfminestom.gui.hotbar.HotBarActionItems;
import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.Instance;

public class HubHotBarListeners {
    public static void playerSpawnEvent(PlayerSpawnEvent e) {
        Player p = e.getPlayer();
        Instance newInstance = e.getInstance();

        Instances instances = Instances.getInstance();

        if (newInstance ==
                instances.getHubInstance()) {
            p.getInventory().setItemStack(
                    0,
                    HotBarActionItems.MY_PLOTS_ACTION_ITEM.getItem()
            );
        }
    }
}
