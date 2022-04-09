package net.tigereye.hellishmaterials.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BaseShovel extends ShovelItem {

    public BaseShovel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public BaseShovel(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }

    public BaseShovel(ToolMaterial material) {
        super(material, -1.5f, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }
    
}