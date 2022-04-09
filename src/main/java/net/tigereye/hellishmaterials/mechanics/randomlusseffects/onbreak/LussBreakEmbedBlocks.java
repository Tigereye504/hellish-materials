package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

import java.util.Random;

public class LussBreakEmbedBlocks implements LussBreakEffect {


    Quality quality;
    int weight;
    Block block;
    int average;
    int radius;

    public LussBreakEmbedBlocks(Quality quality, int weight, Block block){
        this(quality,weight,block,2);
    }
    public LussBreakEmbedBlocks(Quality quality, int weight, Block block, int average){
        this(quality,weight,block,average,2);
    }
    public LussBreakEmbedBlocks(Quality quality, int weight, Block block, int average, int radius){
        this.quality = quality;
        this.weight = weight;
        this.block = block;
        this.average = average;
        this.radius = radius;
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
        Random rand = world.getRandom();
        int range = (radius*2)+1;
        int attempts = LussLuck.StackSizeRandomizer(average,null,luck);
        BlockPos cornerPos = pos.add(-radius,-radius,-radius);
        for (int i = 0; i < attempts; i++) {
            BlockPos targetPos = cornerPos.add(rand.nextInt(range),rand.nextInt(range),rand.nextInt(range));
            BlockState targetBlockState = world.getBlockState(targetPos);
            if(targetBlockState.isSolidBlock(world,pos)
                    && targetBlockState.getBlock().getBlastResistance() < 1000000
                    && world.canSetBlock(targetPos)){
                world.setBlockState(targetPos,this.block.getDefaultState());
            }
        }
    }

}
