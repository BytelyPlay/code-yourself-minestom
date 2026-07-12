package net.bytelyplay.codeyourselfminestom.world.generators;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;

public class OneGrassBlockLayerGenerator implements Generator {
    private static final int GRASS_BLOCK_Y_LEVEL = 3;

    @Override
    public void generate(GenerationUnit unit) {
        Point start = unit.absoluteStart();
        Point end = unit.absoluteEnd();

        start = start.withY(GRASS_BLOCK_Y_LEVEL);
        end = end.withY(GRASS_BLOCK_Y_LEVEL + 1);

        unit.modifier()
                .fill(start, end, Block.GRASS_BLOCK);
    }
}
