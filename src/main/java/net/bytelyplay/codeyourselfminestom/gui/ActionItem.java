package net.bytelyplay.codeyourselfminestom.gui;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ActionItem {
    private final Tag<UUID> identifierTag;
    private final UUID identifier = UUID.randomUUID();

    private final EventNode<Event> rootNode;
    protected final List<EventListener<? extends Event>> events = new ArrayList<>();

    private final ItemStack item;

    protected ActionItem(
            ItemStack baseItem,
            EventNode<Event> eventNode,
            Tag<UUID> identifierTag
    ) {
        this.identifierTag = identifierTag;
        this.rootNode = eventNode;
        this.item = baseItem.withTag(
                identifierTag,
                identifier
        );
        addListeners(events);
        setupEvents();
    }

    protected abstract void addListeners(List<EventListener<? extends Event>> events);

    public ItemStack getItem() {
        return item;
    }

    /**
     * Removes the actionNode from the rootNode.
     */
    protected final void cleanAndRemove() {
        events.forEach(rootNode::removeListener);
    }

    /**
     * Filters the items to the ones that this owns.
     *
     * @param item The Item
     * @return True if we should keep it, false otherwise.
     */
    protected boolean filterItem(ItemStack item) {
        if (item.hasTag(identifierTag))
            return item
                    .getTag(identifierTag)
                    .equals(identifier);
        return false;
    }

    /**
     * Attaches the listeners to the event node.
     */
    private void setupEvents() {
        events.forEach(rootNode::addListener);
    }
}
