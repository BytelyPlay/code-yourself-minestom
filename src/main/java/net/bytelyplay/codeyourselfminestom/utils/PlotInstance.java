package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.instance.Instance;

public record PlotInstance(
        PlotInstanceData data,
        Instance instance
) {
}
