package com.thva.underground.misc;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class UGConfig
{
	private static Configuration config;
	
	/**
	 * Load configuration
	 * @param configDirectory
	 */
	public static void loadConfig(File configDirectory)
	{
		config = new Configuration(new File(configDirectory, "Underground.cfg"));
	}
	
	/**
	 * Save configuration values
	 */
	public static void saveConfig()
	{
		config.save();
	}
	
	/**
	 * Get the config during preInit event only !
	 * @return configuration
	 */
	public static Configuration getConfig()
	{
		return config;
	}
}
