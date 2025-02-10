package net.tigereye.hellishmaterials.items.vuld.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.EnumMap;
import java.util.Map;

public class VuldArmorMaterial implements ArmorMaterial {

    private static final Map<ArmorItem.Type, Integer> DURABILITY = new EnumMap<>(ArmorItem.Type.class);
    private static final Map<ArmorItem.Type, Integer> PROTECTION = new EnumMap<>(ArmorItem.Type.class);

    static{
        PROTECTION.put(ArmorItem.Type.BOOTS, 6);
        PROTECTION.put(ArmorItem.Type.LEGGINGS, 12);
        PROTECTION.put(ArmorItem.Type.CHESTPLATE, 16);
        PROTECTION.put(ArmorItem.Type.HELMET, 6);
        DURABILITY.put(ArmorItem.Type.BOOTS, 1300);
        DURABILITY.put(ArmorItem.Type.LEGGINGS, 1500);
        DURABILITY.put(ArmorItem.Type.CHESTPLATE, 1600);
        DURABILITY.put(ArmorItem.Type.HELMET, 1100);
    }
    @Override
    public int getDurability(ArmorItem.Type slot) {
        return DURABILITY.getOrDefault(slot,1000);
    }

    @Override
    public int getProtection(ArmorItem.Type slot) {
        return PROTECTION.getOrDefault(slot,10);
    }

    @Override
    public int getEnchantability() {
        return -10;
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