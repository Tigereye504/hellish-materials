package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakThreeEffects implements LussBreakEffect {


    Quality quality;
    int weight;
    Quality newQuality;

    public LussBreakThreeEffects(Quality quality, int weight, Quality newQuality){
        this.quality = quality;
        this.weight = weight;
        this.newQuality = newQuality;
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
        LussBreakEffectManager.getRandomLussBreakEffect(newQuality, player.getRandom()).causeEffect(block, world, pos, state, player, luck);
        LussBreakEffectManager.getRandomLussBreakEffect(newQuality, player.getRandom()).causeEffect(block, world, pos, state, player, luck);
        LussBreakEffectManager.getRandomLussBreakEffect(newQuality, player.getRandom()).causeEffect(block, world, pos, state, player, luck);
    }

}
