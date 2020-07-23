package net.tigereye.hellishmaterials.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseSword extends SwordItem {

    public BaseSword(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public BaseSword(ToolMaterial material) {
        super(material, 0, -2.4f, new Item.Settings().group(ItemGroup.TOOLS));
    }
    
}