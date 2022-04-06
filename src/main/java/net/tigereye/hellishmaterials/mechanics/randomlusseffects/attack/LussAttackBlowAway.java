package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class LussAttackBlowAway implements LussAttackEffect{

    Quality quality;
    int weight;
    public LussAttackBlowAway(Quality quality, int weight){
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
        Vec3d angle = new Vec3d(defender.getX()-attacker.getX(),defender.getY()-attacker.getY(),defender.getZ()-attacker.getZ());
        angle = angle.normalize().add(0,.2,0);
        defender.setVelocity(angle.multiply(4+luck));
    }
}
