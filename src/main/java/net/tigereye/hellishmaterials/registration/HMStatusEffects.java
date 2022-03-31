package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mob_effect.HM_StatusEffect;

public class HMStatusEffects {
    public static StatusEffect BLEEDING = new HM_StatusEffect(StatusEffectCategory.HARMFUL, 0xa1151e);
    public static StatusEffect GUTS = new HM_StatusEffect(StatusEffectCategory.BENEFICIAL, 0xc18e66);

    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "bleeding"), BLEEDING);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "guts"), GUTS);
    }
}
