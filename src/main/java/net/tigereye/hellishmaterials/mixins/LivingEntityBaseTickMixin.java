package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.tigereye.hellishmaterials.mechanics.VuldCorruption;

@Mixin(LivingEntity.class)
public class LivingEntityBaseTickMixin {
    //int HM_BloodDebtTimer = 0;

    @Inject(
        at = @At("HEAD"),
        method = "baseTick")
    private void HellishMaterialsBaseTickMixin(CallbackInfo info){
        //int vuldPoisoning = VuldCorruption.countVuldArmor(((LivingEntity) (Object) this));
        //if(vuldPoisoning > 2){
        //    VuldCorruption.inflictCumulativeWither(((LivingEntity) (Object) this), 2, 1, 20);
        //}
        //else if(vuldPoisoning > 0){
        //    VuldCorruption.inflictCumulativeWither(((LivingEntity) (Object) this), 2, 0, 20);
        //}
    }
}