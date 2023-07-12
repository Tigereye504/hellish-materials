package net.tigereye.hellishmaterials.items.luss;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.datapack.LussDustLootManager;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LussDust extends Item{

    public LussDust(Settings settings) {
        super(settings);
    }

    //private static List<ShapelessRecipe> singleBlockRecipes;

    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        BlockState targetBlockState = context.getWorld().getBlockState(context.getBlockPos());
        Block targetBlock = targetBlockState.getBlock();

        Identifier blockID = Registry.BLOCK.getId(targetBlock);
        Pair<Identifier,Integer> loot = LussDustLootManager.getOutput(blockID,context.getWorld());

        if(loot.getLeft() == null){
            return ActionResult.PASS;
        }

        int count = LussLuck.StackSizeRandomizer(loot.getRight(), context.getPlayer());
        Item item = Registry.ITEM.get(loot.getLeft());
        if(item == Items.AIR){
            HellishMaterials.LOGGER.error("Invalid item returned by luss dust used on "+blockID+".");
            HellishMaterials.LOGGER.error("Please check your datapack for misspellings.");
            return ActionResult.PASS;
        }
        context.getWorld().breakBlock(context.getBlockPos(), false);
        World w = context.getWorld();
        int x = context.getBlockPos().getX();
        int y = context.getBlockPos().getY();
        int z = context.getBlockPos().getZ();
        for(int i = 0; i < count; i++){
            w.spawnEntity(new ItemEntity(w,
            x, y, z, new ItemStack(item, 1)));
        }
        
        context.getStack().decrement(1);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".luss_dust.tooltip"));
    }
}