package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.damage.DamageSource;

public class HM_DamageSource extends DamageSource {
    public static final DamageSource HM_BLOOD_DEBT = new HM_DamageSource("hmBloodDebt");

    public HM_DamageSource(String name) {
        super(name);
    }

    
}