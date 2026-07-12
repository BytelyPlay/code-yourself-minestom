package net.bytelyplay.codeyourselfminestom.gui.inventory.buttons;

import net.bytelyplay.codeyourselfminestom.gui.inventory.Button;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.trait.InventoryEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlotButton extends Button {
    private static final Logger log = LoggerFactory.getLogger(PlotButton.class);

    private static final ItemStack BASE_ITEM = ItemStack
            .of(Material.GRASS_BLOCK);

    public PlotButton(EventNode<InventoryEvent> eventNode) {
        super(BASE_ITEM, eventNode);
    }

    @Override
    protected void preClick(InventoryPreClickEvent e) {
        log.info("aasdwasdqwasdwasd");
    }
}
