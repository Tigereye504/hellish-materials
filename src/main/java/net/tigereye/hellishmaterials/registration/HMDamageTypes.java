package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;

public class HMDamageTypes {
    public static final RegistryKey<DamageType> BLOOD_DEBT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(HellishMaterials.MODID, "blood_debt"));

    public static final TagKey<DamageType> TAG_PRODUCES_VULD_DROP = TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(HellishMaterials.MODID,"produces_vuld_drop"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
