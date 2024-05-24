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
		name = "Void Knight",
		description = "Track 'Void Knight'",
		section = trackSection,
		position = 0
	)
	default boolean VoidKnight() { return true; }
	@ConfigItem(
		keyName = "set_obsidian",
		name = "Obsidian",
		description = "Track 'Obsidian'",
		section = trackSection,
		position = 1
	)
	default boolean Obsidian() { return true; }
	@ConfigItem(
			keyName = "set_bloodmoon",
			name = "BloodMoon",
			description = "Track 'BloodMoon'",
			section = trackSection,
			position = 2
	)
	default boolean BloodMoon() { return true; }
	@ConfigItem(
			keyName = "set_bluemoon",
			name = "BlueMoon",
			description = "Track 'BlueMoon'",
			section = trackSection,
			position = 3
	)
	default boolean BlueMoon() { return true; }
	@ConfigItem(
			keyName = "set_eclipsemoon",
			name = "EclipseMoon",
			description = "Track 'EclipseMoon'",
			section = trackSection,
			position = 4
	)
	default boolean EclipseMoon() { return true; }
	@ConfigItem(
			keyName = "set_virtus",
			name = "Virtus",
			description = "Track 'Virtus'",
			section = trackSection,
			position = 5
	)
	default boolean Virtus() { return true; }
	@ConfigItem(
			keyName = "set_swampbark",
			name = "Swampbark",
			description = "Track 'Swampbark'",
			section = trackSection,
			position = 6
	)
	default boolean Swampbark() { return true; }
	@ConfigItem(
			keyName = "set_bloodbark",
			name = "Bloodbark",
			description = "Track 'Bloodbark'",
			section = trackSection,
			position = 7
	)
	default boolean Bloodbark() { return true; }
	@ConfigItem(
			keyName = "set_inquisitor",
			name = "Inquisitor",
			description = "Track 'Inquisitor'",
			section = trackSection,
			position = 8
	)
	default boolean Inquisitor() { return true; }
	@ConfigItem(
			keyName = "set_crystal",
			name = "Crystal",
			description = "Track 'Crystal'",
			section = trackSection,
			position = 9
	)
	default boolean Shayzien() { return true; }
	@ConfigItem(
			keyName = "set_justicar",
			name = "Justicar",
			description = "Track 'Justicar'",
			section = trackSection,
			position = 10
	)
	default boolean Justicar() { return true; }
	@ConfigItem(
			keyName = "set_ahrims",
			name = "Ahrims",
			description = "Track 'Ahrims'",
			section = trackSection,
			position = 11
	)
	default boolean Ahrims() { return true; }
	@ConfigItem(
			keyName = "set_dharoks",
			name = "Dharoks",
			description = "Track 'Dharoks'",
			section = trackSection,
			position = 12
	)
	default boolean Dharoks() { return true; }
	@ConfigItem(
			keyName = "set_guthans",
			name = "Guthans",
			description = "Track 'Guthans'",
			section = trackSection,
			position = 13
	)
	default boolean Guthans() { return true; }
	@ConfigItem(
			keyName = "set_karils",
			name = "Karils",
			description = "Track 'Karils'",
			section = trackSection,
			position = 12
	)
	default boolean Karils() { return true; }
	@ConfigItem(
			keyName = "set_torags",
			name = "Torags",
			description = "Track 'Torags'",
			section = trackSection,
			position = 13
	)
	default boolean Torags() { return true; }
	@ConfigItem(
			keyName = "set_veracs",
			name = "Veracs",
			description = "Track 'Veracs'",
			section = trackSection,
			position = 14
	)
	default boolean Veracs() { return true; }
	@ConfigItem(
			keyName = "set_graceful",
			name = "Graceful",
			description = "Track 'Graceful'",
			section = trackSection,
			position = 15
	)
	default boolean Graceful() { return true; }
	@ConfigItem(
		keyName = "set_farming",
		name = "Farming",
		description = "Track 'Farming'",
		section = trackSection,
		position = 17
	)
	default boolean Farming() { return true; }
	@ConfigItem(
		keyName = "set_rogue",
		name = "Rogue",
		description = "Track 'Rogue'",
		section = trackSection,
		position = 16
	)
	default boolean Rogue() { return true; }
	@ConfigItem(
		keyName = "set_hunter",
		name = "Hunter",
		description = "Track 'Hunter'",
		section = trackSection,
		position = 18
	)
	default boolean Hunter() { return true; }
	@ConfigItem(
		keyName = "set_lumberjack",
		name = "Lumberjack",
		description = "Track 'Lumberjack'",
		section = trackSection,
		position = 19
	)
	default boolean Lumberjack() { return true; }
	@ConfigItem(
		keyName = "set_carpenter",
		name = "Carpenter",
		description = "Track 'Carpenter'",
		section = trackSection,
		position = 20
	)
	default boolean Carpenter() { return true; }
}
