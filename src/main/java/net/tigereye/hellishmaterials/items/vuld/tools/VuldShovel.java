package net.tigereye.hellishmaterials.items.vuld.tools;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.BaseAxe;
import net.tigereye.hellishmaterials.items.BaseShovel;

public class VuldShovel extends BaseShovel {


    public VuldShovel(ToolMaterial material) {
        super(material);
    }

    public VuldShovel(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }

}
