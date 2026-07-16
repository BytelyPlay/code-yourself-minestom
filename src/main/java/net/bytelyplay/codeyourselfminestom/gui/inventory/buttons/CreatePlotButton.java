package net.bytelyplay.codeyourselfminestom.gui.inventory.buttons;

import net.bytelyplay.codeyourselfminestom.constants.ItemNames;
import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstance;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstances;
import net.bytelyplay.codeyourselfminestom.utils.TeleportationHelpers;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.AbstractInventory;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePlotButton extends Button {
    private static final Logger log = LoggerFactory.getLogger(CreatePlotButton.class);

    private static final ItemStack BASE_ITEM =
            ItemStack
                    .of(Material.GRASS_BLOCK)
                    .withCustomName(ItemNames.CREATE_PLOT_BUTTON);
    private static final Pos DEFAULT_SPAWN_POS =
            new Pos(0.0f, 4.0f, 0.0f, 0.0f, 90.0f);

    public CreatePlotButton() {
        super(BASE_ITEM);
    }

    @Override
    protected void click(Click click, AbstractInventory inv, Player p) {
        p.closeInventory();
        p.sendMessage(Messages.CREATING_PLOT);

        PlotInstance cachedData = PlotInstances.getInstance()
                .createPlotInstance(
                        DEFAULT_SPAWN_POS,
                        p.getUuid()
                );

        p.sendMessage(Messages.CREATED_PLOT);

        TeleportationHelpers
                .teleportPlayerToPlot(
                        cachedData, p
                );
    }
}
