package net.bytelyplay.codeyourselfminestom.listeners;

import net.bytelyplay.codeyourselfminestom.utils.Config;
import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class SpawnListener {
    public static void asyncPlayerConfigurationEvent(
            AsyncPlayerConfigurationEvent e
    ) {
        Config config = Config.getInstance();

        e.setSpawningInstance(
                Instances.getInstance()
                        .getHubInstance()
        );
        e.getPlayer()
                .setRespawnPoint(
                        config.getSpawnPositionInHub()
                );
    }
}
