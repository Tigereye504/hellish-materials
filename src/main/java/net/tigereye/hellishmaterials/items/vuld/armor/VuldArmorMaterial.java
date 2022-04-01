package net.tigereye.hellishmaterials.items.vuld.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class VuldArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 6, 12, 16, 6 };

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()]*100;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNTS[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);
    }

    @Override
    public String getName() {
        return "vuld";
    }

    @Override
    public float getToughness() {
        return 4;
    }

    @Override
    public float getKnockbackResistance() {
        return .3f;
    }
}