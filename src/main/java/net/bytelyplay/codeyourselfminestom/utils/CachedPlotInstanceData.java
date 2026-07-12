package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;

import java.util.UUID;

public record CachedPlotInstanceData(
        UUID plotInstanceId,
        UUID ownerPlayerId,
        Instance instance,
        Pos spawnPos
) {}