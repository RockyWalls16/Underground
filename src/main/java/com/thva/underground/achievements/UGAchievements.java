package com.thva.underground.achievements;

import java.util.ArrayList;
import java.util.List;

import com.thva.underground.Underground;
import com.thva.underground.blocks.UGBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class UGAchievements
{
	private static AchievementPage underground_achivements;
	private static List<Achievement> achievementList = new ArrayList<Achievement>();
	
	//Achievements
	private static Achievement testAchievement = createAchievement("test", UGBlocks.TEST_BLOCK, null, 0, 0);

	/**
	 * Create new achievement page containing all achievements
	 */
	public static void registerAllAchievements()
	{
		Achievement[] achievements = new Achievement[achievementList.size()];
		achievementList.toArray(achievements);
		
		underground_achivements = new AchievementPage("Underground", achievements);
		AchievementPage.registerAchievementPage(underground_achivements);
	}
	
	/**
	 * Create a new underground achievement
	 * @param name Achievement name
	 * @param icon Achievement icon
	 * @param parent Parent achievement
	 * @param column X pos of achievement
	 * @param row Y pos of achievement
	 * @return new Achievement
	 */
	private static Achievement createAchievement(String name, ItemStack icon, Achievement parent, int column, int row)
	{
		Achievement output = new Achievement(Underground.MODID + ":ach_" + name, "ug_" + name, column, row, icon, parent);
		achievementList.add(output);
		output.registerStat();
		
		return output;
	}
	
	/**
	 * Shortcut for creating achievement with block icon
	 * @param name Achievement name
	 * @param icon Achievement icon
	 * @param parent Parent achievement
	 * @param column X pos of achievement
	 * @param row Y pos of achievement
	 * @return new Achievement
	 */
	private static Achievement createAchievement(String name, Block icon, Achievement parent, int column, int row)
	{
		return createAchievement(name, new ItemStack(icon), parent, column, row);
	}
	
	/**
	 * Shortcut for creating achievement with item icon
	 * @param name Achievement name
	 * @param icon Achievement icon
	 * @param parent Parent achievement
	 * @param column X pos of achievement
	 * @param row Y pos of achievement
	 * @return new Achievement
	 */
	private static Achievement createAchievement(String name, Item icon, Achievement parent, int column, int row)
	{
		return createAchievement(name, new ItemStack(icon), parent, column, row);
	}
}
