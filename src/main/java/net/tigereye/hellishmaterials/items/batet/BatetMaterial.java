package net.tigereye.hellishmaterials.items.batet;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.tigereye.hellishmaterials.registration.HMItems;

public class BatetMaterial implements ToolMaterial{

    @Override
    public int getDurability() {
        return 250; //iron durability
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0f; //iron mining speed
    }

    @Override
    public float getAttackDamage() {
        return 5.0f; //iron damage
    }

    @Override
    public int getMiningLevel() {
        return 2; //iron tier
    }

    @Override
    public int getEnchantability() {
        return 14; //iron enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(HMItems.BATET_GEM);
    }
    
}