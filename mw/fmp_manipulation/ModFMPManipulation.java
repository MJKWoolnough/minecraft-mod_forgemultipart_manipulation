package mw.fmp_manipulation;

import codechicken.multipart.handler.MultipartProxy;
import mw.editor.EditorPacketHandler;
import mw.library.BlockManipulator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="mw.fmp_manipulation", name="ModFMPManipulation", version="1.0.0", dependencies = "required-after:MWLibrary;required-after:ForgeMultipart")
//@NetworkMod(clientSideRequired=false, serverSideRequired=true)
public class ModFMPManipulation {
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent postInitEvent) {
		BlockManipulator.registerManipulator(MultipartProxy.block(), new ForgeMultiPartManipulator());
	}
}
