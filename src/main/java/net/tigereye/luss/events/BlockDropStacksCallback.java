package net.tigereye.luss.events;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Callback for breaking a block 
 * Called before item drops are calculated
/**/

public interface BlockDropStacksCallback {
    Event<BlockDropStacksCallback> EVENT = EventFactory.createArrayBacked(BlockDropStacksCallback.class,
        (listeners) -> (state, world, pos, blockEntity, entity, stack, stacksToDrop) -> {
            for (BlockDropStacksCallback listener : listeners) {
                TypedActionResult<List<ItemStack>> result = listener.editDropStacks(state, world, pos, blockEntity, entity, stack, stacksToDrop);
                if(result.getResult() != ActionResult.PASS) {
                    return result;
                }
                stacksToDrop = result.getValue();
            }
            return TypedActionResult.pass(stacksToDrop);
    });

    TypedActionResult<List<ItemStack>> editDropStacks(BlockState state, World world, BlockPos pos, /*@Nullable*/ BlockEntity blockEntity, /*@Nullable*/ Entity entity, ItemStack stack, List<ItemStack> stacksToDrop);
}