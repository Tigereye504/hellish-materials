package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.registration.HM_Items;

@Mixin(LivingEntity.class)
public class LivingEntityApplyDamageMixin {

    @ModifyVariable(at = @At(value = "CONSTANT",ordinal = 2,args = "floatValue=0.0F"), method = "applyDamage")
    public float HellishMaterialsApplyDamageMixin(float amount, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity){
            if(((LivingEntity)(source.getAttacker())).getStackInHand(((LivingEntity)(source.getAttacker())).getActiveHand()).getItem().isIn(HM_Items.TAG_BATET)){
                BatetDeferment.ForgiveDebts((LivingEntity)source.getAttacker(),amount);
            }
        }
		return BatetDeferment.deferDamage(((LivingEntity)(Object)this), source, amount);
    }
    
}