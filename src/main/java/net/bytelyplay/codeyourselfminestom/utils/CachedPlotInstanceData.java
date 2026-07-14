package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.instance.Instance;

public record CachedPlotInstanceData(
        PlotInstanceData data,
        Instance instance
) {
}
