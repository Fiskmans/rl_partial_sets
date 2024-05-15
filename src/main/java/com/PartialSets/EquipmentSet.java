package com.PartialSets;

import net.runelite.api.ItemContainer;

import java.util.List;

public class EquipmentSet {

    public List<List<Integer>> myItems;
    public int myIconId;
    public String myName;

    public int myLastCount;

    EquipmentSet(String aName, int aIconId, List<List<Integer>> aItems)
    {
        myItems = aItems;
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

        for (List<Integer> slot : myItems) {
            for (Integer item : slot) {
                if (aEquipment.contains(item))
                {
                    count++;
                    break;
                }
            }
        }

        myLastCount = count;

        if (count > 0)
        {
            if (count == myItems.size())
                return  CheckResult.FULL;

            return CheckResult.PARTIAL;
        }

        return CheckResult.NONE;
    }
}
