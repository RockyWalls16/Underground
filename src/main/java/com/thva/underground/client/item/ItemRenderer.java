package com.thva.underground.client.item;

import com.thva.underground.UnderGround;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRenderer
{
	/**
	 * Register render for a BaseItem
	 * 
	 * @param item
	 *            Item to render
	 */
	public static void registerItemRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(UnderGround.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
