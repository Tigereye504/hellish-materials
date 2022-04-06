package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class LussAttackOneSecondStun implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackOneSecondStun(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
    }
    @Override
    public Quality getQuality() {
        return this.quality;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,4),attacker);
        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,4),attacker);
        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,20,4),attacker);
        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,20,4),attacker);
    }
}
