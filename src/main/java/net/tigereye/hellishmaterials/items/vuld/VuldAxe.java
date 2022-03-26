package net.tigereye.hellishmaterials.items.vuld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.armor.BaseArmor;
import net.tigereye.hellishmaterials.items.BaseAxe;

public class VuldAxe extends BaseAxe {


    public VuldAxe(ToolMaterial material) {
        super(material);
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
