package net.tigereye.hellishmaterials.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class BaseHoe extends HoeItem {

    public BaseHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public BaseHoe(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }

    public BaseHoe(ToolMaterial material) {
        super(material, -3, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }
    
}