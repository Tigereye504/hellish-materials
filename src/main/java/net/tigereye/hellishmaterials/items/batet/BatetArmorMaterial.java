package net.tigereye.hellishmaterials.items.batet;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.EnumMap;
import java.util.Map;

public class BatetArmorMaterial implements ArmorMaterial {


    private static final Map<ArmorItem.Type, Integer> DURABILITY = new EnumMap<>(ArmorItem.Type.class);
    private static final Map<ArmorItem.Type, Integer> PROTECTION = new EnumMap<>(ArmorItem.Type.class);

    static{
        PROTECTION.put(ArmorItem.Type.BOOTS, 2);
        PROTECTION.put(ArmorItem.Type.LEGGINGS, 5);
        PROTECTION.put(ArmorItem.Type.CHESTPLATE, 6);
        PROTECTION.put(ArmorItem.Type.HELMET, 2);
        DURABILITY.put(ArmorItem.Type.BOOTS, 429);
        DURABILITY.put(ArmorItem.Type.LEGGINGS, 495);
        DURABILITY.put(ArmorItem.Type.CHESTPLATE, 528);
        DURABILITY.put(ArmorItem.Type.HELMET, 363);
    }

    @Override
    public int getDurability(ArmorItem.Type slot) {
        return DURABILITY.getOrDefault(slot,330);
    }

    @Override
    public int getProtection(ArmorItem.Type slot) {
        return PROTECTION.getOrDefault(slot,4);
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(HMItems.BATET_GEM);
    }

    @Override
    public String getName() {
        return "batet";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}