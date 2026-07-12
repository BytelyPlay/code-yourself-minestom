package net.bytelyplay.codeyourselfminestom.gui.hotbar;

import net.bytelyplay.codeyourselfminestom.gui.ActionItem;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerHandAnimationEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

import java.util.List;
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
public abstract class HotBarActionItem extends ActionItem {
    private static final String TAG_ID = "hotbar_action_item_identifier";
    private static final Tag<UUID> IDENTIFIER_TAG = Tag.UUID(TAG_ID);

    protected HotBarActionItem(
            ItemStack baseItem,
            EventNode<Event> eventNode
    ) {
        super(baseItem, eventNode, IDENTIFIER_TAG);
    }

    @Override
    protected void addListeners(List<EventListener<? extends Event>> events) {
        events.add(EventListener
                .builder(PlayerUseItemEvent.class)
                .ignoreCancelled(false)
                .filter(
                        e -> filterItem(
                                e.getItemStack()
                        )
                )
                .handler(this::rightClick)
                .build()
        );
        events.add(EventListener
                .builder(PlayerHandAnimationEvent.class)
                .ignoreCancelled(false)
                .filter(
                        e -> filterItem(
                                e.getPlayer().getItemInMainHand()
                        )
                )
                .handler(this::leftClick)
                .build()
        );
    }

    protected abstract void rightClick(PlayerUseItemEvent e);
    protected abstract void leftClick(PlayerHandAnimationEvent e);
}
