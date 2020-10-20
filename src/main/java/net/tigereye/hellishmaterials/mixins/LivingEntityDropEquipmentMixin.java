package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;

@Mixin(LivingEntity.class)
public class LivingEntityDropEquipmentMixin {
    int HM_BloodDebtTimer = 0;

    @Inject(
        at = @At("HEAD"),
        method = "dropEquipment")
    private void HellishMaterialsDropEquipmentMixin(CallbackInfo info){
        BatetDeferment.forgiveDebts((LivingEntity)(Object)this);
    }
}