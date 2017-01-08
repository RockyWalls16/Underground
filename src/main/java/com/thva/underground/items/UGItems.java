package com.thva.underground.items;

import java.util.ArrayList;

import com.thva.underground.Underground;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UGItems
{
	// List which contains all ug items
	public static final ArrayList<Item> ITEM_LIST = new ArrayList<Item>();

	public static final BaseItem ITEM_TEST = (BaseItem) new BaseItem().setUnlocalizedName("item_test").setCreativeTab(Underground.UG_TAB);
	
	public static final ItemWarp WARPER = (ItemWarp) new ItemWarp().setUnlocalizedName("warper").setCreativeTab(Underground.UG_TAB);

	/**
	 * Used to register all items
	 */
	public static void registerAllItems()
	{
		registerBaseItem(ITEM_TEST);
		registerBaseItem(WARPER);
	}

	/**
	 * Register an item into game registry
	 * 
	 * @param item
	 *            BaseItem to register
	 */
	private static void registerBaseItem(Item item)
	{
		ITEM_LIST.add(item);

		GameRegistry.register(item, new ResourceLocation(Underground.MODID, item.getUnlocalizedName().substring(5)));
	}
}
