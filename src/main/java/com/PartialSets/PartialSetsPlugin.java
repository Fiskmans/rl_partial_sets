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
		Reset();
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
		Reset();
		
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
					new ItemSlot(
								ItemID.VOID_MELEE_HELM, ItemID.VOID_MELEE_HELM_L, ItemID.VOID_MELEE_HELM_LOR, ItemID.VOID_MELEE_HELM_OR,
								ItemID.VOID_MAGE_HELM, ItemID.VOID_MAGE_HELM_L, ItemID.VOID_MAGE_HELM_LOR, ItemID.VOID_MAGE_HELM_OR,
								ItemID.VOID_RANGER_HELM, ItemID.VOID_RANGER_HELM_L, ItemID.VOID_RANGER_HELM_LOR, ItemID.VOID_RANGER_HELM_OR),
					new ItemSlot(
								ItemID.ELITE_VOID_TOP, ItemID.ELITE_VOID_TOP_L, ItemID.ELITE_VOID_TOP_LOR, ItemID.ELITE_VOID_TOP_OR,
								ItemID.VOID_KNIGHT_TOP, ItemID.VOID_KNIGHT_TOP_L, ItemID.VOID_KNIGHT_TOP_LOR, ItemID.VOID_KNIGHT_TOP_OR),
					new ItemSlot(
								ItemID.ELITE_VOID_ROBE, ItemID.ELITE_VOID_ROBE_L, ItemID.ELITE_VOID_ROBE_LOR, ItemID.ELITE_VOID_ROBE_OR,
								ItemID.VOID_KNIGHT_ROBE, ItemID.VOID_KNIGHT_ROBE_L, ItemID.VOID_KNIGHT_ROBE_LOR, ItemID.VOID_KNIGHT_ROBE_OR),
					new ItemSlot(
								ItemID.VOID_KNIGHT_GLOVES, ItemID.VOID_KNIGHT_GLOVES_L, ItemID.VOID_KNIGHT_GLOVES_LOR, ItemID.VOID_KNIGHT_GLOVES_OR)

			));
		}

		if (myConfig.Obsidian()) {
			mySets.add(new EquipmentSet("Set of Obsidian", ItemID.OBSIDIAN_HELMET,
					new ItemSlot(ItemID.OBSIDIAN_HELMET),
					new ItemSlot(ItemID.OBSIDIAN_PLATEBODY),
					new ItemSlot(ItemID.OBSIDIAN_PLATELEGS)
			));
		}

		if (myConfig.Farming()) {
			mySets.add(new EquipmentSet("Farming Set", ItemID.FARMERS_STRAWHAT,
					new ItemSlot(ItemID.FARMERS_STRAWHAT, ItemID.FARMERS_STRAWHAT_13647),
					new ItemSlot(ItemID.FARMERS_JACKET, ItemID.FARMERS_SHIRT),
					new ItemSlot(ItemID.FARMERS_BORO_TROUSERS, ItemID.FARMERS_BORO_TROUSERS_13641),
					new ItemSlot(ItemID.FARMERS_BOOTS, ItemID.FARMERS_BOOTS_13645)
			));
		}

		if (myConfig.Rogue()) {
			mySets.add(new EquipmentSet("Set of Rogue Equipment", ItemID.ROGUE_MASK,
					new ItemSlot(ItemID.ROGUE_MASK),
					new ItemSlot(ItemID.ROGUE_TOP),
					new ItemSlot(ItemID.ROGUE_GLOVES),
					new ItemSlot(ItemID.ROGUE_TROUSERS),
					new ItemSlot(ItemID.ROGUE_BOOTS)
			));
		}

		if (myConfig.Hunter()) {
			mySets.add(new EquipmentSet("Set of Hunter Outfit", ItemID.GUILD_HUNTER_HEADWEAR,
					new ItemSlot(ItemID.GUILD_HUNTER_HEADWEAR),
					new ItemSlot(ItemID.GUILD_HUNTER_TOP),
					new ItemSlot(ItemID.GUILD_HUNTER_LEGS),
					new ItemSlot(ItemID.GUILD_HUNTER_BOOTS)
			));
		}
		if (myConfig.Lumberjack()) {
			mySets.add(new EquipmentSet("Set of Lumberjack Outfit", ItemID.LUMBERJACK_TOP,
					new ItemSlot(ItemID.LUMBERJACK_HAT),
					new ItemSlot(ItemID.LUMBERJACK_TOP),
					new ItemSlot(ItemID.LUMBERJACK_LEGS),
					new ItemSlot(ItemID.LUMBERJACK_BOOTS)
			));
		}
		if (myConfig.Carpenter()) {
			mySets.add(new EquipmentSet("Set of Carpenters Outfit", ItemID.CARPENTERS_HELMET,
					new ItemSlot(ItemID.CARPENTERS_HELMET),
					new ItemSlot(ItemID.CARPENTERS_SHIRT),
					new ItemSlot(ItemID.CARPENTERS_TROUSERS),
					new ItemSlot(ItemID.CARPENTERS_BOOTS)
			));
		}
		if (myConfig.BloodMoon()) {
			mySets.add(new EquipmentSet("Set of Blood Moon Armour", ItemID.BLOOD_MOON_CHESTPLATE,
					new ItemSlot(ItemID.BLOOD_MOON_CHESTPLATE, ItemID.BLOOD_MOON_CHESTPLATE_29043),
					new ItemSlot(ItemID.BLOOD_MOON_HELM, ItemID.BLOOD_MOON_HELM_29047),
					new ItemSlot(ItemID.BLOOD_MOON_TASSETS, ItemID.BLOOD_MOON_TASSETS_29045)
			));
		}
		if (myConfig.BlueMoon()) {
			mySets.add(new EquipmentSet("Set of Blue Moon Armour", ItemID.BLUE_MOON_CHESTPLATE,
					new ItemSlot(ItemID.BLUE_MOON_CHESTPLATE, ItemID.BLUE_MOON_CHESTPLATE_29037),
					new ItemSlot(ItemID.BLUE_MOON_HELM, ItemID.BLUE_MOON_HELM_29041),
					new ItemSlot(ItemID.BLUE_MOON_TASSETS, ItemID.BLUE_MOON_TASSETS_29039)
			));
		}
		if (myConfig.EclipseMoon()) {
			mySets.add(new EquipmentSet("Set of Eclipse Moon Armour", ItemID.ECLIPSE_MOON_CHESTPLATE,
					new ItemSlot(ItemID.ECLIPSE_MOON_CHESTPLATE, ItemID.ECLIPSE_MOON_CHESTPLATE_29031),
					new ItemSlot(ItemID.ECLIPSE_MOON_HELM, ItemID.ECLIPSE_MOON_HELM_29035),
					new ItemSlot(ItemID.ECLIPSE_MOON_TASSETS, ItemID.ECLIPSE_MOON_TASSETS_29033)
			));
		}
		if (myConfig.Virtus()) {
			mySets.add(new EquipmentSet("Set of Virtus Robes", ItemID.VIRTUS_MASK,
					new ItemSlot(ItemID.VIRTUS_MASK),
					new ItemSlot(ItemID.VIRTUS_ROBE_TOP),
					new ItemSlot(ItemID.VIRTUS_ROBE_BOTTOM)
			));
		}
		if (myConfig.Swampbark()) {
			mySets.add(new EquipmentSet("Set of Swampbark Armour", ItemID.SWAMPBARK_HELM,
					new ItemSlot(ItemID.SWAMPBARK_HELM),
					new ItemSlot(ItemID.SWAMPBARK_BODY),
					new ItemSlot(ItemID.SWAMPBARK_LEGS),
					new ItemSlot(ItemID.SWAMPBARK_BOOTS),
					new ItemSlot(ItemID.SWAMPBARK_GAUNTLETS)
			));
		}
		if (myConfig.Bloodbark()) {
			mySets.add(new EquipmentSet("Set of Bloodbark Armour", ItemID.BLOODBARK_HELM,
					new ItemSlot(ItemID.BLOODBARK_HELM),
					new ItemSlot(ItemID.BLOODBARK_BODY),
					new ItemSlot(ItemID.BLOODBARK_LEGS),
					new ItemSlot(ItemID.BLOODBARK_BOOTS),
					new ItemSlot(ItemID.BLOODBARK_GAUNTLETS)
			));
		}
		if (myConfig.Inquisitor()) {
			mySets.add(new EquipmentSet("Set of Inquisitors Armour", ItemID.INQUISITORS_GREAT_HELM,
					new ItemSlot(ItemID.INQUISITORS_GREAT_HELM, ItemID.INQUISITORS_GREAT_HELM_27195),
					new ItemSlot(ItemID.INQUISITORS_HAUBERK, ItemID.INQUISITORS_HAUBERK_27196),
					new ItemSlot(ItemID.INQUISITORS_PLATESKIRT, ItemID.INQUISITORS_PLATESKIRT_27197)
			));
		}
		if (myConfig.Ahrims()) {
			mySets.add(new EquipmentSet("Set of Ahrims Robes", ItemID.AHRIMS_HOOD,
					new ItemSlot(ItemID.AHRIMS_HOOD, ItemID.AHRIMS_HOOD_100, ItemID.AHRIMS_HOOD_75, ItemID.AHRIMS_HOOD_50, ItemID.AHRIMS_HOOD_25),
					new ItemSlot(ItemID.AHRIMS_ROBETOP, ItemID.AHRIMS_ROBETOP_100, ItemID.AHRIMS_ROBETOP_75, ItemID.AHRIMS_ROBETOP_50, ItemID.AHRIMS_ROBETOP_25),
					new ItemSlot(ItemID.AHRIMS_ROBESKIRT, ItemID.AHRIMS_ROBESKIRT_100, ItemID.AHRIMS_ROBESKIRT_75, ItemID.AHRIMS_ROBESKIRT_50, ItemID.AHRIMS_ROBESKIRT_25)
			));
		}
		if (myConfig.Dharoks()) {
			mySets.add(new EquipmentSet("Set of Dharoks Armour", ItemID.DHAROKS_HELM,
					new ItemSlot(ItemID.DHAROKS_HELM, ItemID.DHAROKS_HELM_100, ItemID.DHAROKS_HELM_75, ItemID.DHAROKS_HELM_50, ItemID.DHAROKS_HELM_25),
					new ItemSlot(ItemID.DHAROKS_PLATEBODY, ItemID.DHAROKS_PLATEBODY_100, ItemID.DHAROKS_PLATEBODY_75, ItemID.DHAROKS_PLATEBODY_50, ItemID.DHAROKS_PLATEBODY_25),
					new ItemSlot(ItemID.DHAROKS_PLATELEGS, ItemID.DHAROKS_PLATELEGS_100, ItemID.DHAROKS_PLATELEGS_75, ItemID.DHAROKS_PLATELEGS_50, ItemID.DHAROKS_PLATELEGS_25)
			));
		}
		if (myConfig.Guthans()) {
			mySets.add(new EquipmentSet("Set of Guthans Armour", ItemID.GUTHANS_HELM,
					new ItemSlot(ItemID.GUTHANS_HELM, ItemID.GUTHANS_HELM_100, ItemID.GUTHANS_HELM_75, ItemID.GUTHANS_HELM_50, ItemID.GUTHANS_HELM_25),
					new ItemSlot(ItemID.GUTHANS_PLATEBODY, ItemID.GUTHANS_PLATEBODY_100, ItemID.GUTHANS_PLATEBODY_75, ItemID.GUTHANS_PLATEBODY_50, ItemID.GUTHANS_PLATEBODY_25),
					new ItemSlot(ItemID.GUTHANS_CHAINSKIRT, ItemID.GUTHANS_CHAINSKIRT_100, ItemID.GUTHANS_CHAINSKIRT_75, ItemID.GUTHANS_CHAINSKIRT_50, ItemID.GUTHANS_CHAINSKIRT_25)
			));
		}
		if (myConfig.Karils()) {
			mySets.add(new EquipmentSet("Set of Karils Armour", ItemID.KARILS_COIF,
					new ItemSlot(ItemID.KARILS_COIF, ItemID.KARILS_COIF_100, ItemID.KARILS_COIF_75, ItemID.KARILS_COIF_50, ItemID.KARILS_COIF_25),
					new ItemSlot(ItemID.KARILS_LEATHERTOP, ItemID.KARILS_LEATHERTOP_100, ItemID.KARILS_LEATHERTOP_75, ItemID.KARILS_LEATHERTOP_50, ItemID.KARILS_LEATHERTOP_25),
					new ItemSlot(ItemID.KARILS_LEATHERSKIRT, ItemID.KARILS_LEATHERSKIRT_100, ItemID.KARILS_LEATHERSKIRT_75, ItemID.KARILS_LEATHERSKIRT_50, ItemID.KARILS_LEATHERSKIRT_25)
			));
		}
		if (myConfig.Torags()) {
			mySets.add(new EquipmentSet("Set of Torags Armour", ItemID.TORAGS_HELM,
					new ItemSlot(ItemID.TORAGS_HELM, ItemID.TORAGS_HELM_100, ItemID.TORAGS_HELM_75, ItemID.TORAGS_HELM_50, ItemID.TORAGS_HELM_25),
					new ItemSlot(ItemID.TORAGS_PLATEBODY, ItemID.TORAGS_PLATEBODY_100, ItemID.TORAGS_PLATEBODY_75, ItemID.TORAGS_PLATEBODY_50, ItemID.TORAGS_PLATEBODY_25),
					new ItemSlot(ItemID.TORAGS_PLATELEGS, ItemID.TORAGS_PLATELEGS_100, ItemID.TORAGS_PLATELEGS_75, ItemID.TORAGS_PLATELEGS_50, ItemID.TORAGS_PLATELEGS_25)
			));
		}
		if (myConfig.Veracs()) {
			mySets.add(new EquipmentSet("Set of Veracs Armour", ItemID.VERACS_HELM,
					new ItemSlot(ItemID.VERACS_HELM, ItemID.VERACS_HELM_100, ItemID.VERACS_HELM_75, ItemID.VERACS_HELM_50, ItemID.VERACS_HELM_25),
					new ItemSlot(ItemID.VERACS_BRASSARD, ItemID.VERACS_BRASSARD_100, ItemID.VERACS_BRASSARD_75, ItemID.VERACS_BRASSARD_50, ItemID.VERACS_BRASSARD_25),
					new ItemSlot(ItemID.VERACS_PLATESKIRT, ItemID.VERACS_PLATESKIRT_100, ItemID.VERACS_PLATESKIRT_75, ItemID.VERACS_PLATESKIRT_50, ItemID.VERACS_PLATESKIRT_25)
			));
		}
		if (myConfig.Graceful()) {
			mySets.add(new EquipmentSet("Set of Graceful", ItemID.GRACEFUL_HOOD,
					new ItemSlot(ItemID.GRACEFUL_HOOD, ItemID.GRACEFUL_HOOD_11851, ItemID.GRACEFUL_HOOD_13579, ItemID.GRACEFUL_HOOD_13580,
							ItemID.GRACEFUL_HOOD_13591, ItemID.GRACEFUL_HOOD_13592, ItemID.GRACEFUL_HOOD_13603, ItemID.GRACEFUL_HOOD_13604,
							ItemID.GRACEFUL_HOOD_13615, ItemID.GRACEFUL_HOOD_13616, ItemID.GRACEFUL_HOOD_13627, ItemID.GRACEFUL_HOOD_13628,
							ItemID.GRACEFUL_HOOD_13667, ItemID.GRACEFUL_HOOD_13668, ItemID.GRACEFUL_HOOD_21061, ItemID.GRACEFUL_HOOD_21063,
							ItemID.GRACEFUL_HOOD_24743, ItemID.GRACEFUL_HOOD_24745, ItemID.GRACEFUL_HOOD_25069, ItemID.GRACEFUL_HOOD_25071,
							ItemID.GRACEFUL_HOOD_27444, ItemID.GRACEFUL_HOOD_27446),
					new ItemSlot(
							ItemID.GRACEFUL_TOP,       ItemID.GRACEFUL_TOP_11855, ItemID.GRACEFUL_TOP_13583, ItemID.GRACEFUL_TOP_13584,
							ItemID.GRACEFUL_TOP_13595, ItemID.GRACEFUL_TOP_13596, ItemID.GRACEFUL_TOP_13607, ItemID.GRACEFUL_TOP_13608,
							ItemID.GRACEFUL_TOP_13619, ItemID.GRACEFUL_TOP_13620, ItemID.GRACEFUL_TOP_13631, ItemID.GRACEFUL_TOP_13632,
							ItemID.GRACEFUL_TOP_13671, ItemID.GRACEFUL_TOP_13672, ItemID.GRACEFUL_TOP_21067, ItemID.GRACEFUL_TOP_21069,
							ItemID.GRACEFUL_TOP_24749, ItemID.GRACEFUL_TOP_24751, ItemID.GRACEFUL_TOP_25075, ItemID.GRACEFUL_TOP_25077,
							ItemID.GRACEFUL_TOP_27450, ItemID.GRACEFUL_TOP_27452),
					new ItemSlot(
							ItemID.GRACEFUL_LEGS,       ItemID.GRACEFUL_LEGS_11857, ItemID.GRACEFUL_LEGS_13585, ItemID.GRACEFUL_LEGS_13586,
							ItemID.GRACEFUL_LEGS_13597, ItemID.GRACEFUL_LEGS_13598, ItemID.GRACEFUL_LEGS_13609, ItemID.GRACEFUL_LEGS_13610,
							ItemID.GRACEFUL_LEGS_13621, ItemID.GRACEFUL_LEGS_13622, ItemID.GRACEFUL_LEGS_13633, ItemID.GRACEFUL_LEGS_13634,
							ItemID.GRACEFUL_LEGS_13673, ItemID.GRACEFUL_LEGS_13674, ItemID.GRACEFUL_LEGS_21070, ItemID.GRACEFUL_LEGS_21072,
							ItemID.GRACEFUL_LEGS_24752, ItemID.GRACEFUL_LEGS_24754, ItemID.GRACEFUL_LEGS_25078, ItemID.GRACEFUL_LEGS_25080,
							ItemID.GRACEFUL_LEGS_27453, ItemID.GRACEFUL_LEGS_27455),
					new ItemSlot(
							ItemID.GRACEFUL_GLOVES,       ItemID.GRACEFUL_GLOVES_11859, ItemID.GRACEFUL_GLOVES_13587, ItemID.GRACEFUL_GLOVES_13588,
							ItemID.GRACEFUL_GLOVES_13599, ItemID.GRACEFUL_GLOVES_13600, ItemID.GRACEFUL_GLOVES_13611, ItemID.GRACEFUL_GLOVES_13612,
							ItemID.GRACEFUL_GLOVES_13623, ItemID.GRACEFUL_GLOVES_13624, ItemID.GRACEFUL_GLOVES_13635, ItemID.GRACEFUL_GLOVES_13636,
							ItemID.GRACEFUL_GLOVES_13675, ItemID.GRACEFUL_GLOVES_13676, ItemID.GRACEFUL_GLOVES_21073, ItemID.GRACEFUL_GLOVES_21075,
							ItemID.GRACEFUL_GLOVES_24755, ItemID.GRACEFUL_GLOVES_24757, ItemID.GRACEFUL_GLOVES_25081, ItemID.GRACEFUL_GLOVES_25083,
							ItemID.GRACEFUL_GLOVES_27456, ItemID.GRACEFUL_GLOVES_27458),
					new ItemSlot(
							ItemID.GRACEFUL_BOOTS,       ItemID.GRACEFUL_BOOTS_11861, ItemID.GRACEFUL_BOOTS_13589, ItemID.GRACEFUL_BOOTS_13590,
							ItemID.GRACEFUL_BOOTS_13601, ItemID.GRACEFUL_BOOTS_13602, ItemID.GRACEFUL_BOOTS_13613, ItemID.GRACEFUL_BOOTS_13614,
							ItemID.GRACEFUL_BOOTS_13625, ItemID.GRACEFUL_BOOTS_13626, ItemID.GRACEFUL_BOOTS_13637, ItemID.GRACEFUL_BOOTS_13638,
							ItemID.GRACEFUL_BOOTS_13677, ItemID.GRACEFUL_BOOTS_13678, ItemID.GRACEFUL_BOOTS_21076, ItemID.GRACEFUL_BOOTS_21078,
							ItemID.GRACEFUL_BOOTS_24758, ItemID.GRACEFUL_BOOTS_24760, ItemID.GRACEFUL_BOOTS_25084, ItemID.GRACEFUL_BOOTS_25086,
							ItemID.GRACEFUL_BOOTS_27459, ItemID.GRACEFUL_BOOTS_27461),
					new ItemSlot(
							ItemID.GRACEFUL_CAPE,       ItemID.GRACEFUL_CAPE_11853, ItemID.GRACEFUL_CAPE_13581, ItemID.GRACEFUL_CAPE_13582,
							ItemID.GRACEFUL_CAPE_13593, ItemID.GRACEFUL_CAPE_13594, ItemID.GRACEFUL_CAPE_13605, ItemID.GRACEFUL_CAPE_13606,
							ItemID.GRACEFUL_CAPE_13617, ItemID.GRACEFUL_CAPE_13618, ItemID.GRACEFUL_CAPE_13629, ItemID.GRACEFUL_CAPE_13630,
							ItemID.GRACEFUL_CAPE_13669, ItemID.GRACEFUL_CAPE_13670, ItemID.GRACEFUL_CAPE_21064, ItemID.GRACEFUL_CAPE_21066,
							ItemID.GRACEFUL_CAPE_24746, ItemID.GRACEFUL_CAPE_24748, ItemID.GRACEFUL_CAPE_25072, ItemID.GRACEFUL_CAPE_25074,
							ItemID.GRACEFUL_CAPE_27447, ItemID.GRACEFUL_CAPE_27449)
			));
		}
		if (myConfig.Crystal()) {
			mySets.add(new EquipmentSet("Set of Crystal armour", ItemID.CRYSTAL_HELM,
					new ItemSlot(
							ItemID.CRYSTAL_HELM, 		ItemID.CRYSTAL_HELM_27705, ItemID.CRYSTAL_HELM_27717, ItemID.CRYSTAL_HELM_27729,
							ItemID.CRYSTAL_HELM_27741, 	ItemID.CRYSTAL_HELM_27753, ItemID.CRYSTAL_HELM_27765, ItemID.CRYSTAL_HELM_27777),
					new ItemSlot(
							ItemID.CRYSTAL_BODY, 		ItemID.CRYSTAL_BODY_27697, ItemID.CRYSTAL_BODY_27709, ItemID.CRYSTAL_BODY_27721,
							ItemID.CRYSTAL_BODY_27733, 	ItemID.CRYSTAL_BODY_27745, ItemID.CRYSTAL_BODY_27757, ItemID.CRYSTAL_BODY_27769),
					new ItemSlot(
							ItemID.CRYSTAL_LEGS, 		ItemID.CRYSTAL_LEGS_27701, ItemID.CRYSTAL_LEGS_27713, ItemID.CRYSTAL_LEGS_27725,
							ItemID.CRYSTAL_LEGS_27737, 	ItemID.CRYSTAL_LEGS_27749, ItemID.CRYSTAL_LEGS_27761, ItemID.CRYSTAL_LEGS_27773)
			));
		}
		if (myConfig.Shayzien()) {
			mySets.add(new EquipmentSet("Set of Shayzien tier 1", ItemID.SHAYZIEN_HELM_1,
					new ItemSlot(ItemID.SHAYZIEN_HELM_1),
					new ItemSlot(ItemID.SHAYZIEN_PLATEBODY_1),
					new ItemSlot(ItemID.SHAYZIEN_GREAVES_1),
					new ItemSlot(ItemID.SHAYZIEN_GLOVES_1),
					new ItemSlot(ItemID.SHAYZIEN_BOOTS_1)
			));
			mySets.add(new EquipmentSet("Set of Shayzien tier 2", ItemID.SHAYZIEN_HELM_2,
					new ItemSlot(ItemID.SHAYZIEN_HELM_2),
					new ItemSlot(ItemID.SHAYZIEN_PLATEBODY_2),
					new ItemSlot(ItemID.SHAYZIEN_GREAVES_2),
					new ItemSlot(ItemID.SHAYZIEN_GLOVES_2),
					new ItemSlot(ItemID.SHAYZIEN_BOOTS_2)
			));
			mySets.add(new EquipmentSet("Set of Shayzien tier 3", ItemID.SHAYZIEN_HELM_3,
					new ItemSlot(ItemID.SHAYZIEN_HELM_3),
					new ItemSlot(ItemID.SHAYZIEN_PLATEBODY_3),
					new ItemSlot(ItemID.SHAYZIEN_GREAVES_3),
					new ItemSlot(ItemID.SHAYZIEN_GLOVES_3),
					new ItemSlot(ItemID.SHAYZIEN_BOOTS_3)
			));
			mySets.add(new EquipmentSet("Set of Shayzien tier 4", ItemID.SHAYZIEN_HELM_4,
					new ItemSlot(ItemID.SHAYZIEN_HELM_4),
					new ItemSlot(ItemID.SHAYZIEN_PLATEBODY_4),
					new ItemSlot(ItemID.SHAYZIEN_GREAVES_4),
					new ItemSlot(ItemID.SHAYZIEN_GLOVES_4),
					new ItemSlot(ItemID.SHAYZIEN_BOOTS_4)
			));
			mySets.add(new EquipmentSet("Set of Shayzien tier 5", ItemID.SHAYZIEN_HELM_5,
					new ItemSlot(ItemID.SHAYZIEN_HELM_5)
							.NonWarningAlternatives(
									ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET,
									ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I,
									ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET,
									ItemID.PURPLE_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I,
									ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET,
									ItemID.TWISTED_SLAYER_HELMET_I, ItemID.SLAYER_HELMET_I_25177, ItemID.BLACK_SLAYER_HELMET_I_25179,
									ItemID.GREEN_SLAYER_HELMET_I_25181, ItemID.RED_SLAYER_HELMET_I_25183, ItemID.PURPLE_SLAYER_HELMET_I_25185,
									ItemID.TURQUOISE_SLAYER_HELMET_I_25187, ItemID.HYDRA_SLAYER_HELMET_I_25189, ItemID.TWISTED_SLAYER_HELMET_I_25191,
									ItemID.TZTOK_SLAYER_HELMET, ItemID.TZTOK_SLAYER_HELMET_I, ItemID.TZTOK_SLAYER_HELMET_I_25902,
									ItemID.VAMPYRIC_SLAYER_HELMET, ItemID.VAMPYRIC_SLAYER_HELMET_I, ItemID.VAMPYRIC_SLAYER_HELMET_I_25908,
									ItemID.TZKAL_SLAYER_HELMET, ItemID.TZKAL_SLAYER_HELMET_I, ItemID.TZKAL_SLAYER_HELMET_I_25914,
									ItemID.SLAYER_HELMET_I_26674, ItemID.BLACK_SLAYER_HELMET_I_26675, ItemID.GREEN_SLAYER_HELMET_I_26676,
									ItemID.RED_SLAYER_HELMET_I_26677, ItemID.PURPLE_SLAYER_HELMET_I_26678, ItemID.TURQUOISE_SLAYER_HELMET_I_26679,
									ItemID.HYDRA_SLAYER_HELMET_I_26680, ItemID.TWISTED_SLAYER_HELMET_I_26681, ItemID.TZTOK_SLAYER_HELMET_I_26682,
									ItemID.VAMPYRIC_SLAYER_HELMET_I_26683, ItemID.TZKAL_SLAYER_HELMET_I_26684),
					new ItemSlot(ItemID.SHAYZIEN_BODY_5),
					new ItemSlot(ItemID.SHAYZIEN_GREAVES_5),
					new ItemSlot(ItemID.SHAYZIEN_GLOVES_5),
					new ItemSlot(ItemID.SHAYZIEN_BOOTS_5)
			));
		}
		if (myConfig.Justicar()) {
			mySets.add(new EquipmentSet("Set of Justicar Armour", ItemID.JUSTICIAR_FACEGUARD,
					new ItemSlot(ItemID.JUSTICIAR_FACEGUARD),
					new ItemSlot(ItemID.JUSTICIAR_CHESTGUARD),
					new ItemSlot(ItemID.JUSTICIAR_LEGGUARDS)
			));
		}

		if (myConfig.Gauntlet())
		{
			mySets.add(new EquipmentSet("Set of Gauntlet T1 armour", ItemID.CRYSTAL_BODY_BASIC,
					new ItemSlot(ItemID.CRYSTAL_HELM_BASIC),
					new ItemSlot(ItemID.CRYSTAL_BODY_BASIC),
					new ItemSlot(ItemID.CRYSTAL_LEGS_BASIC)
			));
			mySets.add(new EquipmentSet("Set of Corrupted Gauntlet T1 armour", ItemID.CORRUPTED_BODY_BASIC,
					new ItemSlot(ItemID.CORRUPTED_HELM_BASIC),
					new ItemSlot(ItemID.CORRUPTED_BODY_BASIC),
					new ItemSlot(ItemID.CORRUPTED_LEGS_BASIC)
			));

			mySets.add(new EquipmentSet("Set of Gauntlet T2 armour", ItemID.CRYSTAL_BODY_ATTUNED,
					new ItemSlot(ItemID.CRYSTAL_HELM_ATTUNED),
					new ItemSlot(ItemID.CRYSTAL_BODY_ATTUNED),
					new ItemSlot(ItemID.CRYSTAL_LEGS_ATTUNED)
			));
			mySets.add(new EquipmentSet("Set of Corrupted Gauntlet T2 armour", ItemID.CORRUPTED_BODY_ATTUNED,
					new ItemSlot(ItemID.CORRUPTED_HELM_ATTUNED),
					new ItemSlot(ItemID.CORRUPTED_BODY_ATTUNED),
					new ItemSlot(ItemID.CORRUPTED_LEGS_ATTUNED)
			));

			mySets.add(new EquipmentSet("Set of Gauntlet T3 armour", ItemID.CRYSTAL_BODY_PERFECTED,
					new ItemSlot(ItemID.CRYSTAL_HELM_PERFECTED),
					new ItemSlot(ItemID.CRYSTAL_BODY_PERFECTED),
					new ItemSlot(ItemID.CRYSTAL_LEGS_PERFECTED)
			));
			mySets.add(new EquipmentSet("Set of Corrupted Gauntlet T3 armour", ItemID.CORRUPTED_BODY_PERFECTED,
					new ItemSlot(ItemID.CORRUPTED_HELM_PERFECTED),
					new ItemSlot(ItemID.CORRUPTED_BODY_PERFECTED),
					new ItemSlot(ItemID.CORRUPTED_LEGS_PERFECTED)
			));
		}
	}

	void Reset()
	{
		infoBoxes.removeIf(infoBox -> infoBox.getClass() == EquipmentSetInfoBox.class);
	}
}
