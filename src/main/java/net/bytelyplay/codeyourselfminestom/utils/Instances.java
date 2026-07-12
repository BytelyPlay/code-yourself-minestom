package net.bytelyplay.codeyourselfminestom.utils;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.listeners.HubRestrictionsListeners;
import net.bytelyplay.codeyourselfminestom.world.generators.OneGrassBlockLayerGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.DimensionType;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Instances {
    private final static Instances INSTANCE = new Instances();
    private final static RegistryKey<DimensionType> DIMENSION = DimensionType.OVERWORLD;

    private Instance hubInstance;

    // TODO: Make this persistent.
    private final LoadingCache<UUID, ArrayList<CachedPlotInstanceData>>
            playerIdToPlotInstancesCache =
            Caffeine.newBuilder()
                    .expireAfterAccess(Duration.ofMinutes(15))
                    .removalListener(this::cacheRemovalListener)
                    // TODO: Later it should use MySQL to check out the data, and only if there is no data should it create a new arraylist.
                    .build(uuid -> new ArrayList<>());

    public Instance getHubInstance() {
        if (hubInstance == null)
            hubInstance = createHubInstance();
        return hubInstance;
    }

    public ArrayList<CachedPlotInstanceData> getInstancesForPlayer(Player p) {
        return getInstancesForPlayerUUID(p.getUuid());
    }
    public ArrayList<CachedPlotInstanceData> getInstancesForPlayerUUID(UUID uuid) {
        return playerIdToPlotInstancesCache.get(uuid);
    }
    public Instance getInstanceById(UUID id) {
        throw new IllegalStateException("Not Implemented!");
    }

    /**
     * Creates an instance for a player and saves it.
     * @return The newly created instance.
     */
    public Instance createAndSaveInstanceForPlayer(Player p) {
        return createAndSaveInstanceForPlayer(p.getUuid());
    }

    /**
     * Creates an instance for a player and saves it.
     * @return The newly created instance.
     */
    public Instance createAndSaveInstanceForPlayer(UUID uuid) {
        Instance instance = MinecraftServer.getInstanceManager()
                .createInstanceContainer();
        instance.setGenerator(new OneGrassBlockLayerGenerator());

        playerIdToPlotInstancesCache.get(uuid).add(new CachedPlotInstanceData(
                                                 // TODO: Don't hardcode magic numbers.
                UUID.randomUUID(), uuid, instance, new Pos(0, 4, 0)
        ));
        return instance;
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

    /**
     * This removes an instance from Minestom's tracking stuff, IT DOESN'T remove it from the cache or anything else.
     */
    private void removeInstanceFromMinestom(Instance instance) {
        for (Player p : instance.getPlayers()) {
            p.setInstance(getHubInstance());
            p.sendMessage(Messages.TAKEN_TO_HUB_DUE_TO_INSTANCE_REMOVAL);
        }
        for (Entity e : instance.getEntities())
            e.remove();
        MinecraftServer.getInstanceManager()
                .unregisterInstance(instance);
    }
    private void cacheRemovalListener(UUID key, ArrayList<CachedPlotInstanceData> value, RemovalCause cause) {

    }

    private Instances() {}
}
