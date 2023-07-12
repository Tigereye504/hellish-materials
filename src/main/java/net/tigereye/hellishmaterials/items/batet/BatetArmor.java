package net.tigereye.hellishmaterials.items.batet;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BatetArmor extends ArmorItem {

    public BatetArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".batet_armor.tooltip"));
    }
}