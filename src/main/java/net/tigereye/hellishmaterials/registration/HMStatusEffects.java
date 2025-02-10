package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mob_effect.HM_StatusEffect;

public class HMStatusEffects {
    public static StatusEffect BLEEDING = new HM_StatusEffect(StatusEffectCategory.HARMFUL, 0xa1151e);
    public static StatusEffect GUTS = new HM_StatusEffect(StatusEffectCategory.BENEFICIAL, 0xc18e66);

    public static void register(){
        Registry.register(Registries.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "bleeding"), BLEEDING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "guts"), GUTS);
    }
}
