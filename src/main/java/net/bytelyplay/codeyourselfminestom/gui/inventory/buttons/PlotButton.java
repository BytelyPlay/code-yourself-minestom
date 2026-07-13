package net.bytelyplay.codeyourselfminestom.gui.inventory.buttons;

import net.bytelyplay.codeyourselfminestom.gui.inventory.Button;
import net.bytelyplay.codeyourselfminestom.utils.Instances;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.trait.InventoryEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PlotButton extends Button {
    private static final Logger log = LoggerFactory.getLogger(PlotButton.class);

    private final UUID plotId;

    public PlotButton(ItemStack baseItem, EventNode<InventoryEvent> eventNode, UUID plotId) {
        super(baseItem, eventNode);

        this.plotId = plotId;
    }

    @Override
    protected void preClick(InventoryPreClickEvent e) {
        e.getPlayer()
                .setInstance(
                        Instances.getInstance()
                                .getInstanceById(
                                        plotId
                                )
                );
    }
}
