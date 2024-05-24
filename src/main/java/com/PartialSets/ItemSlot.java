package com.PartialSets;

import net.runelite.api.ItemContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemSlot {

    enum EquipStatus
    {
        NotPresent,
        Equipped,
        EquippedNoWarning
    }

    Integer[] myItems;
    Integer[] myNonWarning = new Integer[]{};

    ItemSlot(Integer... aItems)
    {
        myItems = aItems;
    }

    // Some sets (Shayzien 5) have items (slayer helm) which can be used for the set bonus
    // but that should not raise a warning when used on their own
    ItemSlot NonWarningAlternatives(Integer... aItems)
    {
        myNonWarning = aItems;

        return this;
    }

    EquipStatus IsEquipped(ItemContainer aEquipment)
    {
        if (Arrays.stream(myItems).anyMatch(item ->  aEquipment.contains(item)))
            return EquipStatus.Equipped;
        if (Arrays.stream(myNonWarning).anyMatch(item ->  aEquipment.contains(item)))
            return EquipStatus.EquippedNoWarning;

        return EquipStatus.NotPresent;
    }
}
