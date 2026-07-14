package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.instance.Instance;

import java.util.*;
import java.util.stream.Collectors;

public class PlotInstances {
    //         The plot ID -> Plot instance data.
    private HashMap<UUID, CachedPlotInstanceData> plotIdPlotInstance =
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
                                entry.getValue()
                                        .data()
                                        .ownerPlayerId() == playerUuid
                        )
                .toList();
    }
}
