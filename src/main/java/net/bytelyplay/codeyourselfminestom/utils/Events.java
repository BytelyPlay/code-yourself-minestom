package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.listeners.SetupHotBarListener;
import net.bytelyplay.codeyourselfminestom.listeners.SpawnListener;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.event.trait.PlayerEvent;

public class Events {
    private final static Events INSTANCE = new Events();

    private final static String MISC_NODE_ID = "misc-events";
    private final static String PLAYER_NODE_ID = "player-events";

    private final EventNode<Event> miscNode = EventNode
            .all(MISC_NODE_ID);
    private final EventNode<PlayerEvent> playerNode = EventNode
            .type(PLAYER_NODE_ID, EventFilter.PLAYER);

    public void setupEvents(EventNode<Event> rootNode) {
        setupMiscEvents(rootNode);
        setupPlayerEvents(rootNode);
    }

    public static Events getInstance() {
        return INSTANCE;
    }

    private void setupMiscEvents(EventNode<Event> rootNode) {
        rootNode.addChild(miscNode);
    }
    private void setupPlayerEvents(EventNode<Event> rootNode) {
        playerNode.addListener(
                AsyncPlayerConfigurationEvent.class,
                SpawnListener::setupSpawn
        );
        playerNode.addListener(
                PlayerSpawnEvent.class,
                SetupHotBarListener::playerSpawnEvent
        );
        rootNode.addChild(playerNode);
    }

    private Events() {}
}
