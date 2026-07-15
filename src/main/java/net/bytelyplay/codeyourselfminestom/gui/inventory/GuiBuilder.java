package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.Button;
import net.kyori.adventure.text.Component;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

public class GuiBuilder {
    private final InventoryGui gui;

    public static GuiBuilder builder(InventoryType type, Component title) {
        return new GuiBuilder(
                new InventoryGui(
                        type, title
                )
        );
    }
    public static GuiBuilder builder(InventoryGui gui) {
        return new GuiBuilder(gui);
    }

    public GuiBuilder addButton(int slot, Button button) {
        gui.setButton(slot, button);
        return this;
    }
    public GuiBuilder fillEmpty(ItemStack item) {
        for (int i = 0; i < gui.getSize(); i++)
            if (gui.getItemStack(i).isAir())
                gui.setItemStack(i, item);
        return this;
    }
    private GuiBuilder(InventoryGui gui) {
        this.gui = gui;
    }
}
