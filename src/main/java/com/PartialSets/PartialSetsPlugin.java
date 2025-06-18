package com.PartialSets;

import com.PartialSets.overlay.EquipmentSetInfoBox;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.callback.ClientThread;
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
	@Inject
	private ClientThread myClientThread;

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

		Reset();
		myClientThread.invokeLater(() ->
		{
			Setup();
			Check(client.getItemContainer(InventoryID.EQUIPMENT.getId()));
		});
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
			switch (set.Check(aEquipment, myItemManager))
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
		mySets = Sets.All(this, myConfig);
	}

	void Reset()
	{
		infoBoxes.removeIf(infoBox -> infoBox.getClass() == EquipmentSetInfoBox.class);
	}
}
