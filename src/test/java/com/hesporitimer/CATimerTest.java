package com.hesporitimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CATimerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CATimerPlugin.class);
		RuneLite.main(args);
	}
}