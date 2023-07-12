package net.tigereye.hellishmaterials.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(at = @At("TAIL"), method = "updateHealth")
    public void ClientPlayerEntityStopFlinchingFromBloodDebtMixin(float health, CallbackInfo info){
        if(BatetDeferment.findBloodDebtFactor((ClientPlayerEntity) (Object) this) > 0){
            ((ClientPlayerEntity)(Object)this).hurtTime = 0;
        }
    }
}
