package net.tigereye.hellishmaterials.items.vuld.armor;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class VuldChestplate extends VuldArmor {

    protected static EntityAttributeModifier mod = new EntityAttributeModifier(UUID.fromString("2ef3fb6f-59bd-41bb-9925-f6a8ba480ed0"),
            "HM_Vuld_Chestplate_Health_Penalty",-.32, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public VuldChestplate(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot);
    }

    @Override
    protected EntityAttributeModifier getHealthLoss() {
        return mod;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".vuld.tooltip"));
    }
}
