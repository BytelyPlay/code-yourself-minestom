package net.bytelyplay.codeyourselfminestom.listeners;

import net.bytelyplay.codeyourselfminestom.gui.hotbar.HubHotBarItems;
import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class SetupHotBarListener {
    public static void playerSpawnEvent(PlayerSpawnEvent e) {
        Player p = e.getPlayer();
        Instance newInstance = e.getInstance();

        Instances instances = Instances.getInstance();

        if (newInstance ==
                instances.getHubInstance()) {
            p.getInventory().setItemStack(
                    0,
                    HubHotBarItems.CREATE_PLOT_ACTION_ITEM.getItem()
            );
        }
    }
}
