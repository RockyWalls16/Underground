package com.thva.underground;

import com.thva.underground.misc.UGCreativeTab;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = UnderGround.MODID, name = UnderGround.MODNAME, version = UnderGround.VERSION)
public class UnderGround
{
	public static final String MODID = "undergroundmod";
	public static final String MODNAME = "Underground";
	public static final String VERSION = "0.1A";

	@Instance
	public static UnderGround instance = new UnderGround();

	//Register proxy
	@SidedProxy(clientSide = "com.thva.underground.client.ClientProxy", serverSide = "com.thva.underground.server.ServerProxy")
	public static CommonProxy proxy;

	public static final UGCreativeTab UG_TAB = new UGCreativeTab();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		this.proxy.postInit(event);
	}
}
