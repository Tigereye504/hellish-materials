package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tigereye.hellishmaterials.blocks.entity.VaporousVuldBlockEntity;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.jetbrains.annotations.Nullable;

public class VaporousVuld extends BlockWithEntity implements BlockEntityProvider {

    public VaporousVuld(){
        super(FabricBlockSettings.of(Material.AIR)
                .air()
                .noCollision()
                .nonOpaque());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VaporousVuldBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, HMItems.VAPOROUS_VULD_BLOCK_ENTITY, VaporousVuldBlockEntity::tick);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockEntity be = world.getBlockEntity(pos);
        if(be instanceof VaporousVuldBlockEntity){
            ((VaporousVuldBlockEntity)be).clearDecayResistance(direction);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

}
