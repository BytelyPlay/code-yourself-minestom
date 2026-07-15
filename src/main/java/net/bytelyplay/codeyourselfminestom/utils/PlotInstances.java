package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.world.generators.OneGrassBlockLayerGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.generator.Generator;

import java.util.*;
import java.util.stream.Collectors;

public class PlotInstances {
    private static final PlotInstances INSTANCE = new PlotInstances();
    private static final Generator INSTANCE_GENERATOR = new OneGrassBlockLayerGenerator();

    //         The plot ID -> Plot instance data.
    private final HashMap<UUID, CachedPlotInstanceData> plotIdPlotInstance =
            new HashMap<>();

    /**
     * Gets all the plots a player owns.
     * @param playerUuid The UUID of the player.
     * @return An unmodified list containing all their plots.
     */
    public List<CachedPlotInstanceData> getPlayerPlotInstances(UUID playerUuid) {
        return plotIdPlotInstance
                .values()
                .stream()
                .filter(
                        (entry) ->
                                entry
                                        .data()
                                        .ownerPlayerId()
                                        .equals(playerUuid)
                        )
                .toList();
    }
    public Optional<CachedPlotInstanceData> getPlotInstanceById(UUID plotId) {
        CachedPlotInstanceData data = plotIdPlotInstance.get(plotId);
        return Optional.ofNullable(data);
    }
    public CachedPlotInstanceData createPlotInstance(Pos spawnPos, UUID playerUUID) {
        Instance inst = MinecraftServer
                .getInstanceManager()
                .createInstanceContainer();
        inst.setGenerator(INSTANCE_GENERATOR);

        PlotInstanceData data = new PlotInstanceData(
                UUID.randomUUID(),
                playerUUID,
                spawnPos
        );
        CachedPlotInstanceData cachedData = new CachedPlotInstanceData(
                data,
                inst
        );
        plotIdPlotInstance.put(
                data.plotInstanceId(),
                cachedData
        );
        return cachedData;
    }

    public static PlotInstances getInstance() {
        return INSTANCE;
    }
}
