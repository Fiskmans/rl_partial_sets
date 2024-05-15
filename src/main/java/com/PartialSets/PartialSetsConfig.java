package com.PartialSets;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup("partial_sets")
public interface PartialSetsConfig extends Config
{
	@ConfigSection(
			name = "Appearance",
			description = "How to show things",
			position = 0
	)
	String appearance = "appearance";
	@ConfigSection(
			name = "Tracking",
			description = "What to track",
			position = 1,
			closedByDefault = true
	)
	String trackSection = "trackSection";

	@ConfigItem(
		keyName = "appearance_text_color",
		name = "Text Color",
		description = "What color to use for text",
		section = appearance
	)
	default Color TextColor() { return Color.RED; }


	@ConfigItem(
		keyName = "set_void_knight",
		name = "Combat: Void Knight",
		description = "Track 'Void Knight'",
		section = trackSection
	)
	default boolean VoidKnight() { return true; }
	@ConfigItem(
		keyName = "set_obsidian",
		name = "Combat: Obsidian",
		description = "Track 'Obsidian'",
		section = trackSection
	)
	default boolean Obsidian() { return true; }
	@ConfigItem(
		keyName = "set_farming",
		name = "Skilling: Farming",
		description = "Track 'Farming'",
		section = trackSection
	)
	default boolean Farming() { return true; }
	@ConfigItem(
		keyName = "set_rogue",
		name = "Skilling: Rogue",
		description = "Track 'Rogue'",
		section = trackSection
	)
	default boolean Rogue() { return true; }
	@ConfigItem(
		keyName = "set_hunter",
		name = "Skilling: Hunter",
		description = "Track 'Hunter'",
		section = trackSection
	)
	default boolean Hunter() { return true; }
	@ConfigItem(
		keyName = "set_lumberjack",
		name = "Skilling: Lumberjack",
		description = "Track 'Lumberjack'",
		section = trackSection
	)
	default boolean Lumberjack() { return true; }
	@ConfigItem(
		keyName = "set_carpenter",
		name = "Skilling: Carpenter",
		description = "Track 'Carpenter'",
		section = trackSection
	)
	default boolean Carpenter() { return true; }
	@ConfigItem(
		keyName = "set_bloodmoon",
		name = "Moons: BloodMoon",
		description = "Track 'BloodMoon'",
		section = trackSection
	)
	default boolean BloodMoon() { return true; }
	@ConfigItem(
		keyName = "set_bluemoon",
		name = "Moons: BlueMoon",
		description = "Track 'BlueMoon'",
		section = trackSection
	)
	default boolean BlueMoon() { return true; }
	@ConfigItem(
		keyName = "set_eclipsemoon",
		name = "Moons: EclipseMoon",
		description = "Track 'EclipseMoon'",
		section = trackSection
	)
	default boolean EclipseMoon() { return true; }
	@ConfigItem(
		keyName = "set_virtus",
		name = "Combat: Virtus",
		description = "Track 'Virtus'",
		section = trackSection
	)
	default boolean Virtus() { return true; }
	@ConfigItem(
		keyName = "set_swampbark",
		name = "Combat: Swampbark",
		description = "Track 'Swampbark'",
		section = trackSection
	)
	default boolean Swampbark() { return true; }
	@ConfigItem(
		keyName = "set_bloodbark",
		name = "Combat: Bloodbark",
		description = "Track 'Bloodbark'",
		section = trackSection
	)
	default boolean Bloodbark() { return true; }
	@ConfigItem(
		keyName = "set_inquisitor",
		name = "Combat: Inquisitor",
		description = "Track 'Inquisitor'",
		section = trackSection
	)
	default boolean Inquisitor() { return true; }
	@ConfigItem(
		keyName = "set_ahrims",
		name = "Barrows: Ahrims",
		description = "Track 'Ahrims'",
		section = trackSection
	)
	default boolean Ahrims() { return true; }
	@ConfigItem(
		keyName = "set_dharoks",
		name = "Dharoks",
		description = "Track 'Dharoks'",
		section = trackSection
	)
	default boolean Dharoks() { return true; }
	@ConfigItem(
		keyName = "set_guthans",
		name = "Barrows: Guthans",
		description = "Track 'Guthans'",
		section = trackSection
	)
	default boolean Guthans() { return true; }
	@ConfigItem(
		keyName = "set_karils",
		name = "Barrows: Karils",
		description = "Track 'Karils'",
		section = trackSection
	)
	default boolean Karils() { return true; }
	@ConfigItem(
		keyName = "set_torags",
		name = "Barrows: Torags",
		description = "Track 'Torags'",
		section = trackSection
	)
	default boolean Torags() { return true; }
	@ConfigItem(
		keyName = "set_veracs",
		name = "Barrows: Veracs",
		description = "Track 'Veracs'",
		section = trackSection
	)
	default boolean Veracs() { return true; }
	@ConfigItem(
		keyName = "set_graceful",
		name = "Misc: Graceful",
		description = "Track 'Graceful'",
		section = trackSection
	)
	default boolean Graceful() { return true; }
}
