package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

import java.util.HashMap;
import java.util.Map;

public class LussAttackNoEffect implements LussAttackEffect{


    @Override
    public Quality getQuality() {
        return Quality.UNLUCKY;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
    }
}
