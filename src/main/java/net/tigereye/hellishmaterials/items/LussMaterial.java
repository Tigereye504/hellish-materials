package net.tigereye.hellishmaterials.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.tigereye.hellishmaterials.registration.HMItems;

public class LussMaterial implements ToolMaterial{

    @Override
    public int getDurability() {
        return 32; //gold durability
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 12.0f; //gold mining speed
    }

    @Override
    public float getAttackDamage() {
        return 3.0f; //diamond damage
    }

    @Override
    public int getMiningLevel() {
        return 3; //diamond, so you can actually do stuff
    }

    @Override
    public int getEnchantability() {
        return 22; //gold enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(HMItems.LUSS_INGOT);
    }
    
}