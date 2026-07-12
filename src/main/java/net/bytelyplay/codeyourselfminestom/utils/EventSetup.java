package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.listeners.SpawnListener;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class EventSetup {
    private final static EventSetup INSTANCE =
            new EventSetup();

    public void setupEvents(EventNode<Event> rootNode) {
        setupMiscEvents(rootNode);
    }

    public static EventSetup getInstance() {
        return INSTANCE;
    }

    private void setupMiscEvents(EventNode<Event> rootNode) {
        EventNode<Event> miscNode =
                EventNode.all("misc-events");

        // TODO: Put this in a player events category.
        miscNode.addListener(
                AsyncPlayerConfigurationEvent.class,
                SpawnListener::setupSpawn
        );
        rootNode.addChild(miscNode);
    }

    private EventSetup() {}
}
