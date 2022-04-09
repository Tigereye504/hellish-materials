package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.LivingEntity;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;

public interface LussAttackEffect extends LussRandomEffect {


    void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck);

}
