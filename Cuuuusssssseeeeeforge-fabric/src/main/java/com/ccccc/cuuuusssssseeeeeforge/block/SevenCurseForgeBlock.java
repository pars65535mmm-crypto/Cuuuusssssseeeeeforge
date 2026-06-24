package com.ccccc.cuuuusssssseeeeeforge.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
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
                String modId = BuiltInRegistries.ITEM.getKey(heldItem.getItem()).getNamespace();
                String targetUrl = null;

                if ("minecraft".equals(modId)) {
                    targetUrl = "https://curseforge.com";
                } else {
                    java.util.Optional<net.fabricmc.loader.api.ModContainer> container = net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer(modId);
                    if (container.isPresent()) {
                        net.fabricmc.loader.api.metadata.ModMetadata metadata = container.get().getMetadata();
                        String homepage = metadata.getContact().get("homepage").orElse("");
                        if (!homepage.isEmpty()) {
                            targetUrl = homepage;
                        } else {
                            String sources = metadata.getContact().get("sources").orElse("");
                            if (!sources.isEmpty()) {
                                targetUrl = sources;
                            }
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
                    Util.getPlatform().openUri(correctUrl);
                } 
                else {
                    String fallbackUrl = "https://www.curseforge.com/minecraft/mc-mods/" + modId;
                    Util.getPlatform().openUri(fallbackUrl);
                }

            } else {
                player.sendSystemMessage(Component.literal("調べたいModのアイテムを手に持って右クリックしてください"));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }
}
