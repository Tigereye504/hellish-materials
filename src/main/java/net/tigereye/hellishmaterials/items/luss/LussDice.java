package net.tigereye.hellishmaterials.items.luss;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.items.DiceItem;

public class LussDice extends DiceItem {

    public LussDice() {
        super(new Settings().maxCount(1).group(ItemGroup.TOOLS));
    }
    
    //luss dice generate 4 luck-dependant values from 1 to 8, and displays the result in chat.
    //they are just a sink for bad luck or for boredom, and a crafting material
    @Override
    protected TypedActionResult<ItemStack> onFourOfAKind(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int kind){
        String out = "Got Four of a Kind!! " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    protected TypedActionResult<ItemStack> onThreeOfAKind(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int kind){
        String out = "Rolled Three of a Kind! " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    protected TypedActionResult<ItemStack> onTwoPair(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int pair1, int pair2){
        String out = "Rolled Two Pair! " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    protected TypedActionResult<ItemStack> onPair(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int pair){
        String out = "Rolled a Pair of "+pair+"s. " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    protected TypedActionResult<ItemStack> onStraight(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int lowest){
        String out = "Rolled a Straight! " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    protected TypedActionResult<ItemStack> onNoScore(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4){
        String out = "Rolled " + die1 +
                ", " + die2 +
                ", " + die3 +
                ", and " + die4 + ".";
        user.sendMessage(new LiteralText(out), true);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}