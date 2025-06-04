package com.PartialSets;

import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;
import net.runelite.client.util.ColorUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ItemSlot {

    enum EquipStatus
    {
        NotPresent,
        Equipped,
        EquippedNoWarning,
        NotRequired
    }

    PartialSetsConfig myConfig;
    Integer myLastEquipped = -1;
    String myEmptyName = "";

    Integer[] myItems;
    Integer[] myNonWarning = new Integer[]{};

    ItemSlot(PartialSetsConfig aConfig, Integer... aItems)
    {
        myConfig = aConfig;
        myItems = aItems;

        assert myItems.length > 0;
    }

    // Some sets (Shayzien 5) have items (slayer helm) which can be used for the set bonus
    // but that should not raise a warning when used on their own
    ItemSlot NonWarningAlternatives(Integer... aItems)
    {
        myNonWarning = aItems;

        assert myNonWarning.length > 0;

        return this;
    }

    ItemSlot SetEmptyName(String aName)
    {
        myEmptyName = aName;
        return this;
    }

    public String toColoredString(ItemManager aItemManager)
    {
        if (myLastEquipped == -1)
        {
            if (myEmptyName.equals(""))
                myEmptyName = aItemManager.getItemComposition(myItems[0]).getMembersName();

            return "</br>" + ColorUtil.wrapWithColorTag(myEmptyName, myConfig.UnequippedTextColor());
        }
        else
        {
            return "</br>" + ColorUtil.wrapWithColorTag(aItemManager.getItemComposition(myLastEquipped).getMembersName(), myConfig.EquippedTextColor());
        }
    }

    EquipStatus IsEquipped(ItemContainer aEquipment)
    {
        {
            Optional<Integer> equipped = Arrays.stream(myItems)
                                            .filter(item ->  aEquipment.contains(item))
                                            .findFirst();

            if (equipped.isPresent())
            {
                myLastEquipped = equipped.get();
                return EquipStatus.Equipped;
            }
        }
        {
            Optional<Integer> equipped = Arrays.stream(myNonWarning)
                                            .filter(item ->  aEquipment.contains(item))
                                            .findFirst();

            if (equipped.isPresent())
            {
                myLastEquipped = equipped.get();
                return EquipStatus.EquippedNoWarning;
            }
        }

        myLastEquipped = -1;
        return EquipStatus.NotPresent;
    }
}
