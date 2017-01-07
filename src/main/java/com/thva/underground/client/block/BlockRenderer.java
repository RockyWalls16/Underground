package com.thva.underground.client.block;

import com.thva.underground.UnderGround;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRenderer
{
	/**
	 * Register render for a BaseBlock
	 * 
	 * @param block
	 *            Block to render
	 */
	public static void registerBlockRender(Block block)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(UnderGround.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
