package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstance;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstanceData;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

public class TeleportationHelpers {
    /**
     * Teleport a player to a plot.
     * This will notify the player if it couldn't teleport successfully
     * and also send other informational messages
     *
     * @param plot The plot's data
     * @param p    The player
     * @return Whether it was successful or not
     */
    public static boolean teleportPlayerToPlot(PlotInstance plot, Player p) {
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
        ).thenRun(
                () -> {
                    p.getInventory().clear();
                    p.sendMessage(Messages.TELEPORTED_TO_PLOT);
                }
        );
        return true;
    }

    /**
     * Teleports a player to the hub
     * If something goes wrong, it will notify the player.
     * It will also send the teleport messages.
     *
     * @param p The player
     * @return Whether it was successful or not (true means it was successful, false otherwise)
     */
    public static boolean teleportPlayerToHub(Player p) {
        Instance hubInstance = Instances
                .getInstance()
                .getHubInstance();

        p.sendMessage(Messages.TELEPORTING_TO_HUB);

        if (p.getInstance().equals(hubInstance)) {
            p.teleport(
                    Config.getInstance().getSpawnPositionInHub()
            );
            p.sendMessage(Messages.TELEPORTED_TO_HUB);
            return true;
        }

        p.getInventory().clear();

        p.setInstance(
                hubInstance,
                Config.getInstance().getSpawnPositionInHub()
        ).thenRun(
                () -> p.sendMessage(Messages.TELEPORTED_TO_HUB)
        );
        return true;
    }
}