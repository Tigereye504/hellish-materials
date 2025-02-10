package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakExplode implements LussBreakEffect {


    Quality quality;
    int weight;
    float power;
    boolean fiery;

    public LussBreakExplode(Quality quality, int weight, float power, boolean fiery){
        this.quality = quality;
        this.weight = weight;
        this.power = power;
        this.fiery = fiery;
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
        //TODO: this may cause exploding dice to break blocks.
        world.createExplosion(null,pos.getX()+.5, pos.getY()+.5,pos.getZ()+.5,power,fiery, World.ExplosionSourceType.NONE);
    }

}
