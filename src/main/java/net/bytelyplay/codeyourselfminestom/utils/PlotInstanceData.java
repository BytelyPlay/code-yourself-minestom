package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.coordinate.Pos;

import java.util.UUID;

public record PlotInstanceData(
        UUID plotInstanceId,
        UUID ownerPlayerId,
        Pos spawnPos
) {}