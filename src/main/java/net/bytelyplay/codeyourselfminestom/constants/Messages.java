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
}
