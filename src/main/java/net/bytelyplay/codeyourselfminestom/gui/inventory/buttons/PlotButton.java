package net.bytelyplay.codeyourselfminestom.gui.inventory.buttons;

import net.bytelyplay.codeyourselfminestom.constants.Messages;
import net.bytelyplay.codeyourselfminestom.utils.CachedPlotInstanceData;
import net.bytelyplay.codeyourselfminestom.utils.PlotInstanceData;
import net.bytelyplay.codeyourselfminestom.utils.PlotInstances;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.inventory.AbstractInventory;
import net.minestom.server.inventory.click.Click;
import net.minestom.server.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class PlotButton extends Button {
    private static final Logger log = LoggerFactory.getLogger(PlotButton.class);

    private final UUID plotId;

    public PlotButton(ItemStack baseItem, UUID plotId) {
        super(baseItem);

        this.plotId = plotId;
    }

    @Override
    protected void click(Click click, AbstractInventory inv, Player p) {
        Optional<CachedPlotInstanceData> optData =
                PlotInstances.getInstance()
                        .getPlotInstanceById(plotId);
        if (optData.isEmpty()) {
            p.closeInventory();
            p.sendMessage(Messages.PLOT_DOESNT_EXIST);

            return;
        }
        CachedPlotInstanceData cachedData = optData.orElseThrow();
        PlotInstanceData data = cachedData.data();

        Instance plotInstance = cachedData.instance();

        if (plotInstance.equals(p.getInstance())) {
            p.closeInventory();
            p.sendMessage(Messages.ALREADY_IN_PLOT);

            return;
        }

        p.setInstance(
                plotInstance,
                data.spawnPos()
        );
        p.closeInventory();
    }
}
