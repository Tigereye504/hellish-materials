package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;

public interface LussBreakEffect extends LussRandomEffect {


    void causeEffect(Block block, World world, BlockPos pos, BlockState state, PlayerEntity player, float luck);

}
