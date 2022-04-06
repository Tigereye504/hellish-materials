package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class LussAttackStatusEffect implements LussAttackEffect{

    Quality quality;
    StatusEffect effect;
    int weight;
    int duration;
    int amplification;

    public LussAttackStatusEffect(Quality quality, int weight, StatusEffect effect, int duration, int amplification){
        this.quality = quality;
        this.effect = effect;
        this.duration = duration;
        this.weight = weight;
        this.amplification = amplification;
    }

    @Override
    public Quality getQuality() {
        return quality;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        defender.addStatusEffect(new StatusEffectInstance(effect,duration, amplification),attacker);
    }
}
