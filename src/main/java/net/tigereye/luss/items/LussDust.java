package net.tigereye.luss.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tigereye.luss.LussGamblersOre;
import net.tigereye.luss.mechanics.LussLuck;

public class LussDust extends Item{

    public LussDust(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        Item targetItem = Items.DIRT;
        int compFactor = 0;
        Block targetBlock = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        
        //wish I could use a switch here...
        if(targetBlock.is(Blocks.DIAMOND_BLOCK)){ 
            targetItem = Items.DIAMOND;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.DIAMOND_ORE)){ 
            targetItem = Items.DIAMOND;
            compFactor = 3;
        }
        else if(targetBlock.is(Blocks.EMERALD_BLOCK)){
            targetItem = Items.EMERALD;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.EMERALD_ORE)){ 
            targetItem = Items.EMERALD;
            compFactor = 3;
        }
        else if(targetBlock.is(Blocks.REDSTONE_BLOCK)){
            targetItem = Items.REDSTONE;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.REDSTONE_ORE)){ 
            targetItem = Items.REDSTONE;
            compFactor = 6;
        }
        else if(targetBlock.is(Blocks.LAPIS_BLOCK)){
            targetItem = Items.LAPIS_LAZULI;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.LAPIS_ORE)){ 
            targetItem = Items.LAPIS_LAZULI;
            compFactor = 15;
        }
        else if(targetBlock.is(Blocks.GOLD_BLOCK)){
            targetItem = Items.GOLD_INGOT;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.GOLD_ORE)){ 
            targetItem = Items.GOLD_INGOT;
            compFactor = 3;
        }
        else if(targetBlock.is(Blocks.NETHER_GOLD_ORE)){ 
            targetItem = Items.GOLD_INGOT;
            compFactor = 3;
        }
        else if(targetBlock.is(Blocks.IRON_BLOCK)){
            targetItem = Items.IRON_INGOT;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.IRON_ORE)){
            targetItem = Items.IRON_INGOT;
            compFactor = 3;
        }
        else if(targetBlock.is(Blocks.COAL_BLOCK)){
            targetItem = Items.COAL;
            compFactor = 9;
        }
        else if(targetBlock.is(Blocks.COAL_ORE)){
            targetItem = Items.COAL;
            compFactor = 3;
        }
        else if(targetBlock.is(LussGamblersOre.LUSS_BLOCK)){
            targetItem = LussGamblersOre.LUSS_INGOT;
            compFactor = 9;
        }
        else if(targetBlock.is(LussGamblersOre.LUSS_ORE)){
            targetItem = LussGamblersOre.LUSS_DUST;
            compFactor = 4;
        }
        else if(targetBlock.is(Blocks.GLOWSTONE)){
            targetItem = Items.GLOWSTONE_DUST;
            compFactor = 4;
        }
        else{
            return ActionResult.PASS;
        }

        compFactor = LussLuck.Randomizer(compFactor, context.getPlayer().getLuck());

        context.getWorld().breakBlock(context.getBlockPos(), false);
        World w = context.getWorld();
        int x = context.getBlockPos().getX();
        int y = context.getBlockPos().getY();
        int z = context.getBlockPos().getZ();
        for(int i = 0; i < compFactor; i++){
            w.spawnEntity(new ItemEntity(w,
            x, y, z, new ItemStack(targetItem, 1)));
        }
        
        context.getStack().decrement(1);
        return ActionResult.SUCCESS;
    }
}