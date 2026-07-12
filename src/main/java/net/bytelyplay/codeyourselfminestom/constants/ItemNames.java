package net.bytelyplay.codeyourselfminestom.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

public class ItemNames {
    public static final Component CREATE_PLOT_ITEM_NAME =
            Component
                    .text("Create Plot")
                    .style(
                            Style.style(
                                    TextColor.color(
                                            0,
                                            255,
                                            0
                                    )
                            )
                    );
}
