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

public class VuldLeggings extends VuldArmor {

    protected static EntityAttributeModifier mod = new EntityAttributeModifier(UUID.fromString("57d9f22b-cba2-47d9-9289-ac4c44753ad9"),
            "HM_Vuld_Helmet_Health_Penalty",-.24, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public VuldLeggings(ArmorMaterial material, EquipmentSlot slot) {
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
