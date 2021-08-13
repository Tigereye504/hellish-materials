package net.tigereye.hellishmaterials.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import net.tigereye.hellishmaterials.registration.HMItems;

public class LussDust extends Item{

    public LussDust(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        Item targetItem;
        int compFactor;
        BlockState targetBlockState = context.getWorld().getBlockState(context.getBlockPos());
        Block targetBlock = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        
        //wish I could use a switch here...
        //TODO: data driven luss dust
        if(targetBlockState.isOf(Blocks.DIAMOND_BLOCK)){ 
            targetItem = Items.DIAMOND;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.DIAMOND_ORE)){ 
            targetItem = Items.DIAMOND;
            compFactor = 3;
        }
        else if(targetBlockState.isOf(Blocks.EMERALD_BLOCK)){
            targetItem = Items.EMERALD;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.EMERALD_ORE)){ 
            targetItem = Items.EMERALD;
            compFactor = 3;
        }
        else if(targetBlockState.isOf(Blocks.REDSTONE_BLOCK)){
            targetItem = Items.REDSTONE;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.REDSTONE_ORE)){ 
            targetItem = Items.REDSTONE;
            compFactor = 6;
        }
        else if(targetBlockState.isOf(Blocks.LAPIS_BLOCK)){
            targetItem = Items.LAPIS_LAZULI;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.LAPIS_ORE)){ 
            targetItem = Items.LAPIS_LAZULI;
            compFactor = 15;
        }
        else if(targetBlockState.isOf(Blocks.GOLD_BLOCK)){
            targetItem = Items.GOLD_INGOT;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.GOLD_ORE)){ 
            targetItem = Items.RAW_GOLD;
            compFactor = 3;
        }
        else if(targetBlockState.isOf(Blocks.RAW_GOLD_BLOCK)){
            targetItem = Items.RAW_GOLD;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.NETHER_GOLD_ORE)){ 
            targetItem = Items.GOLD_NUGGET;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.IRON_BLOCK)){
            targetItem = Items.IRON_INGOT;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.IRON_ORE)){
            targetItem = Items.RAW_IRON;
            compFactor = 3;
        }
        else if(targetBlockState.isOf(Blocks.RAW_IRON_BLOCK)){
            targetItem = Items.RAW_IRON;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.COPPER_BLOCK)){
            targetItem = Items.COPPER_INGOT;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.COPPER_ORE)){
            targetItem = Items.RAW_COPPER;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.RAW_COPPER_BLOCK)){
            targetItem = Items.RAW_COPPER;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.COAL_BLOCK)){
            targetItem = Items.COAL;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(Blocks.COAL_ORE)){
            targetItem = Items.COAL;
            compFactor = 3;
        }
        else if(targetBlockState.isOf(HMItems.LUSS_BLOCK)){
            targetItem = HMItems.LUSS_INGOT;
            compFactor = 9;
        }
        else if(targetBlockState.isOf(HMItems.LUSS_ORE)){
            targetItem = HMItems.LUSS_DUST;
            compFactor = 4;
        }
        else if(targetBlockState.isOf(Blocks.GLOWSTONE)){
            targetItem = Items.GLOWSTONE_DUST;
            compFactor = 4;
        }
        else{
            return ActionResult.PASS;
        }

        compFactor = LussLuck.StackSizeRandomizer(compFactor, context.getPlayer());

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