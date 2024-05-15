package com.PartialSets;

import com.PartialSets.overlay.EquipmentSetInfoBox;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Partial Sets"
)
public class PartialSetsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PartialSetsConfig myConfig;
	@Inject
	private InfoBoxManager infoBoxes;
	@Inject
	private ItemManager myItemManager;

	List<EquipmentSet> mySets;

	@Override
	protected void startUp() throws Exception
	{
		log.info("PartialSets started!");
		Setup();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("PartialSets stopped!");
		mySets = null;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("partial_sets"))
			return;

		Setup();
		Check(client.getItemContainer(InventoryID.EQUIPMENT.getId()));
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged ev)
	{
		if (ev.getContainerId() != InventoryID.EQUIPMENT.getId())
			return;

		Check(ev.getItemContainer());
	}

	private void Check(ItemContainer aEquipment)
	{
		infoBoxes.removeIf(infoBox -> infoBox.getClass() == EquipmentSetInfoBox.class);

		for (EquipmentSet set : mySets) {
			switch (set.Check(aEquipment))
			{
				case PARTIAL:
					PartialSetEquipped(set);
					break;
				default:
					break;
			}
		}
	}

	@Provides
	PartialSetsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PartialSetsConfig.class);
	}

	void PartialSetEquipped(EquipmentSet aSet)
	{
		infoBoxes.addInfoBox(new EquipmentSetInfoBox(this, myItemManager, myConfig, aSet));
	}

	void Setup()
	{
		mySets = new ArrayList<>();

		if (myConfig.VoidKnight()) {
			mySets.add(new EquipmentSet("Set of Void Knight Equipment", ItemID.ELITE_VOID_TOP,
					Arrays.asList(
							Arrays.asList(
									ItemID.VOID_MELEE_HELM, ItemID.VOID_MELEE_HELM_L, ItemID.VOID_MELEE_HELM_LOR, ItemID.VOID_MELEE_HELM_OR,
									ItemID.VOID_MAGE_HELM, ItemID.VOID_MAGE_HELM_L, ItemID.VOID_MAGE_HELM_LOR, ItemID.VOID_MAGE_HELM_OR,
									ItemID.VOID_RANGER_HELM, ItemID.VOID_RANGER_HELM_L, ItemID.VOID_RANGER_HELM_LOR, ItemID.VOID_RANGER_HELM_OR),
							Arrays.asList(
									ItemID.ELITE_VOID_TOP, ItemID.ELITE_VOID_TOP_L, ItemID.ELITE_VOID_TOP_LOR, ItemID.ELITE_VOID_TOP_OR,
									ItemID.VOID_KNIGHT_TOP, ItemID.VOID_KNIGHT_TOP_L, ItemID.VOID_KNIGHT_TOP_LOR, ItemID.VOID_KNIGHT_TOP_OR),
							Arrays.asList(
									ItemID.ELITE_VOID_ROBE, ItemID.ELITE_VOID_ROBE_L, ItemID.ELITE_VOID_ROBE_LOR, ItemID.ELITE_VOID_ROBE_OR,
									ItemID.VOID_KNIGHT_ROBE, ItemID.VOID_KNIGHT_ROBE_L, ItemID.VOID_KNIGHT_ROBE_LOR, ItemID.VOID_KNIGHT_ROBE_OR),
							Arrays.asList(
									ItemID.VOID_KNIGHT_GLOVES, ItemID.VOID_KNIGHT_GLOVES_L, ItemID.VOID_KNIGHT_GLOVES_LOR, ItemID.VOID_KNIGHT_GLOVES_OR)
					)
			));
		}

		if (myConfig.Obsidian()) {
			mySets.add(new EquipmentSet("Set of Obsidian", ItemID.OBSIDIAN_HELMET,
					Arrays.asList(
							Arrays.asList(
									ItemID.OBSIDIAN_HELMET),
							Arrays.asList(
									ItemID.OBSIDIAN_PLATEBODY),
							Arrays.asList(
									ItemID.OBSIDIAN_PLATELEGS)
					)
			));
		}

		if (myConfig.Farming()) {
			mySets.add(new EquipmentSet("Farming Set", ItemID.FARMERS_STRAWHAT,
					Arrays.asList(
							Arrays.asList(
									ItemID.FARMERS_STRAWHAT, ItemID.FARMERS_STRAWHAT_13647),
							Arrays.asList(
									ItemID.FARMERS_JACKET, ItemID.FARMERS_SHIRT),
							Arrays.asList(
									ItemID.FARMERS_BORO_TROUSERS, ItemID.FARMERS_BORO_TROUSERS_13641),
							Arrays.asList(
									ItemID.FARMERS_BOOTS, ItemID.FARMERS_BOOTS_13645)
					)
			));
		}

		if (myConfig.Rogue()) {
			mySets.add(new EquipmentSet("Set of Rogue Equipment", ItemID.ROGUE_MASK,
					Arrays.asList(
							Arrays.asList(
									ItemID.ROGUE_MASK),
							Arrays.asList(
									ItemID.ROGUE_TOP),
							Arrays.asList(
									ItemID.ROGUE_GLOVES),
							Arrays.asList(
									ItemID.ROGUE_TROUSERS),
							Arrays.asList(
									ItemID.ROGUE_BOOTS)
					)
			));
		}

		if (myConfig.Hunter()) {
			mySets.add(new EquipmentSet("Set of Hunter Outfit", ItemID.GUILD_HUNTER_HEADWEAR,
					Arrays.asList(
							Arrays.asList(
									ItemID.GUILD_HUNTER_HEADWEAR),
							Arrays.asList(
									ItemID.GUILD_HUNTER_TOP),
							Arrays.asList(
									ItemID.GUILD_HUNTER_LEGS),
							Arrays.asList(
									ItemID.GUILD_HUNTER_BOOTS)
					)
			));
		}
		if (myConfig.Lumberjack()) {
			mySets.add(new EquipmentSet("Set of Lumberjack Outfit", ItemID.LUMBERJACK_TOP,
					Arrays.asList(
							Arrays.asList(
									ItemID.LUMBERJACK_HAT),
							Arrays.asList(
									ItemID.LUMBERJACK_TOP),
							Arrays.asList(
									ItemID.LUMBERJACK_LEGS),
							Arrays.asList(
									ItemID.LUMBERJACK_BOOTS)
					)
			));
		}
		if (myConfig.Carpenter()) {
			mySets.add(new EquipmentSet("Set of Carpenters Outfit", ItemID.CARPENTERS_HELMET,
					Arrays.asList(
							Arrays.asList(
									ItemID.CARPENTERS_HELMET),
							Arrays.asList(
									ItemID.CARPENTERS_SHIRT),
							Arrays.asList(
									ItemID.CARPENTERS_TROUSERS),
							Arrays.asList(
									ItemID.CARPENTERS_BOOTS)
					)
			));
		}
		if (myConfig.BloodMoon()) {
			mySets.add(new EquipmentSet("Set of Blood Moon Armour", ItemID.BLOOD_MOON_CHESTPLATE,
					Arrays.asList(
							Arrays.asList(
									ItemID.BLOOD_MOON_CHESTPLATE, ItemID.BLOOD_MOON_CHESTPLATE_29043),
							Arrays.asList(
									ItemID.BLOOD_MOON_HELM, ItemID.BLOOD_MOON_HELM_29047),
							Arrays.asList(
									ItemID.BLOOD_MOON_TASSETS, ItemID.BLOOD_MOON_TASSETS_29045)
					)
			));
		}
		if (myConfig.BlueMoon()) {
			mySets.add(new EquipmentSet("Set of Blue Moon Armour", ItemID.BLUE_MOON_CHESTPLATE,
					Arrays.asList(
							Arrays.asList(
									ItemID.BLUE_MOON_CHESTPLATE, ItemID.BLUE_MOON_CHESTPLATE_29037),
							Arrays.asList(
									ItemID.BLUE_MOON_HELM, ItemID.BLUE_MOON_HELM_29041),
							Arrays.asList(
									ItemID.BLUE_MOON_TASSETS, ItemID.BLUE_MOON_TASSETS_29039)
					)
			));
		}
		if (myConfig.EclipseMoon()) {
			mySets.add(new EquipmentSet("Set of Eclipse Moon Armour", ItemID.ECLIPSE_MOON_CHESTPLATE,
					Arrays.asList(
							Arrays.asList(
									ItemID.ECLIPSE_MOON_CHESTPLATE, ItemID.ECLIPSE_MOON_CHESTPLATE_29031),
							Arrays.asList(
									ItemID.ECLIPSE_MOON_HELM, ItemID.ECLIPSE_MOON_HELM_29035),
							Arrays.asList(
									ItemID.ECLIPSE_MOON_TASSETS, ItemID.ECLIPSE_MOON_TASSETS_29033)
					)
			));
		}
		if (myConfig.Virtus()) {
			mySets.add(new EquipmentSet("Set of Virtus Robes", ItemID.VIRTUS_MASK,
					Arrays.asList(
							Arrays.asList(
									ItemID.VIRTUS_MASK),
							Arrays.asList(
									ItemID.VIRTUS_ROBE_TOP),
							Arrays.asList(
									ItemID.VIRTUS_ROBE_BOTTOM)
					)
			));
		}
		if (myConfig.Swampbark()) {
			mySets.add(new EquipmentSet("Set of Swampbark Armour", ItemID.SWAMPBARK_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.SWAMPBARK_HELM),
							Arrays.asList(
									ItemID.SWAMPBARK_BODY),
							Arrays.asList(
									ItemID.SWAMPBARK_LEGS),
							Arrays.asList(
									ItemID.SWAMPBARK_BOOTS),
							Arrays.asList(
									ItemID.SWAMPBARK_GAUNTLETS)
					)
			));
		}
		if (myConfig.Bloodbark()) {
			mySets.add(new EquipmentSet("Set of Bloodbark Armour", ItemID.BLOODBARK_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.BLOODBARK_HELM),
							Arrays.asList(
									ItemID.BLOODBARK_BODY),
							Arrays.asList(
									ItemID.BLOODBARK_LEGS),
							Arrays.asList(
									ItemID.BLOODBARK_BOOTS),
							Arrays.asList(
									ItemID.BLOODBARK_GAUNTLETS)
					)
			));
		}
		if (myConfig.Inquisitor()) {
			mySets.add(new EquipmentSet("Set of Inquisitors Armour", ItemID.INQUISITORS_GREAT_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.INQUISITORS_GREAT_HELM, ItemID.INQUISITORS_GREAT_HELM_27195),
							Arrays.asList(
									ItemID.INQUISITORS_HAUBERK, ItemID.INQUISITORS_HAUBERK_27196),
							Arrays.asList(
									ItemID.INQUISITORS_PLATESKIRT, ItemID.INQUISITORS_PLATESKIRT_27197)
					)
			));
		}
		if (myConfig.Ahrims()) {
			mySets.add(new EquipmentSet("Set of Ahrims Robes", ItemID.AHRIMS_HOOD,
					Arrays.asList(
							Arrays.asList(
									ItemID.AHRIMS_HOOD, ItemID.AHRIMS_HOOD_100, ItemID.AHRIMS_HOOD_75, ItemID.AHRIMS_HOOD_50, ItemID.AHRIMS_HOOD_25),
							Arrays.asList(
									ItemID.AHRIMS_ROBETOP, ItemID.AHRIMS_ROBETOP_100, ItemID.AHRIMS_ROBETOP_75, ItemID.AHRIMS_ROBETOP_50, ItemID.AHRIMS_ROBETOP_25),
							Arrays.asList(
									ItemID.AHRIMS_ROBESKIRT, ItemID.AHRIMS_ROBESKIRT_100, ItemID.AHRIMS_ROBESKIRT_75, ItemID.AHRIMS_ROBESKIRT_50, ItemID.AHRIMS_ROBESKIRT_25)
					)
			));
		}
		if (myConfig.Dharoks()) {
			mySets.add(new EquipmentSet("Set of Dharoks Armour", ItemID.DHAROKS_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.DHAROKS_HELM, ItemID.DHAROKS_HELM_100, ItemID.DHAROKS_HELM_75, ItemID.DHAROKS_HELM_50, ItemID.DHAROKS_HELM_25),
							Arrays.asList(
									ItemID.DHAROKS_PLATEBODY, ItemID.DHAROKS_PLATEBODY_100, ItemID.DHAROKS_PLATEBODY_75, ItemID.DHAROKS_PLATEBODY_50, ItemID.DHAROKS_PLATEBODY_25),
							Arrays.asList(
									ItemID.DHAROKS_PLATELEGS, ItemID.DHAROKS_PLATELEGS_100, ItemID.DHAROKS_PLATELEGS_75, ItemID.DHAROKS_PLATELEGS_50, ItemID.DHAROKS_PLATELEGS_25)
					)
			));
		}
		if (myConfig.Guthans()) {
			mySets.add(new EquipmentSet("Set of Guthans Armour", ItemID.GUTHANS_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.GUTHANS_HELM, ItemID.GUTHANS_HELM_100, ItemID.GUTHANS_HELM_75, ItemID.GUTHANS_HELM_50, ItemID.GUTHANS_HELM_25),
							Arrays.asList(
									ItemID.GUTHANS_PLATEBODY, ItemID.GUTHANS_PLATEBODY_100, ItemID.GUTHANS_PLATEBODY_75, ItemID.GUTHANS_PLATEBODY_50, ItemID.GUTHANS_PLATEBODY_25),
							Arrays.asList(
									ItemID.GUTHANS_CHAINSKIRT, ItemID.GUTHANS_CHAINSKIRT_100, ItemID.GUTHANS_CHAINSKIRT_75, ItemID.GUTHANS_CHAINSKIRT_50, ItemID.GUTHANS_CHAINSKIRT_25)
					)
			));
		}
		if (myConfig.Karils()) {
			mySets.add(new EquipmentSet("Set of Karils Armour", ItemID.KARILS_COIF,
					Arrays.asList(
							Arrays.asList(
									ItemID.KARILS_COIF, ItemID.KARILS_COIF_100, ItemID.KARILS_COIF_75, ItemID.KARILS_COIF_50, ItemID.KARILS_COIF_25),
							Arrays.asList(
									ItemID.KARILS_LEATHERTOP, ItemID.KARILS_LEATHERTOP_100, ItemID.KARILS_LEATHERTOP_75, ItemID.KARILS_LEATHERTOP_50, ItemID.KARILS_LEATHERTOP_25),
							Arrays.asList(
									ItemID.KARILS_LEATHERSKIRT, ItemID.KARILS_LEATHERSKIRT_100, ItemID.KARILS_LEATHERSKIRT_75, ItemID.KARILS_LEATHERSKIRT_50, ItemID.KARILS_LEATHERSKIRT_25)
					)
			));
		}
		if (myConfig.Torags()) {
			mySets.add(new EquipmentSet("Set of Torags Armour", ItemID.TORAGS_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.TORAGS_HELM, ItemID.TORAGS_HELM_100, ItemID.TORAGS_HELM_75, ItemID.TORAGS_HELM_50, ItemID.TORAGS_HELM_25),
							Arrays.asList(
									ItemID.TORAGS_PLATEBODY, ItemID.TORAGS_PLATEBODY_100, ItemID.TORAGS_PLATEBODY_75, ItemID.TORAGS_PLATEBODY_50, ItemID.TORAGS_PLATEBODY_25),
							Arrays.asList(
									ItemID.TORAGS_PLATELEGS, ItemID.TORAGS_PLATELEGS_100, ItemID.TORAGS_PLATELEGS_75, ItemID.TORAGS_PLATELEGS_50, ItemID.TORAGS_PLATELEGS_25)
					)
			));
		}
		if (myConfig.Veracs()) {
			mySets.add(new EquipmentSet("Set of Veracs Armour", ItemID.VERACS_HELM,
					Arrays.asList(
							Arrays.asList(
									ItemID.VERACS_HELM, ItemID.VERACS_HELM_100, ItemID.VERACS_HELM_75, ItemID.VERACS_HELM_50, ItemID.VERACS_HELM_25),
							Arrays.asList(
									ItemID.VERACS_BRASSARD, ItemID.VERACS_BRASSARD_100, ItemID.VERACS_BRASSARD_75, ItemID.VERACS_BRASSARD_50, ItemID.VERACS_BRASSARD_25),
							Arrays.asList(
									ItemID.VERACS_PLATESKIRT, ItemID.VERACS_PLATESKIRT_100, ItemID.VERACS_PLATESKIRT_75, ItemID.VERACS_PLATESKIRT_50, ItemID.VERACS_PLATESKIRT_25)
					)
			));
		}
		if (myConfig.Graceful()) {
			mySets.add(new EquipmentSet("Set of Graceful", ItemID.GRACEFUL_HOOD,
					Arrays.asList( // This was pain
							Arrays.asList(ItemID.GRACEFUL_HOOD, ItemID.GRACEFUL_HOOD_11851, ItemID.GRACEFUL_HOOD_13579, ItemID.GRACEFUL_HOOD_13580,
									ItemID.GRACEFUL_HOOD_13591, ItemID.GRACEFUL_HOOD_13592, ItemID.GRACEFUL_HOOD_13603, ItemID.GRACEFUL_HOOD_13604,
									ItemID.GRACEFUL_HOOD_13615, ItemID.GRACEFUL_HOOD_13616, ItemID.GRACEFUL_HOOD_13627, ItemID.GRACEFUL_HOOD_13628,
									ItemID.GRACEFUL_HOOD_13667, ItemID.GRACEFUL_HOOD_13668, ItemID.GRACEFUL_HOOD_21061, ItemID.GRACEFUL_HOOD_21063,
									ItemID.GRACEFUL_HOOD_24743, ItemID.GRACEFUL_HOOD_24745, ItemID.GRACEFUL_HOOD_25069, ItemID.GRACEFUL_HOOD_25071,
									ItemID.GRACEFUL_HOOD_27444, ItemID.GRACEFUL_HOOD_27446),
							Arrays.asList(
									ItemID.GRACEFUL_TOP,       ItemID.GRACEFUL_TOP_11855, ItemID.GRACEFUL_TOP_13583, ItemID.GRACEFUL_TOP_13584,
									ItemID.GRACEFUL_TOP_13595, ItemID.GRACEFUL_TOP_13596, ItemID.GRACEFUL_TOP_13607, ItemID.GRACEFUL_TOP_13608,
									ItemID.GRACEFUL_TOP_13619, ItemID.GRACEFUL_TOP_13620, ItemID.GRACEFUL_TOP_13631, ItemID.GRACEFUL_TOP_13632,
									ItemID.GRACEFUL_TOP_13671, ItemID.GRACEFUL_TOP_13672, ItemID.GRACEFUL_TOP_21067, ItemID.GRACEFUL_TOP_21069,
									ItemID.GRACEFUL_TOP_24749, ItemID.GRACEFUL_TOP_24751, ItemID.GRACEFUL_TOP_25075, ItemID.GRACEFUL_TOP_25077,
									ItemID.GRACEFUL_TOP_27450, ItemID.GRACEFUL_TOP_27452),
							Arrays.asList(
									ItemID.GRACEFUL_LEGS,       ItemID.GRACEFUL_LEGS_11857, ItemID.GRACEFUL_LEGS_13585, ItemID.GRACEFUL_LEGS_13586,
									ItemID.GRACEFUL_LEGS_13597, ItemID.GRACEFUL_LEGS_13598, ItemID.GRACEFUL_LEGS_13609, ItemID.GRACEFUL_LEGS_13610,
									ItemID.GRACEFUL_LEGS_13621, ItemID.GRACEFUL_LEGS_13622, ItemID.GRACEFUL_LEGS_13633, ItemID.GRACEFUL_LEGS_13634,
									ItemID.GRACEFUL_LEGS_13673, ItemID.GRACEFUL_LEGS_13674, ItemID.GRACEFUL_LEGS_21070, ItemID.GRACEFUL_LEGS_21072,
									ItemID.GRACEFUL_LEGS_24752, ItemID.GRACEFUL_LEGS_24754, ItemID.GRACEFUL_LEGS_25078, ItemID.GRACEFUL_LEGS_25080,
									ItemID.GRACEFUL_LEGS_27453, ItemID.GRACEFUL_LEGS_27455),
							Arrays.asList(
									ItemID.GRACEFUL_GLOVES,       ItemID.GRACEFUL_GLOVES_11859, ItemID.GRACEFUL_GLOVES_13587, ItemID.GRACEFUL_GLOVES_13588,
									ItemID.GRACEFUL_GLOVES_13599, ItemID.GRACEFUL_GLOVES_13600, ItemID.GRACEFUL_GLOVES_13611, ItemID.GRACEFUL_GLOVES_13612,
									ItemID.GRACEFUL_GLOVES_13623, ItemID.GRACEFUL_GLOVES_13624, ItemID.GRACEFUL_GLOVES_13635, ItemID.GRACEFUL_GLOVES_13636,
									ItemID.GRACEFUL_GLOVES_13675, ItemID.GRACEFUL_GLOVES_13676, ItemID.GRACEFUL_GLOVES_21073, ItemID.GRACEFUL_GLOVES_21075,
									ItemID.GRACEFUL_GLOVES_24755, ItemID.GRACEFUL_GLOVES_24757, ItemID.GRACEFUL_GLOVES_25081, ItemID.GRACEFUL_GLOVES_25083,
									ItemID.GRACEFUL_GLOVES_27456, ItemID.GRACEFUL_GLOVES_27458),
							Arrays.asList(
									ItemID.GRACEFUL_BOOTS,       ItemID.GRACEFUL_BOOTS_11861, ItemID.GRACEFUL_BOOTS_13589, ItemID.GRACEFUL_BOOTS_13590,
									ItemID.GRACEFUL_BOOTS_13601, ItemID.GRACEFUL_BOOTS_13602, ItemID.GRACEFUL_BOOTS_13613, ItemID.GRACEFUL_BOOTS_13614,
									ItemID.GRACEFUL_BOOTS_13625, ItemID.GRACEFUL_BOOTS_13626, ItemID.GRACEFUL_BOOTS_13637, ItemID.GRACEFUL_BOOTS_13638,
									ItemID.GRACEFUL_BOOTS_13677, ItemID.GRACEFUL_BOOTS_13678, ItemID.GRACEFUL_BOOTS_21076, ItemID.GRACEFUL_BOOTS_21078,
									ItemID.GRACEFUL_BOOTS_24758, ItemID.GRACEFUL_BOOTS_24760, ItemID.GRACEFUL_BOOTS_25084, ItemID.GRACEFUL_BOOTS_25086,
									ItemID.GRACEFUL_BOOTS_27459, ItemID.GRACEFUL_BOOTS_27461),
							Arrays.asList(
									ItemID.GRACEFUL_CAPE,       ItemID.GRACEFUL_CAPE_11853, ItemID.GRACEFUL_CAPE_13581, ItemID.GRACEFUL_CAPE_13582,
									ItemID.GRACEFUL_CAPE_13593, ItemID.GRACEFUL_CAPE_13594, ItemID.GRACEFUL_CAPE_13605, ItemID.GRACEFUL_CAPE_13606,
									ItemID.GRACEFUL_CAPE_13617, ItemID.GRACEFUL_CAPE_13618, ItemID.GRACEFUL_CAPE_13629, ItemID.GRACEFUL_CAPE_13630,
									ItemID.GRACEFUL_CAPE_13669, ItemID.GRACEFUL_CAPE_13670, ItemID.GRACEFUL_CAPE_21064, ItemID.GRACEFUL_CAPE_21066,
									ItemID.GRACEFUL_CAPE_24746, ItemID.GRACEFUL_CAPE_24748, ItemID.GRACEFUL_CAPE_25072, ItemID.GRACEFUL_CAPE_25074,
									ItemID.GRACEFUL_CAPE_27447, ItemID.GRACEFUL_CAPE_27449)
					)
			));
		}
	}
}
