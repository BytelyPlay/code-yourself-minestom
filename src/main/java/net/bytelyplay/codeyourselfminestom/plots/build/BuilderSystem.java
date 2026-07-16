package net.bytelyplay.codeyourselfminestom.plots.build;

import net.bytelyplay.codeyourselfminestom.plots.PlotInstance;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstanceData;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;

public class BuilderSystem {
    private static final BuilderSystem INSTANCE =
            new BuilderSystem();

    public static BuilderSystem getInstance() {
        return INSTANCE;
    }

    public void putPlayerInBuild(PlotInstance plot, Player p) {
        PlotInstanceData data = plot.data();
        InstanceContainer instance = MinecraftServer
                .getInstanceManager()
                .createInstanceContainer();

        // TODO: It should take from the last save.

        p.setInstance(
                instance,
                data.spawnPos()
        );
    }

    private BuilderSystem() {}
}
