package net.tigereye.hellishmaterials.items.vuld.tools;

import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.BaseAxe;

public class VuldAxe extends BaseAxe {


    public VuldAxe(ToolMaterial material) {
        super(material);
    }

    public VuldAxe(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
