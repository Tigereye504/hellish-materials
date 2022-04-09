package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

public class LussBreakScatterGold implements LussBreakEffect {


    Quality quality;
    int weight;

    public LussBreakScatterGold(Quality quality, int weight){
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
        int drops = LussLuck.StackSizeRandomizer(block.getHardness()*block.getHardness()/25,null,luck);
        for(int i = 0; i < drops; i++){
            int rand = world.getRandom().nextInt(200);
            Item item = LussLuck.scatterGoldLootTable.getOrDefault(rand, Items.GOLD_NUGGET);
            ItemEntity entity = new ItemEntity(world,
                    pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5, new ItemStack(item, 1));
            double angle = 2*Math.PI*i/drops;
            entity.setVelocity(Math.sin(angle)*.2,.8,Math.cos(angle)*.2);
            world.spawnEntity(entity);
        }
    }

}
