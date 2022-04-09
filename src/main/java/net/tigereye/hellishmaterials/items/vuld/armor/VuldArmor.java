package net.tigereye.hellishmaterials.items.vuld.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.BaseArmor;

public abstract class VuldArmor extends BaseArmor {


    public VuldArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot);
    }
    /*
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(stack.hasEnchantments()){
            stack.removeSubNbt("Enchantments");
            stack.removeSubNbt("StoredEnchantments");
        }
    }
    */

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> oldMultiMap = super.getAttributeModifiers(slot);
        if (slot == getSlotType()) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(oldMultiMap).put(EntityAttributes.GENERIC_MAX_HEALTH,getHealthLoss());
            Multimap<EntityAttribute, EntityAttributeModifier> ret = builder.build();
            return ret;
        }
        return oldMultiMap;
    }

    abstract protected EntityAttributeModifier getHealthLoss();
}
