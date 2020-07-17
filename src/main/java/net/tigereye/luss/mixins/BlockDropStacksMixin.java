package net.tigereye.luss.mixins;

import net.tigereye.luss.events.BlockDropStacksCallback;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(Block.class)
public class BlockDropStacksMixin {
    
    @Inject(
        at = @At("HEAD"),
        method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
        cancellable = true)
    private static void ChangeDropBlocks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo info){
        if (world instanceof ServerWorld) {
            List<ItemStack> stacksToDrop = Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, stack);
            TypedActionResult<List<ItemStack>> result = BlockDropStacksCallback.EVENT.invoker().editDropStacks(state, world, pos, blockEntity, entity, stack, stacksToDrop);
            stacksToDrop = result.getValue();
            stacksToDrop.forEach((stackToDrop) -> {
                Block.dropStack(world, pos, stackToDrop);
            });
        }
        state.onStacksDropped(world, pos, ItemStack.EMPTY);
        info.cancel();
    }
}
//oh god I hope I don't need this...
//method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)"