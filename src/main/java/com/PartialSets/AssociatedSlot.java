package com.PartialSets;

import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;

public class AssociatedSlot extends ItemSlot
{
    AssociatedSlot(PartialSetsConfig aConfig, Integer... aItems)
    {
        super(aConfig, aItems);
    }

    @Override
    EquipStatus IsEquipped(ItemContainer aEquipment, ItemManager aManager)
    {
        if (!myConfig.Associated())
        {
            return EquipStatus.NotRequired;
        }

        switch (super.IsEquipped(aEquipment, aManager))
        {
            case Equipped:
            case EquippedNoWarning:
                return EquipStatus.EquippedNoWarning;
            case NotPresent:
                return EquipStatus.NotPresent;
            case NotRequired:
                return EquipStatus.NotRequired;
        }

        return EquipStatus.NotPresent;
    }

    @Override
    public String toColoredString(ItemManager aItemManager)
    {
        if (myConfig.Associated())
            return super.toColoredString(aItemManager);

        return "";
    }
}
