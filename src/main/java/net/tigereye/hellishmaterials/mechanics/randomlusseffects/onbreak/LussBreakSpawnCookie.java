package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LussBreakSpawnCookie implements LussBreakEffect {


    Quality quality;
    int weight;

    public LussBreakSpawnCookie(Quality quality, int weight){
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
        ItemEntity entity = new ItemEntity(world,
                pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5,
                new ItemStack(Items.COOKIE, 1));
        world.spawnEntity(entity);
    }

}
