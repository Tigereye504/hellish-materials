package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakSpawnSlime implements LussBreakEffect {


    Quality quality;
    int weight;
    int size;

    public LussBreakSpawnSlime(Quality quality, int weight, int size) {
        this.quality = quality;
        this.weight = weight;
        this.size = size;
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
        SlimeEntity slime = new SlimeEntity(EntityType.SLIME, world);
        slime.setPos(pos.getX()+.5,pos.getY(),pos.getZ()+.5);
        slime.setSize(size,true);
        world.spawnEntity(slime);
    }

}
