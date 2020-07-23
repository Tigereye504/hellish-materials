package net.tigereye.hellishmaterials.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class BaseAxe extends AxeItem {

    public BaseAxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public BaseAxe(ToolMaterial material) {
        super(material, 3f, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }
    
}