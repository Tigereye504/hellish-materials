package net.tigereye.hellishmaterials.items.vuld.tools;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class VuldMaterial implements ToolMaterial{

    @Override
    public int getDurability() {
        return 6244; // 4*diamond durability
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 40.0f;   // absurdly fast (diamond is 8), 45 is enough to auto break stone, 60 to auto break wood
                        // 90 would auto-break ore
    }

    @Override
    public float getAttackDamage() {
        return 14.0f; // staggering damage! Should allow crits to kill zombies
    }

    @Override
    public int getMiningLevel() {
        return 4;   //diamond+, though most things that tough
                    //you probably don't want to destroy...
    }

    @Override
    public int getEnchantability() {
        return 1; //om nom magic
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);
    }
    
}