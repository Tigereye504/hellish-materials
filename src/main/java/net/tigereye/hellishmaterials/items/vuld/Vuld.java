package net.tigereye.hellishmaterials.items.vuld;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mechanics.VuldCorruption;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Vuld extends Item{
    private static int MAX_BLAST_RES = 10000;
    public Vuld(Settings settings) {
        super(settings);
    }
    
    //when a player right clicks a block with vuld, it consumes that block
    //and infects the player with a second of Cumulative Wither
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        Block targetBlock = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        if(targetBlock.getBlastResistance() <= MAX_BLAST_RES){
            context.getWorld().breakBlock(context.getBlockPos(), false);
            PlayerEntity player = context.getPlayer();
            if(player != null){
                VuldCorruption.inflictCumulativeWither(player, 20, 0, 20);
            }
            return ActionResult.SUCCESS;
        }
        else{
            return ActionResult.PASS;
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".vuld.tooltip"));
    }
}