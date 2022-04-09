package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;

public class LussAttackLightningStrike implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackLightningStrike(Quality quality, int weight){
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
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, defender.world);
        lightning.setPos(defender.getX(),defender.getY(),defender.getZ());
        defender.world.spawnEntity(lightning);
    }
}
