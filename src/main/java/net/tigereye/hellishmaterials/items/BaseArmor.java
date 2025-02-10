package net.tigereye.hellishmaterials.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class BaseArmor extends ArmorItem {

    public BaseArmor(ArmorMaterial material, ArmorItem.Type armorType){
        super(material, armorType, new Item.Settings());
    }
}