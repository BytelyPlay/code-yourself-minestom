package net.bytelyplay.codeyourselfminestom.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ItemNames {
    public static final Component MY_PLOTS =
            Component.text("My Plots")
                    .color(
                            TextColor.color(
                                    0,
                                    255,
                                    0
                            )
                    )
                    .decoration(TextDecoration.ITALIC, false);
    public static final Component CREATE_PLOT_BUTTON =
            Component.text("Create Plot")
                    .color(
                            TextColor.color(
                                    0,
                                    255,
                                    0
                            )
                    )
                    .decoration(
                            TextDecoration.ITALIC,
                            false
                    );
}
