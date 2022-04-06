package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.LivingEntity;

public class LussAttackThreeVeryLuckyEffects implements LussAttackEffect{


    @Override
    public Quality getQuality() {
        return Quality.EXTREMELY_LUCKY;
    }

    @Override
    public int getWeight() {
        return 60;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.VERY_LUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.VERY_LUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
        LussAttackEffectManager.getRandomLussAttackEffect(Quality.VERY_LUCKY, attacker.getRandom()).causeEffect(attacker,defender,damage,luck);
    }
}
