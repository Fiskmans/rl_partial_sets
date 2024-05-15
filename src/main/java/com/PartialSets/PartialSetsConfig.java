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
		description = "What color use for text",
		section = appearance
	)
	default Color TextColor() { return Color.RED; }


	@ConfigItem(
		keyName = "set_void_knight",
		name = "Void Knight",
		description = "Track 'Void Knight'",
		section = trackSection
	)
	default boolean VoidKnight() { return true; }
	@ConfigItem(
		keyName = "set_obsidian",
		name = "Obsidian",
		description = "Track 'Obsidian'",
		section = trackSection
	)
	default boolean Obsidian() { return true; }
	@ConfigItem(
		keyName = "set_farming",
		name = "Farming",
		description = "Track 'Farming'",
		section = trackSection
	)
	default boolean Farming() { return true; }
	@ConfigItem(
		keyName = "set_rogue",
		name = "Rogue",
		description = "Track 'Rogue'",
		section = trackSection
	)
	default boolean Rogue() { return true; }
	@ConfigItem(
		keyName = "set_hunter",
		name = "Hunter",
		description = "Track 'Hunter'",
		section = trackSection
	)
	default boolean Hunter() { return true; }
	@ConfigItem(
		keyName = "set_lumberjack",
		name = "Lumberjack",
		description = "Track 'Lumberjack'",
		section = trackSection
	)
	default boolean Lumberjack() { return true; }
	@ConfigItem(
		keyName = "set_carpenter",
		name = "Carpenter",
		description = "Track 'Carpenter'",
		section = trackSection
	)
	default boolean Carpenter() { return true; }
	@ConfigItem(
		keyName = "set_bloodmoon",
		name = "BloodMoon",
		description = "Track 'BloodMoon'",
		section = trackSection
	)
	default boolean BloodMoon() { return true; }
	@ConfigItem(
		keyName = "set_bluemoon",
		name = "BlueMoon",
		description = "Track 'BlueMoon'",
		section = trackSection
	)
	default boolean BlueMoon() { return true; }
	@ConfigItem(
		keyName = "set_eclipsemoon",
		name = "EclipseMoon",
		description = "Track 'EclipseMoon'",
		section = trackSection
	)
	default boolean EclipseMoon() { return true; }
	@ConfigItem(
		keyName = "set_virtus",
		name = "Virtus",
		description = "Track 'Virtus'",
		section = trackSection
	)
	default boolean Virtus() { return true; }
	@ConfigItem(
		keyName = "set_swampbark",
		name = "Swampbark",
		description = "Track 'Swampbark'",
		section = trackSection
	)
	default boolean Swampbark() { return true; }
	@ConfigItem(
		keyName = "set_bloodbark",
		name = "Bloodbark",
		description = "Track 'Bloodbark'",
		section = trackSection
	)
	default boolean Bloodbark() { return true; }
	@ConfigItem(
		keyName = "set_inquisitor",
		name = "Inquisitor",
		description = "Track 'Inquisitor'",
		section = trackSection
	)
	default boolean Inquisitor() { return true; }
	@ConfigItem(
		keyName = "set_ahrims",
		name = "Ahrims",
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
		name = "Guthans",
		description = "Track 'Guthans'",
		section = trackSection
	)
	default boolean Guthans() { return true; }
	@ConfigItem(
		keyName = "set_karils",
		name = "Karils",
		description = "Track 'Karils'",
		section = trackSection
	)
	default boolean Karils() { return true; }
	@ConfigItem(
		keyName = "set_torags",
		name = "Torags",
		description = "Track 'Torags'",
		section = trackSection
	)
	default boolean Torags() { return true; }
	@ConfigItem(
		keyName = "set_veracs",
		name = "Veracs",
		description = "Track 'Veracs'",
		section = trackSection
	)
	default boolean Veracs() { return true; }
}
