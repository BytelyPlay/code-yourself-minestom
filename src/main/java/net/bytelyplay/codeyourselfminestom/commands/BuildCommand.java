package net.bytelyplay.codeyourselfminestom.commands;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstance;
import net.bytelyplay.codeyourselfminestom.plots.PlotInstances;
import net.bytelyplay.codeyourselfminestom.plots.build.BuilderSystem;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;

import java.util.Optional;

public class BuildCommand implements FullCommand {
    @Override
    public Command createCommand() {
        Command cmd = new Command("build");
        cmd.setDefaultExecutor(this::execute);

        return cmd;
    }

    private void execute(CommandSender sender, CommandContext ctx) {
        if (sender instanceof Player p) {
            Optional<PlotInstance> instance =
                    PlotInstances
                            .getInstance()
                            .getPlotInstanceFromInstance(
                                    p.getInstance()
                            );
            if (instance.isEmpty()) {
                p.sendMessage(Messages.NOT_IN_A_PLOT);
                return;
            }
            BuilderSystem.getInstance()
                    .putPlayerInBuild(
                            instance.orElseThrow(), p
                    );
        } else {
            sender.sendMessage(Messages.ONLY_PLAYERS_CAN_DO_THIS);
        }
    }
}