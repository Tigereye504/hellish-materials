package net.tigereye.luss.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.tigereye.luss.mechanics.VuldCorruption;

public class Vuld extends Item{

    public Vuld(Settings settings) {
        super(settings);
    }
    
    //when a player right clicks a block with vuld, it consumes that block
    //and infects the player with a second of Cumulative Wither
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        Block targetBlock = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        if(!targetBlock.is(Blocks.BEDROCK)){ 
            context.getWorld().breakBlock(context.getBlockPos(), false);
            VuldCorruption.inflictCumulativeWither(context.getPlayer(), 20, 0, 20);
            return ActionResult.SUCCESS;
        }
        else{
            return ActionResult.PASS;
        }
    }
}