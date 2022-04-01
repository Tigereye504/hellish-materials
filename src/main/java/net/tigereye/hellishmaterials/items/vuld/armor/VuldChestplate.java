package net.tigereye.hellishmaterials.items.vuld.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.vuld.armor.VuldArmor;

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
}
