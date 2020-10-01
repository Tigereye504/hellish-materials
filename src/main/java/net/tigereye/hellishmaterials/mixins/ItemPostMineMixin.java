package net.tigereye.hellishmaterials.mixins;

import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;

@Mixin(Item.class)
public class ItemPostMineMixin {
    
    @Inject(
        at = @At("HEAD"),
        method = "postMine")
        public void HellishMaterialsPostMineMixin(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> info){
            if(stack.getItem().isIn(HMItems.TAG_BATET)){
                BatetDeferment.ForgiveDebts(miner,state.getHardness(world, pos));
            }
        }
}
