package com.ccccc.cuuuusssssseeeeeforge.block;

import com.ccccc.cuuuusssssseeeeeforge.Cuuuusssssseeeeeforge;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cuuuusssssseeeeeforge.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cuuuusssssseeeeeforge.MOD_ID);

    public static final RegistryObject<Block> CURSEFORGE_ANVIL = registerBlock("curseforge_anvil",
            () -> new CurseForgeAnvilBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));

    public static final RegistryObject<Block> SEVEN_CURSEFORGE = registerBlock("seven_curseforge",
            () -> new SevenCurseForgeBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}
