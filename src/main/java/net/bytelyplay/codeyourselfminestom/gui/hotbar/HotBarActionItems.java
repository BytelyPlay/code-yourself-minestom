package net.bytelyplay.codeyourselfminestom.gui.hotbar;

import net.bytelyplay.codeyourselfminestom.gui.hotbar.items.MyPlotsHotBarActionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotBarActionItems {
    private static final Logger log = LoggerFactory.getLogger(HotBarActionItems.class);

    public static final HotBarActionItem MY_PLOTS_ACTION_ITEM =
            MyPlotsHotBarActionItem.getInstance();
}
