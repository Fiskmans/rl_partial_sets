package com.PartialSets;

import net.runelite.api.ItemContainer;

import java.util.Arrays;
import java.util.List;

public class EquipmentSet {

    public ItemSlot[] mySlots;
    public int myIconId;
    public String myName;

    public int myLastCount;
    public int myFullCount;

    EquipmentSet(String aName, int aIconId, ItemSlot... slots)
    {
        mySlots = slots;
        myIconId = aIconId;
        myName = aName;
    }

    enum CheckResult
    {
        NONE,
        PARTIAL,
        FULL
    }

    CheckResult Check(ItemContainer aEquipment)
    {
        int count = 0;
        boolean shouldShow = false;

        myFullCount = mySlots.length;

        for (ItemSlot slot : mySlots) {
            switch (slot.IsEquipped(aEquipment))
            {
                case NotRequired:
                    myFullCount--;
                    break;
                case NotPresent:
                    break;
                case EquippedNoWarning:
                    count++;
                    break;
                case Equipped:
                    count++;
                    shouldShow = true;
                    break;
            }
        }

        myLastCount = count;

        if (shouldShow)
        {
            if (count == myFullCount)
                return  CheckResult.FULL;

            return CheckResult.PARTIAL;
        }

        return CheckResult.NONE;
    }
}
