package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.damage.DamageSource;

public class HMDamageSource extends DamageSource {
    public static final DamageSource HM_BLOOD_DEBT = new HMDamageSource("hmBloodDebt").setBypassesArmor();

    public HMDamageSource(String name) {
        super(name);
    }

    
}