package com.PartialSets;

import net.runelite.api.Item;
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

    enum Mode
    {
        Id,
        Name
    }

    Mode myMode;

    PartialSetsConfig myConfig;
    Integer myLastEquipped = -1;
    String myEmptyName = "";

    Integer[] myItems;
    Integer[] myNonWarning = new Integer[]{};

    ItemSlot(PartialSetsConfig aConfig, Integer... aItems)
    {
        myMode = Mode.Id;

        myConfig = aConfig;
        myItems = aItems;

        assert myItems.length > 0;
    }

    ItemSlot(PartialSetsConfig aConfig, String aName)
    {
        myMode = Mode.Name;

        myConfig = aConfig;
        myEmptyName = aName;
        myItems = new Integer[]{1}; //This should never be visible, here as a safeguard
    }

    // Some sets (Shayzien 5) have items (slayer helm) which can be used for the set bonus
    // but that should not raise a warning when used on their own
    ItemSlot NonWarningAlternatives(Integer... aItems)
    {
        myNonWarning = aItems;

        assert myNonWarning.length > 0;

        return this;
    }

    public String toColoredString(ItemManager aItemManager)
    {
        if (myLastEquipped == -1)
        {
            if (myEmptyName.equals(""))
                myEmptyName = aItemManager.getItemComposition(myItems[0]).getMembersName();

            if (myItems.length > 1)
            {
                return "</br>" + ColorUtil.wrapWithColorTag(myEmptyName + " (+" + (myItems.length - 1) + ")", myConfig.UnequippedTextColor());
            }
            else
            {
                return "</br>" + ColorUtil.wrapWithColorTag(myEmptyName, myConfig.UnequippedTextColor());
            }
        }
        else
        {
            return "</br>" + ColorUtil.wrapWithColorTag(aItemManager.getItemComposition(myLastEquipped).getMembersName(), myConfig.EquippedTextColor());
        }
    }

    EquipStatus IsEquipped(ItemContainer aEquipment, ItemManager aItemManager)
    {
        switch (myMode)
        {
            case Id:
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

            case Name:
                {
                    Optional<Item> equipped = Arrays.stream(aEquipment.getItems())
                            .filter(item -> myEmptyName.equals(aItemManager.getItemComposition(item.getId()).getName()))
                            .findFirst();

                    if (equipped.isPresent())
                    {
                        myLastEquipped = equipped.get().getId();
                        return EquipStatus.Equipped;
                    }
                }
                return EquipStatus.NotPresent;
        }

        return EquipStatus.NotRequired;
    }
}
