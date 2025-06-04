package com.PartialSets.overlay;

import com.PartialSets.*;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ColorUtil;

import java.awt.*;

public class EquipmentSetInfoBox extends InfoBox {

    EquipmentSet mySet;
    PartialSetsConfig myConfig;

    public EquipmentSetInfoBox(PartialSetsPlugin aPlugin, ItemManager aItemManager, PartialSetsConfig aConfig, EquipmentSet aSet)
    {
        super(aItemManager.getImage(aSet.myIconId), aPlugin);
        mySet = aSet;
        myConfig = aConfig;

        String tooltip = "Partial " + ColorUtil.wrapWithColorTag(mySet.myName, myConfig.SetTextColor()) + " equipped";

        for (ItemSlot slot : mySet.mySlots) {
            tooltip += slot.toColoredString(aItemManager);
        }

        setTooltip(tooltip);
    }

    @Override
    public String getText() {
        return mySet.myLastCount + "/" + mySet.myFullCount;
    }

    @Override
    public Color getTextColor() {
        return myConfig.TextColor();
    }
}
