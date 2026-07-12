package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.validate.Check;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;

public abstract class InventoryGUI extends Inventory {
    private final HashMap<Integer, Button> slotToButtonHashMap =
            new HashMap<>();

    public InventoryGUI(InventoryType inventoryType, Component title) {
        super(inventoryType, title);
    }

    @Override
    public boolean handleClick(@NonNull Player p, Click click) {
        int slot = click.slot();

        if (slotToButtonHashMap.containsKey(slot)) {
            slotToButtonHashMap
                    .get(slot)
                    .click(this, p, click);
        }
        update();

        return false;
    }

    @Override
    public void setItemStack(int slot, @NonNull ItemStack itemStack, boolean sendPacket) {
        super.setItemStack(slot, itemStack, sendPacket);
        slotToButtonHashMap.remove(slot);
    }

    public void setButton(int slot, Button button) {
        Check.argCondition(
                slot >= 0 && slot < getSize(),
                "Tried to put a button in a slot that's " +
                        "out of bounds. " +
                        "This is a gui, not a normal Minestom inventory."
        );

        slotToButtonHashMap.put(slot, button);
        super.setItemStack(slot, button.getItem());
    }
}
