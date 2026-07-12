package net.bytelyplay.codeyourselfminestom.listeners;

import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.InstanceEvent;
import net.minestom.server.inventory.click.Click;

import java.util.Objects;

/* TODO: Later on check if the player has permission to do this stuff.
// Well not for everything, but for some of the stuff. */

// This is only a temporary system.
public class HubRestrictionsListeners {
    private static final HubRestrictionsListeners instance = new HubRestrictionsListeners();

    public void setupHubEventListeners(EventNode<Event> eventNode) {
        setupEventListener(ItemDropEvent.class, eventNode);
        setupEventListener(PlayerBlockBreakEvent.class, eventNode);
        setupEventListener(PlayerBlockPlaceEvent.class, eventNode);
        setupEventListener(EntityDamageEvent.class, eventNode);
        setupEventListener(InventoryPreClickEvent.class, eventNode);
    }

    // TODO: Replace with something less ugly.
    private <T extends Event & CancellableEvent & InstanceEvent>
    void setupEventListener(
            Class<T> c, EventNode<Event> eventNode
    ) {
        eventNode.addListener(
                EventListener
                        .builder(c)
                        .filter(this::isInstanceHub)
                        .handler(this::cancelEvent)
                        .build()
        );
    }

    private void cancelEvent(CancellableEvent e) {
        e.setCancelled(true);
    }

    private boolean isInstanceHub(InstanceEvent e) {
        return Objects.equals(
                e.getInstance(),
                Instances.getInstance().getHubInstance()
        );
    }

    public static HubRestrictionsListeners getInstance() {
        return instance;
    }
}
