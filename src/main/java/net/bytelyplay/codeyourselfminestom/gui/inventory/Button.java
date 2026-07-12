package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import org.jspecify.annotations.NonNull;

public abstract class Button {
    private final ItemStack item;

    protected Button(
            ItemStack baseItem,
            EventNode<Event> eventNode
    ) {
        this.item = baseItem;
    }

    public ItemStack getItem() {
        return item;
    }

    protected abstract void click(
            @NonNull InventoryGUI gui,
            @NonNull Player p,
            @NonNull Click click
    );
}