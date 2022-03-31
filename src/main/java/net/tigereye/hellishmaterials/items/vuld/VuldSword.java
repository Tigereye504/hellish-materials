package net.tigereye.hellishmaterials.items.vuld;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.BaseSword;

public class VuldSword extends BaseSword {


    public VuldSword(ToolMaterial material) {
        super(material);
    }

    public VuldSword(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(ItemGroup.TOOLS));
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(stack.hasEnchantments()){
            stack.removeSubNbt("Enchantments");
            stack.removeSubNbt("StoredEnchantments");
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
