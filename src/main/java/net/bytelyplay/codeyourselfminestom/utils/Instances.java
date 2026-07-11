package net.bytelyplay.codeyourselfminestom.utils;

import net.kyori.adventure.key.Key;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.DimensionType;

import java.nio.file.Path;

public class Instances {
    private final static Instances INSTANCE =
            new Instances();
    private final static RegistryKey<DimensionType>
            DIMENSION_TYPE_FOR_ALL_INSTANCES =
            DimensionType.OVERWORLD;

    private Instance hubInstance;

    public Instance getHubInstance() {
        if (hubInstance == null)
            hubInstance = createHubInstance();
        return hubInstance;
    }

    public static Instances getInstance() {
        return INSTANCE;
    }

    private Instance createHubInstance() {
        Config config = Config.getInstance();

        Instance inst = MinecraftServer
                .getInstanceManager()
                .createInstanceContainer(
                        new AnvilLoader(
                                Path.of(config.getPathToHubWorld()),
                                DIMENSION_TYPE_FOR_ALL_INSTANCES.key()
                        )
                );

        return inst;
    }

    private Instances() {}
}
