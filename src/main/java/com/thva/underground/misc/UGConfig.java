package com.thva.underground.misc;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class UGConfig
{
	private static Configuration config;
	
	public static void loadConfig(File configDirectory)
	{
		config = new Configuration(new File(configDirectory, "Underground.cfg"));
	}
	
	public static void saveConfig()
	{
		config.save();
	}
	
	public static Configuration getConfig()
	{
		return config;
	}
}
