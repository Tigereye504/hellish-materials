package net.tigereye.hellishmaterials.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemPostMineMixin {
    
    @Inject(
        at = @At("HEAD"),
        method = "postMine")
        public void HellishMaterialsPostMineMixin(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> info){
            if(Utils.isBatet(stack)){
                BatetDeferment.forgiveDebts(((BloodDebtTracker)miner),state.getHardness(world, pos));
            }
        }
}
