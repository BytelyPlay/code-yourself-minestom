package net.bytelyplay.codeyourselfminestom;

import net.bytelyplay.codeyourselfminestom.utils.Config;
import net.bytelyplay.codeyourselfminestom.utils.EventSetup;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;

import java.nio.file.Path;

public class Main {
    public static void main() {
        Config config = Config.getInstance();
        MinecraftServer server = MinecraftServer.init(new Auth.Online());

        EventSetup.getInstance()
                .setupEvents(
                        MinecraftServer
                                .getGlobalEventHandler()
                );

        server.start(
                config.getListenIp(),
                config.getListenPort()
        );
    }
}
