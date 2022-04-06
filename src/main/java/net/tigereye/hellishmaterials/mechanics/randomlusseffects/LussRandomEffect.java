package net.tigereye.hellishmaterials.mechanics.randomlusseffects;

import net.minecraft.entity.effect.StatusEffect;

public interface LussRandomEffect {

    enum Quality{EXTREMELY_LUCKY, VERY_LUCKY, LUCKY,UNLUCKY,VERY_UNLUCKY,EXTREMELY_UNLUCKY}
    class Weight{public static final int
            VERY_COMMON = 120, COMMON = 60, UNCOMMON = 30,
            RARE = 15, VERY_RARE = 5;}

    Quality getQuality();
    int getWeight();
}
