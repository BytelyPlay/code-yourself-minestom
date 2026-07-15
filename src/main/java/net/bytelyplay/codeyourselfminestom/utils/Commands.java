package net.bytelyplay.codeyourselfminestom.utils;

import net.bytelyplay.codeyourselfminestom.commands.FullCommand;
import net.bytelyplay.codeyourselfminestom.commands.HubCommand;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;

public class Commands {
    private final CommandManager manager;

    public Commands(CommandManager manager) {
        this.manager = manager;
    }

    public final void register(FullCommand command) {
        manager.register(
                command.createCommand()
        );
    }

    public static void registerServerCommands(CommandManager manager) {
        Commands commands = new Commands(manager);

        commands.register(new HubCommand());
    }
}
