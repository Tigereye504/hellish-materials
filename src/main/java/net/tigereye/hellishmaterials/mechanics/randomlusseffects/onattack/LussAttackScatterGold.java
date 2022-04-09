package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

import java.util.HashMap;
import java.util.Map;

public class LussAttackScatterGold implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackScatterGold(Quality quality, int weight){
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
        int drops = LussLuck.StackSizeRandomizer(damage*damage,null,luck);
        World w = defender.getWorld();
        double x = defender.getX();
        double y = defender.getBodyY(.5);
        double z = defender.getZ();
        for(int i = 0; i < drops; i++){
            int rand = w.getRandom().nextInt(200);
            Item item = LussLuck.scatterGoldLootTable.getOrDefault(rand,Items.GOLD_NUGGET);
            ItemEntity entity = new ItemEntity(w,
                    x, y, z, new ItemStack(item, 1));
            double angle = 2*Math.PI*i/drops;
            entity.setVelocity(Math.sin(angle)*.2,.8,Math.cos(angle)*.2);
            w.spawnEntity(entity);
        }
    }
}
