package net.bytelyplay.codeyourselfminestom.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ItemNames {
    public static final Component PLOTS_ITEM_NAME =
            Component.text("Plots")
                    .color(
                            TextColor.color(
                                    0,
                                    255,
                                    0
                            )
                    )
                    .decoration(TextDecoration.ITALIC, false);
}
