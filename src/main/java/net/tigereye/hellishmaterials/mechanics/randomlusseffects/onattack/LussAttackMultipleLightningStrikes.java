package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;

public class LussAttackMultipleLightningStrikes implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackMultipleLightningStrikes(Quality quality, int weight){
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, defender.world);
                lightning.setPos(defender.getX()-1+i,defender.getY(),defender.getZ()-1+j);
                defender.world.spawnEntity(lightning);
            }
        }
    }
}
