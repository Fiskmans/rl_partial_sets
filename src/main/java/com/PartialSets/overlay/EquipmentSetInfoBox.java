package com.PartialSets.overlay;

import com.PartialSets.EquipmentSet;
import com.PartialSets.PartialSetsConfig;
import com.PartialSets.PartialSetsPlugin;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.*;

public class EquipmentSetInfoBox extends InfoBox {

    EquipmentSet mySet;
    PartialSetsConfig myConfig;

    public EquipmentSetInfoBox(PartialSetsPlugin aPlugin, ItemManager aItemManager, PartialSetsConfig aConfig, EquipmentSet aSet)
    {
        super(aItemManager.getImage(aSet.myIconId), aPlugin);
        mySet = aSet;
        myConfig = aConfig;

        setTooltip("You are wearing a partial " + mySet.myName);
    }

    @Override
    public String getText() {
        return mySet.myLastCount + "/" + mySet.mySlots.length;
    }

    @Override
    public Color getTextColor() {
        return myConfig.TextColor();
    }
}
