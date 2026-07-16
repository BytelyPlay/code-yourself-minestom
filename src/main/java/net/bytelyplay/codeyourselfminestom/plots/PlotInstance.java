package net.bytelyplay.codeyourselfminestom.plots;

import net.minestom.server.instance.Instance;

public record PlotInstance(
        PlotInstanceData data,
        Instance instance
) {
}
