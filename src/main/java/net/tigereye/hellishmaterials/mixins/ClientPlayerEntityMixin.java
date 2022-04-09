package net.tigereye.hellishmaterials.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(at = @At("TAIL"), method = "updateHealth")
    public void ClientPlayerEntityStopFlinchingFromBloodDebtMixin(float health, CallbackInfo info){
        //if(((BloodDebtTracker)(Object)this).isBloodDebtTick() &&
                //((ClientPlayerEntity)(Object)this).hurtTime == ((ClientPlayerEntity)(Object)this).maxHurtTime){
            ((ClientPlayerEntity)(Object)this).hurtTime = 0;
        //}
    }
}
