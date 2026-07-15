package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.world.generators.OneGrassBlockLayerGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
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
     * Teleport a player to a plot.
     * This will notify the player if it couldn't teleport successfully
     * and also send other informational messages
     *
     * @param plot The plot's data
     * @param p The player
     * @return Whether it was successful or not
     */
    public boolean teleportPlayerToPlot(PlotInstance plot, Player p) {
        p.sendMessage(Messages.TELEPORTING_TO_PLOT);

        PlotInstanceData data = plot.data();
        Instance plotInstance = plot.instance();

        Pos spawnPos = data.spawnPos();

        if (p.getInstance().equals(plotInstance)) {
            p.sendMessage(Messages.ALREADY_IN_PLOT);
            return false;
        }

        p.setInstance(
                plotInstance,
                spawnPos
        );
        p.sendMessage(Messages.TELEPORTED_TO_PLOT);
        return true;
    }

    public static PlotInstances getInstance() {
        return INSTANCE;
    }
}
