package net.bytelyplay.codeyourselfminestom;

import net.bytelyplay.codeyourselfminestom.utils.Commands;
import net.bytelyplay.codeyourselfminestom.utils.Config;
import net.bytelyplay.codeyourselfminestom.utils.Events;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;

public class Main {
    public static void main() {
        Config config = Config.getInstance();
        MinecraftServer server = MinecraftServer.init(new Auth.Online());

        Events.getInstance()
                .setupEvents(
                        MinecraftServer
                                .getGlobalEventHandler()
                );
        Commands.registerServerCommands(MinecraftServer.getCommandManager());

        server.start(
                config.getListenIp(),
                config.getListenPort()
        );
    }
}
