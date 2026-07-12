package net.bytelyplay.codeyourselfminestom.gui.hotbar.items;

import net.bytelyplay.codeyourselfminestom.constants.ItemNames;
import net.bytelyplay.codeyourselfminestom.gui.hotbar.HotBarActionItem;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class CreatePlotActionItem extends HotBarActionItem {
    private static final ItemStack BASE_ITEM_STACK =
            ItemStack.builder(Material.NETHER_STAR)
                    .customName(
                            ItemNames.CREATE_PLOT_ITEM_NAME
                    ).build();
    // This should be at the top, but due to initialization order shenanigans, it can't be.
    private static final CreatePlotActionItem instance = new CreatePlotActionItem();

    private CreatePlotActionItem() {
        super(BASE_ITEM_STACK, MinecraftServer.getGlobalEventHandler());
    }

    @Override
    protected void rightClick(PlayerUseItemEvent e) {

    }

    @Override
    protected void leftClick(PlayerHandAnimationEvent e) {

    }

    public static CreatePlotActionItem getInstance() {
        return instance;
    }
}
