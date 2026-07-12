package net.bytelyplay.codeyourselfminestom.gui.hotbar;

import net.bytelyplay.codeyourselfminestom.constants.ItemNames;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HubHotBarItems {
    private static final Logger log = LoggerFactory.getLogger(HubHotBarItems.class);

    private static final ItemStack CREATE_PLOT_ITEM =
            ItemStack.builder(Material.NETHER_STAR)
                    .customName(
                            ItemNames.CREATE_PLOT_ITEM_NAME
                    ).build();

    public static final HotBarActionItem CREATE_PLOT_ACTION_ITEM =
            new CreatePlot();

    private static class CreatePlot extends HotBarActionItem {
        private CreatePlot() {
            super(CREATE_PLOT_ITEM, MinecraftServer.getGlobalEventHandler());
        }

        @Override
        protected void rightClick(PlayerUseItemEvent e) {
            log.info("right");
        }

        @Override
        protected void leftClick(PlayerHandAnimationEvent e) {
            log.info("left");
        }
    }
}
