package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.CreatePlotButton;
import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.PlotButton;
import net.bytelyplay.codeyourselfminestom.utils.PlotInstance;
import net.bytelyplay.codeyourselfminestom.utils.PlotInstanceData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.List;

// TODO: A GUI builder
public class Inventories {
    private static final InventoryType MY_PLOTS_INVENTORY_TYPE = InventoryType.CHEST_6_ROW;
    private static final Component MY_PLOTS_INVENTORY_TITLE = Component
            .text("My Plots")
            .color(TextColor.color(0, 255, 0));

    private static final ItemStack PLOT_BUTTON_BASE_ITEM = ItemStack
            .of(Material.GRASS_BLOCK);

    public static InventoryGui createMyPlotsGUI(List<PlotInstance> plots)
            throws IllegalArgumentException {
        InventoryGui gui = new InventoryGui(MY_PLOTS_INVENTORY_TYPE, MY_PLOTS_INVENTORY_TITLE);

        if (plots.size() >= gui.getSize())
            throw new IllegalArgumentException(
                    "Too many plots provided. Cannot fit them all"
            );

        for (int i = 0; i < plots.size(); i++) {
            PlotInstance cachedData = plots.get(i);
            PlotInstanceData data = cachedData.data();

            gui.setButton(
                    i, new PlotButton(
                            PLOT_BUTTON_BASE_ITEM
                                    .withCustomName(
                                            Component.text(
                                                    data.plotInstanceId()
                                                            .toString()
                                            )
                                    ),
                            data.plotInstanceId()
                    )
            );
        }
        gui.setButton(gui.getSize() - 1, new CreatePlotButton());
        return gui;
    }
}