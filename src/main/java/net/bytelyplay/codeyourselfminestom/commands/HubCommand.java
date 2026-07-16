package net.bytelyplay.codeyourselfminestom.commands;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.bytelyplay.codeyourselfminestom.utils.TeleportationHelpers;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;

public class HubCommand implements FullCommand {
    @Override
    public Command createCommand() {
        Command cmd = new Command("hub", "lobby");
        cmd.setDefaultExecutor(this::execute);

        return cmd;
    }

    private void execute(CommandSender sender, CommandContext ctx) {
        if (sender instanceof Player p) {
            TeleportationHelpers.teleportPlayerToHub(p);
        } else {
            sender.sendMessage(Messages.ONLY_PLAYERS_CAN_DO_THIS);
        }
    }
}
