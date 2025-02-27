package com.flanks255.simplylight.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BaseBlockItem extends BlockItem {
    public BaseBlockItem(Block blockIn, Item.Properties builder)
    {
        super(blockIn, builder);
        this.block = blockIn;
    }

    private final Block block;

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType == EquipmentSlot.HEAD || super.canEquip(stack, armorType, entity);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        boolean redstoneFlag = block instanceof LampBlock;
        if (Screen.hasShiftDown() && I18n.exists(block.getDescriptionId() + ".info")) {
            tooltip.add(new TranslatableComponent(block.getDescriptionId() + ".info").withStyle(ChatFormatting.GRAY));
            if (I18n.exists(block.getDescriptionId()+".info2")) {
                if (redstoneFlag)
                    tooltip.add(new TranslatableComponent(block.getDescriptionId() + ".info2", new TranslatableComponent("simplylight.redstone").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.GRAY));
                else
                    tooltip.add(new TranslatableComponent(block.getDescriptionId() + ".info2").withStyle(ChatFormatting.GRAY));
            }
        }
        else {
            tooltip.add(new TranslatableComponent("simplylight.shift", new TranslatableComponent("simplylight.key.shift").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC)).withStyle(ChatFormatting.GRAY));
        }
    }
}
