package net.tigereye.hellishmaterials.items.vuld.tools;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.items.BaseAxe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VuldAxe extends BaseAxe {


    public VuldAxe(ToolMaterial material) {
        super(material);
    }

    public VuldAxe(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".vuld.tooltip"));
    }
}
