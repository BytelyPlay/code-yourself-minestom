package net.bytelyplay.codeyourselfminestom.gui.inventory.buttons;

import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.trait.InventoryEvent;
import net.minestom.server.inventory.AbstractInventory;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

import java.util.UUID;

public abstract class Button {
    private static final String IDENTIFIER_TAG_ID = "button_identifier_tag";
    private final static Tag<UUID> IDENTIFIER_TAG = Tag.UUID(IDENTIFIER_TAG_ID);

    private final ItemStack item;

    private final EventListener<InventoryPreClickEvent> preClickEvent;
    private final UUID identifier = UUID.randomUUID();

    protected Button(ItemStack item) {
        this.item = item.withTag(IDENTIFIER_TAG, identifier);

        preClickEvent =
                EventListener
                        .builder(InventoryPreClickEvent.class)
                        .ignoreCancelled(false)
                        .filter(this::filterPreClickEvent)
                        .handler(e -> {
                            e.setCancelled(true);
                            click(e.getClick(), e.getInventory(), e.getPlayer());
                        })
                        .build();
    }

    public ItemStack getItem() {
        return item;
    }

    /**
     * When the player clicks, this is always the right item.
     * @param click The click instance
     * @param inv The Inventory
     * @param p The Player
     */
    protected abstract void click(Click click, AbstractInventory inv, Player p);

    /**
     * Listens on the EventNode provided.
     * @param node The event node to listen on
     */
    public void attachListenerToNode(EventNode<InventoryEvent> node) {
        node.addListener(preClickEvent);
    }

    private boolean filterPreClickEvent(InventoryPreClickEvent e) {
        ItemStack item = e.getClickedItem();

        if (item.hasTag(IDENTIFIER_TAG))
            return item
                    .getTag(IDENTIFIER_TAG)
                    .equals(identifier);
        return false;
    }
}