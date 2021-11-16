package com.flanks255.simplylight.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (Screen.hasShiftDown() && I18n.exists(block.getDescriptionId() + ".info")) {
            tooltip.add(new TranslationTextComponent(block.getDescriptionId() + ".info"));
            if (I18n.exists(block.getDescriptionId()+".info2")) {
                tooltip.add(new TranslationTextComponent(block.getDescriptionId() + ".info2"));
                if (I18n.exists(block.getDescriptionId() + ".info3"))
                    tooltip.add(new TranslationTextComponent(block.getDescriptionId() + ".info3"));
            }
        }
        else {
            tooltip.add(new TranslationTextComponent("simplylight.shift", new TranslationTextComponent("simplylight.key.shift").withStyle(TextFormatting.GOLD, TextFormatting.ITALIC)));
        }
    }
}
