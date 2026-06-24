package com.ccccc.cuuuusssssseeeeeforge.block;

import com.ccccc.cuuuusssssseeeeeforge.Cuuuusssssseeeeeforge;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final Block CURSEFORGE_ANVIL = new CurseForgeAnvilBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL));
    public static final Block SEVEN_CURSEFORGE = new SevenCurseForgeBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL));

    private static void registerBlock(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Cuuuusssssseeeeeforge.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Cuuuusssssseeeeeforge.MOD_ID, name), new BlockItem(block, new Item.Properties()));
    }

    public static void register() {
        registerBlock("curseforge_anvil", CURSEFORGE_ANVIL);
        registerBlock("seven_curseforge", SEVEN_CURSEFORGE);
    }
}
