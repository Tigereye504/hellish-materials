package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak.LussBreakEffect;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack.LussAttackNoEffect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LussBreakEffectManager {

    private static final Multimap<LussRandomEffect.Quality, LussBreakEffect> EFFECTS = ArrayListMultimap.create();
    private static Map<LussRandomEffect.Quality,Integer> WEIGHTS = new HashMap<>();
    private static final LussBreakEffect NO_EFFECT = new LussBreakNoEffect(LussRandomEffect.Quality.UNLUCKY,0);

    public static void registerEffect(LussBreakEffect effect){
        EFFECTS.put(effect.getQuality(),effect);
        WEIGHTS.put(effect.getQuality(),WEIGHTS.getOrDefault(effect.getQuality(),0)+effect.getWeight());
    }

    public static LussBreakEffect getRandomLussBreakEffect(LussRandomEffect.Quality quality, Random random){
        Collection<LussBreakEffect> effects = EFFECTS.get(quality);
        int weight = WEIGHTS.getOrDefault(quality,0);
        if(weight == 0){
            return NO_EFFECT;
        }
        int countdown = random.nextInt(weight);
        for (LussBreakEffect effect:
             effects) {
            countdown -= effect.getWeight();
            if(countdown < 0){
                return effect;
            }
        }
        HellishMaterials.LOGGER.error("Ran out of luss break effects! Something is very wrong.");
        return NO_EFFECT;
    }
}
