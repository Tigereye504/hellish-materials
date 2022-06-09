package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.util.math.random.Random;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LussAttackEffectManager {

    private static final Multimap<LussRandomEffect.Quality,LussAttackEffect> ATTACK_EFFECTS = ArrayListMultimap.create();
    private static Map<LussRandomEffect.Quality,Integer> WEIGHTS = new HashMap<>();
    private static final LussAttackEffect NO_EFFECT = new LussAttackNoEffect(LussRandomEffect.Quality.UNLUCKY,0);

    public static void registerEffect(LussAttackEffect effect){
        ATTACK_EFFECTS.put(effect.getQuality(),effect);
        WEIGHTS.put(effect.getQuality(),WEIGHTS.getOrDefault(effect.getQuality(),0)+effect.getWeight());
    }

    public static LussAttackEffect getRandomLussAttackEffect(LussRandomEffect.Quality quality, Random random){
        Collection<LussAttackEffect> effects = ATTACK_EFFECTS.get(quality);
        int weight = WEIGHTS.getOrDefault(quality,0);
        if(weight == 0){
            return NO_EFFECT;
        }
        int countdown = random.nextInt(weight);
        for (LussAttackEffect effect:
             effects) {
            countdown -= effect.getWeight();
            if(countdown < 0){
                return effect;
            }
        }
        HellishMaterials.LOGGER.error("Ran out of luss attack effects! Something is very wrong.");
        return NO_EFFECT;
    }
}
