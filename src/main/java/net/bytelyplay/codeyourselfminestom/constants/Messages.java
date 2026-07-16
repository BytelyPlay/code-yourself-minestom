package net.bytelyplay.codeyourselfminestom.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Messages {
    public static final Component TAKEN_TO_HUB_DUE_TO_INSTANCE_REMOVAL =
            Component.text(
                            "You were removed from this " +
                                    "instance due to it being removed."
                    )
                    .color(
                            TextColor.color(
                                    255,
                                    0,
                                    0
                            )
                    );
    public static final Component PLOT_DOESNT_EXIST =
            Component.text("This plot doesn't exist!")
                    .color(
                            TextColor.color(
                                    255,
                                    0,
                                    0
                            )
                    );
    public static final Component CREATING_PLOT =
            Component.text("Creating Plot");
    public static final Component CREATED_PLOT =
            Component.text("Plot Created");

    public static final Component TELEPORTING_TO_PLOT =
            Component.text("Teleporting to the Plot");
    public static final Component TELEPORTED_TO_PLOT =
            Component.text("Teleported to the Plot");

    public static final Component TELEPORTING_TO_HUB =
            Component.text("Teleporting to the Hub");
    public static final Component TELEPORTED_TO_HUB =
            Component.text("Teleported to the Hub");

    public static final Component ALREADY_IN_INSTANCE =
            Component.text("You are already in this instance.");
    public static final Component ALREADY_IN_HUB =
            Component.text("You are already in the Hub.");

    public static final Component ALREADY_IN_PLOT =
            Component.text("You're already in this plot.");

    public static final Component ONLY_PLAYERS_CAN_DO_THIS =
            Component.text("Only players can do this.");
}
