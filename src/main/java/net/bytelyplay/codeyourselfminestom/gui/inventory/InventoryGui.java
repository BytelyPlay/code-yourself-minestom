package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.inventory.buttons.Button;
import net.kyori.adventure.text.Component;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryGui extends Inventory {
    private static final Logger log = LoggerFactory.getLogger(InventoryGui.class);

    public InventoryGui(InventoryType inventoryType, Component title) {
        super(inventoryType, title);
    }

    public void setButton(int slot, Button button) {
        super.setItemStack(slot, button.getItem());
        button.attachListenerToNode(this.eventNode());
    }
}
