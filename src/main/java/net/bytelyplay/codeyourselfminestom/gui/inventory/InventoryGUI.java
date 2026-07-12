package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.validate.Check;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class InventoryGUI extends Inventory {
    private static final Logger log = LoggerFactory.getLogger(InventoryGUI.class);

    public InventoryGUI(InventoryType inventoryType, Component title) {
        super(inventoryType, title);
    }

    public void setButton(int slot, Button button) {
        super.setItemStack(slot, button.getItem());
    }
}
