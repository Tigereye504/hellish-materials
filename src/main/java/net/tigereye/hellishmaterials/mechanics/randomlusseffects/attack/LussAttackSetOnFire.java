package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.LivingEntity;

public class LussAttackSetOnFire implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackSetOnFire(Quality quality, int weight){
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
        defender.setOnFireFor((int)(60+(luck*30)));
    }
}
