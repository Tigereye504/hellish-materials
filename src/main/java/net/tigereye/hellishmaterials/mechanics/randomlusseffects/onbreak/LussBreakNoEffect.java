package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;

public class LussBreakNoEffect implements LussBreakEffect {


    Quality quality;
    int weight;

    public LussBreakNoEffect(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
    }

    @Override
    public Quality getQuality() {
        return this.quality;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void causeEffect(Block block, World world, BlockPos pos, BlockState state, PlayerEntity player, float luck)
    {

    }

}
