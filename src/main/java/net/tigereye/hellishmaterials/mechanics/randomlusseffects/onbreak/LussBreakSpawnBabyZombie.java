package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakSpawnBabyZombie implements LussBreakEffect {


    Quality quality;
    int weight;

    public LussBreakSpawnBabyZombie(Quality quality, int weight) {
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
        ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, world);
        zombie.setPos(pos.getX()+.5,pos.getY(),pos.getZ()+.5);
        zombie.setBaby(true);
        world.spawnEntity(zombie);
    }

}
