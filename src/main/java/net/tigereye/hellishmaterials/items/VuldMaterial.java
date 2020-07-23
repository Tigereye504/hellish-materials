package net.tigereye.hellishmaterials.items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class VuldMaterial implements ToolMaterial{

    @Override
    public int getDurability() {
        return 17171; // 11*diamond durability
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 46.0f; // absurdly fast, auto-breaks stone before enchantment
    }

    @Override
    public float getAttackDamage() {
        return 19.0f; // !!! damage
    }

    @Override
    public int getMiningLevel() {
        return 4;   //diamond+, though most things that tough
                    //you problably dont wanna destroy...
    }

    @Override
    public int getEnchantability() {
        return 1; //om nom magic, recommended to enchant before smithing
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);
    }
    
}