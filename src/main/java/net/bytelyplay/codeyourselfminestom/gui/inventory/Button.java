package net.bytelyplay.codeyourselfminestom.gui.inventory;

import net.bytelyplay.codeyourselfminestom.gui.ActionItem;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.trait.InventoryEvent;
import net.minestom.server.event.trait.ItemEvent;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.UUID;

public abstract class Button {
    private static final String IDENTIFIER_TAG_ID = "button_identifier_tag";
    private final static Tag<UUID> IDENTIFIER_TAG = Tag.UUID(IDENTIFIER_TAG_ID);

    private final ItemStack item;

    private final EventListener<InventoryPreClickEvent> preClickEvent;
    private final UUID identifier = UUID.randomUUID();

    protected Button(ItemStack item, EventNode<InventoryEvent> eventNode) {
        this.item = item.withTag(IDENTIFIER_TAG, identifier);

        preClickEvent =
                EventListener
                        .builder(InventoryPreClickEvent.class)
                        .ignoreCancelled(false)
                        .filter(this::filterPreClickEvent)
                        .handler(e -> {
                            e.setCancelled(true);
                            preClick(e);
                        })
                        .build();
        eventNode.addListener(preClickEvent);
    }

    public ItemStack getItem() {
        return item;
    }

    /**
     * This is always the correct item.
     * @param e The pre-click event.
     */
    protected abstract void preClick(InventoryPreClickEvent e);

    private boolean filterPreClickEvent(InventoryPreClickEvent e) {
        ItemStack item = e.getClickedItem();

        if (item.hasTag(IDENTIFIER_TAG))
            return item
                    .getTag(IDENTIFIER_TAG)
                    .equals(identifier);
        return false;
    }
}