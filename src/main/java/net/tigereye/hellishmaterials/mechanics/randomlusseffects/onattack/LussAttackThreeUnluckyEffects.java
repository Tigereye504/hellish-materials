package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.LivingEntity;

public class LussAttackThreeUnluckyEffects implements LussAttackEffect{



    Quality quality;
    int weight;

    public LussAttackThreeUnluckyEffects(Quality quality, int weight){
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
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.UNLUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.UNLUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.UNLUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
    }
}
