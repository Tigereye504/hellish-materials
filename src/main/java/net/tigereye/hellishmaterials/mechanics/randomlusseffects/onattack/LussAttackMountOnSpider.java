package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SpiderEntity;

public class LussAttackMountOnSpider implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackMountOnSpider(Quality quality, int weight){
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
        SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, defender.world);
        spider.setPos(defender.getX(),defender.getY(),defender.getZ());
        if(attacker != null){
            spider.setAttacker(attacker);
            spider.setTarget(attacker);
        }
        defender.world.spawnEntity(spider);
        defender.startRiding(spider,true);
    }
}
