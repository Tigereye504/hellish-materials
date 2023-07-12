package net.tigereye.hellishmaterials.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At(value="HEAD"), method = "afterBreak")
    public void HMItemStackAfterBreakMixin(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci){
        if (Utils.isLuss(tool)) {
            LussLuck.tryRandomBreakEffect((Block) (Object) this, world, pos, state, player);
        }
    }
}
