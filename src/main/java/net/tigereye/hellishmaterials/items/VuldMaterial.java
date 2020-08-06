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
        return 21.0f;   // absurdly fast (diamond is 8), 45 is enough to auto break stone (so e5+)
                        // 90 would auto-break ore
    }

    @Override
    public float getAttackDamage() {
        return 14.0f; // staggering damage! Should allow crits to kill zombies
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