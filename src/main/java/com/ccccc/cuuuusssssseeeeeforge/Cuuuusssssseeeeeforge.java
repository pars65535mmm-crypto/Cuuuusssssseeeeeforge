package com.ccccc.cuuuusssssseeeeeforge;

import com.ccccc.cuuuusssssseeeeeforge.block.ModBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Cuuuusssssseeeeeforge.MOD_ID)
public class Cuuuusssssseeeeeforge {
    public static final String MOD_ID = "cuuuusssssseeeeeforge";

    public Cuuuusssssseeeeeforge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
