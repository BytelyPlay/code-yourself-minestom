package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.Button;
import net.kyori.adventure.text.Component;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;

public class InventoryGui extends Inventory {
    public InventoryGui(InventoryType inventoryType, Component title) {
        super(inventoryType, title);
    }

    public void setButton(int slot, Button button) {
        super.setItemStack(slot, button.getItem());
        button.attachListenerToNode(this.eventNode());
    }
}
