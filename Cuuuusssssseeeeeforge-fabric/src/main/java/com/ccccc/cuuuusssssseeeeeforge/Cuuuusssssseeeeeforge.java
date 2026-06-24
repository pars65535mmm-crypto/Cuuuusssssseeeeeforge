package com.ccccc.cuuuusssssseeeeeforge;

import com.ccccc.cuuuusssssseeeeeforge.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cuuuusssssseeeeeforge implements ModInitializer {
    public static final String MOD_ID = "cuuuusssssseeeeeforge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        LOGGER.info("Cuuuusssssseeeeeforge initialized!");
    }
}
