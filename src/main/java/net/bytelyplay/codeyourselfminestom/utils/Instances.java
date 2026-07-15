package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.listeners.HubRestrictionsListeners;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.DimensionType;

import java.nio.file.Path;

public class Instances {
    private final static Instances INSTANCE = new Instances();

    public final static RegistryKey<DimensionType> DIMENSION = DimensionType.OVERWORLD;

    private Instance hubInstance;

    public Instance getHubInstance() {
        if (hubInstance == null)
            hubInstance = createHubInstance();
        return hubInstance;
    }

    /**
     * Teleports a player to the hub
     * If something goes wrong, it will notify the player.
     * It will also send the teleport messages.
     *
     * @param p The player
     * @return Whether it was successful or not (true means it was successful, false otherwise)
     */
    public boolean teleportPlayerToHub(Player p) {
        p.sendMessage(Messages.TELEPORTING_TO_HUB);

        if (p.getInstance().equals(this.getHubInstance())) {
            p.sendMessage(Messages.ALREADY_IN_HUB);
            return false;
        }

        p.setInstance(
                getHubInstance(),
                Config.getInstance().getSpawnPositionInHub()
        );

        p.sendMessage(Messages.TELEPORTED_TO_HUB);

        return true;
    }

    public static Instances getInstance() {
        return INSTANCE;
    }

    private Instance createHubInstance() {
        Config config = Config.getInstance();

        Instance instance = MinecraftServer
                .getInstanceManager()
                .createInstanceContainer(
                        new AnvilLoader(
                                Path.of(config.getPathToHubWorld()),
                                DIMENSION.key()
                        )
                );

        HubRestrictionsListeners
                .getInstance()
                .setupHubEventListeners(instance.eventNode());

        return instance;
    }

    private Instances() {}
}
