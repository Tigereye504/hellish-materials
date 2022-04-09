package net.tigereye.hellishmaterials.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BasePickaxe extends PickaxeItem {

    public BasePickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public BasePickaxe(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }

    public BasePickaxe(ToolMaterial material) {
        super(material, -2, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
    }
    
}