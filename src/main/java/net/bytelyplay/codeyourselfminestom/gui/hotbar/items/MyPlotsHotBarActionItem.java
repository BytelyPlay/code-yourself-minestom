package net.bytelyplay.codeyourselfminestom.gui.hotbar.items;

import net.bytelyplay.codeyourselfminestom.constants.ItemNames;
import net.bytelyplay.codeyourselfminestom.gui.hotbar.HotBarActionItem;
import net.bytelyplay.codeyourselfminestom.gui.inventory.Inventories;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class MyPlotsHotBarActionItem extends HotBarActionItem {
    private static final ItemStack BASE_ITEM_STACK =
            ItemStack.builder(Material.NETHER_STAR)
                    .customName(
                            ItemNames.PLOTS_ITEM_NAME
                    ).build();
    // This should be at the top, but due to initialization order shenanigans, it can't be.
    private static final MyPlotsHotBarActionItem instance = new MyPlotsHotBarActionItem();

    private MyPlotsHotBarActionItem() {
        super(BASE_ITEM_STACK, MinecraftServer.getGlobalEventHandler());
    }

    @Override
    protected void rightClick(PlayerUseItemEvent e) {
        handleClick(e.getPlayer());
    }

    @Override
    protected void leftClick(PlayerHandAnimationEvent e) {
        handleClick(e.getPlayer());
    }

    private void handleClick(Player p) {
        p.openInventory(Inventories.getPlotsGUI());
    }

    public static MyPlotsHotBarActionItem getInstance() {
        return instance;
    }
}
