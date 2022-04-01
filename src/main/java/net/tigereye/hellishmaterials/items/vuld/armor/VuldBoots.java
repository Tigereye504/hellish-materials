package net.tigereye.hellishmaterials.items.vuld.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.include.com.google.common.collect.HashMultimap;

import java.util.UUID;

public class VuldBoots extends VuldArmor {

    protected static EntityAttributeModifier mod = new EntityAttributeModifier(UUID.fromString("6c3f36aa-caed-4ec9-b59f-641ac8a14baa"),
            "HM_Vuld_Boots_Health_Penalty",-.125, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);


    public VuldBoots(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot);
    }

    @Override
    protected EntityAttributeModifier getHealthLoss() {
        return mod;
    }
}
