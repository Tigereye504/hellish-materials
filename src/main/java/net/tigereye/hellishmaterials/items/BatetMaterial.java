package net.tigereye.hellishmaterials.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.tigereye.hellishmaterials.registration.HM_Items;

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
        return 3; //diamond, so you can actually do stuff
    }

    @Override
    public int getEnchantability() {
        return 14; //iron enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(HM_Items.BATET_GEM);
    }
    
}