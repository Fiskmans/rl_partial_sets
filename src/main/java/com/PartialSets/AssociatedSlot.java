package com.PartialSets;

import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;

public class AssociatedSlot extends ItemSlot
{
    AssociatedSlot(PartialSetsConfig aConfig, Integer... aItems)
    {
        super(aConfig, aItems);
        //NonWarningAlternatives(aItems);
    }

    @Override
    EquipStatus IsEquipped(ItemContainer aEquipment)
    {
        if (myConfig.Associated())
            return super.IsEquipped(aEquipment);

        return EquipStatus.NotRequired;
    }

    @Override
    public String toColoredString(ItemManager aItemManager)
    {
        if (myConfig.Associated())
            return super.toColoredString(aItemManager);

        return "";
    }
}
