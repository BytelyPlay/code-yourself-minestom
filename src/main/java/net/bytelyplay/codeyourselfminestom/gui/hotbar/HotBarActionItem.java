package net.bytelyplay.codeyourselfminestom.gui.hotbar;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

import java.util.UUID;

/**
 * This is made for items where if you hold them and then click them,
 * it does something.
 * When you provide the ItemStack it will be modified to add an identifier
 * to the nbt, you need to use getItem() to get
 * the modified version.
 * <br>
 * cleanAndRemove MUST be called at the end or else there will be a memory leak.
 */
// TODO: Make it not necessary to call cleanAndRemove.
public abstract class HotBarActionItem {
    private static final String ACTION_NODE_ID_PREFIX = "hotbar-action-item-";
    private static final String TAG_ID = "hotbar_action_item_identifier";

    private static final Tag<UUID> IDENTIFIER_TAG = Tag.UUID(TAG_ID);

    private final UUID identifier = UUID.randomUUID();
    private final EventNode<Event> actionNode;

    private final ItemStack item;
    private final EventNode<Event> rootNode;

    protected HotBarActionItem(
            ItemStack baseItem,
            EventNode<Event> eventNode
    ) {
        this.rootNode = eventNode;
        this.item = baseItem.withTag(IDENTIFIER_TAG, identifier);
        this.actionNode =
                EventNode.all(
                        ACTION_NODE_ID_PREFIX + identifier
                );

        setupEvents();
    }

    public ItemStack getItem() {
        return item;
    }

    protected abstract void rightClick(PlayerUseItemEvent e);
    protected abstract void leftClick(PlayerHandAnimationEvent e);

    /**
     * Removes the actionNode from the rootNode.
     */
    protected final void cleanAndRemove() {
        rootNode.removeChild(actionNode);
    }

    private void setupEvents() {
        actionNode.addListener(
                EventListener
                        .builder(PlayerUseItemEvent.class)
                        .filter(this::filterUseItemEvent)
                        .handler(this::rightClick)
                        .build()
        );
        actionNode.addListener(
                EventListener
                        .builder(PlayerHandAnimationEvent.class)
                        .filter(this::filterHandAnimationEvent)
                        .handler(this::leftClick)
                        .build()
        );

        rootNode.addChild(actionNode);
    }

    /**
     * Filters the use item events to the ones that we need.
     * @param e The event
     * @return True if we should keep it, false otherwise.
     */
    private boolean filterUseItemEvent(PlayerUseItemEvent e) {
        ItemStack item = e.getItemStack();

        if (item.hasTag(IDENTIFIER_TAG))
            return item.getTag(IDENTIFIER_TAG) == identifier;
        return false;
    }
    /**
     * Filters the hand animation events to the ones that we need.
     * @param e The event
     * @return True if we should keep it, false otherwise.
     */
    private boolean filterHandAnimationEvent(PlayerHandAnimationEvent e) {
        ItemStack item = e
                .getPlayer()
                .getItemInHand(e.getHand());

        if (item.hasTag(IDENTIFIER_TAG))
            return item.getTag(IDENTIFIER_TAG) == identifier;
        return false;
    }
}
