package net.tigereye.luss.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class Vuld extends Item{

    public Vuld(Settings settings) {
        super(settings);
    }
    
    //when a player right clicks a block with vuld, it consumes that block
    //and infects the player with a second of wither,
    //stacking with any existing withering
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        Block targetBlock = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        int duration = 20;
        int severity = 0;
        if(!targetBlock.is(Blocks.BEDROCK)){ 
            context.getWorld().breakBlock(context.getBlockPos(), false);
            if(context.getPlayer().hasStatusEffect(StatusEffects.WITHER)){
                duration += context.getPlayer().getStatusEffect(StatusEffects.WITHER).getDuration();
                severity = context.getPlayer().getStatusEffect(StatusEffects.WITHER).getAmplifier();
            }
            context.getPlayer().addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,duration,severity));
            return ActionResult.SUCCESS;
        }
        else{
            return ActionResult.PASS;
        }
    }
}