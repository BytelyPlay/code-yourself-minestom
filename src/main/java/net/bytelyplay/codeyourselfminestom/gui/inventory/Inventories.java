package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.PlotButton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class Inventories {
    private static final InventoryType INVENTORY_TYPE = InventoryType.CHEST_6_ROW;
    private static final Component INVENTORY_TITLE = Component
            .text("Plots")
            .color(TextColor.color(0, 255, 0));

    private static final ItemStack PLOT_BUTTON_BASE_ITEM = ItemStack
            .of(Material.GRASS_BLOCK);

    public static InventoryGUI getPlotsGUI() {
         InventoryGUI gui = new InventoryGUI(INVENTORY_TYPE, INVENTORY_TITLE);
         // gui.setButton(0, new PlotButton(gui.eventNode()));
         return gui;
    }
}
