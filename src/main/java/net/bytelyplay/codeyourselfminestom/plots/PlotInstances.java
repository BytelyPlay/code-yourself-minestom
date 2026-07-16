package net.bytelyplay.codeyourselfminestom.plots;

import net.bytelyplay.codeyourselfminestom.world.generators.OneGrassBlockLayerGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.generator.Generator;

import java.util.*;

public class PlotInstances {
    private static final PlotInstances INSTANCE = new PlotInstances();
    private static final Generator INSTANCE_GENERATOR = new OneGrassBlockLayerGenerator();

    //         The plot ID -> Plot instance data.
    private final HashMap<UUID, PlotInstance> plotIdPlotInstance =
            new HashMap<>();

    /**
     * Gets all the plots a player owns.
     * @param playerUuid The UUID of the player.
     * @return An unmodified list containing all their plots.
     */
    public List<PlotInstance> getPlayerPlotInstances(UUID playerUuid) {
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
    public Optional<PlotInstance> getPlotInstanceById(UUID plotId) {
        PlotInstance data = plotIdPlotInstance.get(plotId);
        return Optional.ofNullable(data);
    }
    public PlotInstance createPlotInstance(Pos spawnPos, UUID playerUUID) {
        Instance inst = MinecraftServer
                .getInstanceManager()
                .createInstanceContainer();
        inst.setGenerator(INSTANCE_GENERATOR);

        PlotInstanceData data = new PlotInstanceData(
                UUID.randomUUID(),
                playerUUID,
                spawnPos
        );
        PlotInstance plot = new PlotInstance(
                data,
                inst
        );
        plotIdPlotInstance.put(
                data.plotInstanceId(),
                plot
        );
        return plot;
    }

    /**
     * Retrieves the plot instance from the Minestom instance
     * @param inst The instance
     * @return The plot instance optional
     */
    public Optional<PlotInstance> getPlotInstanceFromInstance(Instance inst) {
        return plotIdPlotInstance
                .values()
                .stream()
                .filter(
                        (plotInst) -> plotInst.instance().equals(inst)
                )
                .findFirst();
    }

    public static PlotInstances getInstance() {
        return INSTANCE;
    }
}
