package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakSpawnMob implements LussBreakEffect {


    Quality quality;
    int weight;
    EntityType<? extends MobEntity> entityType;
    boolean isBaby;


    public LussBreakSpawnMob(Quality quality, int weight, EntityType<? extends MobEntity> entityType) {
        this(quality, weight, entityType,false);
    }
    public LussBreakSpawnMob(Quality quality, int weight, EntityType<? extends MobEntity> entityType, boolean isBaby) {
        this.quality = quality;
        this.weight = weight;
        this.entityType = entityType;
        this.isBaby = isBaby;
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
        if(world instanceof ServerWorld) {
            MobEntity entity = entityType.spawn((ServerWorld)world, null, null, player, pos, SpawnReason.TRIGGERED, true, false);
            entity.setBaby(isBaby);
        }
    }

}
