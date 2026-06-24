package com.ccccc.cuuuusssssseeeeeforge.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;

public class SevenCurseForgeBlock extends AnvilBlock {
    public SevenCurseForgeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);

            if (!heldItem.isEmpty()) {
                String modId = net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(heldItem.getItem()).getNamespace();
                String targetUrl = null;

                if ("minecraft".equals(modId)) {
                    targetUrl = "https://curseforge.com";
                } else {
                    java.util.Optional<? extends net.minecraftforge.fml.ModContainer> container = net.minecraftforge.fml.ModList.get().getModContainerById(modId);
                    if (container.isPresent()) {
                        java.util.Optional<java.net.URL> modUrl = container.get().getModInfo().getModURL();
                        if (modUrl.isPresent()) {
                            targetUrl = modUrl.get().toString();
                        }
                    }
                }

                if (targetUrl != null && !targetUrl.isEmpty() && !targetUrl.contains("change.me")) {
                    

                    int lastSlash = targetUrl.lastIndexOf('/');
                    String slug = (lastSlash != -1 && lastSlash < targetUrl.length() - 1) 
                            ? targetUrl.substring(lastSlash + 1) 
                            : modId; 

                    if (slug.contains("?")) {
                        slug = slug.split("\\?")[0];
                    }

                    String correctUrl = "https://www.curseforge.com/minecraft/mc-mods/" + slug;
                    net.minecraft.Util.getPlatform().openUri(correctUrl);
                    
                } 
                else {
                    String fallbackUrl = "https://www.curseforge.com/minecraft/mc-mods/" + modId;
                    net.minecraft.Util.getPlatform().openUri(fallbackUrl);
                }

            } else {
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("調べたいModのアイテムを手に持って右クリックしてください"));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }







    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }
}
