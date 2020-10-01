package net.tigereye.hellishmaterials.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

public class DiceItem extends Item {
    public DiceItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.world.isClient()) {
            int die1 = (int) Math.ceil(LussLuck.RandomFloatWithLuck(user) * 8);
            int die2 = (int) Math.ceil(LussLuck.RandomFloatWithLuck(user) * 8);
            int die3 = (int) Math.ceil(LussLuck.RandomFloatWithLuck(user) * 8);
            int die4 = (int) Math.ceil(LussLuck.RandomFloatWithLuck(user) * 8);
            return evaluateHand(world,user,hand,die1,die2,die3,die4);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    protected TypedActionResult<ItemStack> evaluateHand(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4){
        TypedActionResult<ItemStack> result;
        int score = isFourOfAKind(die1, die2, die3, die4);
        if(score != 0){
             result = onFourOfAKind(world,user,hand,die1,die2,die3,die4,score);
             if(result.getResult() != ActionResult.PASS){return result;}
        }
        score = isThreeOfAKind(die1, die2, die3, die4);
        if(score != 0){
            result = onThreeOfAKind(world,user,hand,die1,die2,die3,die4,score);
            if(result.getResult() != ActionResult.PASS){return result;}
        }
        score = isPair(die1, die2, die3, die4);
        if(score != 0){
            int secondPair = isTwoPair(die1,die2,die3,die4,score);
            if(secondPair != 0){
                result = onTwoPair(world,user,hand,die1,die2,die3,die4,score,secondPair);
                if(result.getResult() != ActionResult.PASS){return result;}
            }
            result = onPair(world,user,hand,die1,die2,die3,die4,score);
            if(result.getResult() != ActionResult.PASS){return result;}
        }
        score = isStraight(die1, die2, die3, die4);
        if(score != 0){
            result = onStraight(world,user,hand,die1,die2,die3,die4,score);
            if(result.getResult() != ActionResult.PASS){return result;}
        }
        return onNoScore(world,user,hand,die1,die2,die3,die4);
    }

    protected int isFourOfAKind(int die1, int die2, int die3, int die4){
        if(die1 == die2 && die1 == die3 && die1 == die4){
            return die1;
        }
        return 0;
    }

    protected int isThreeOfAKind(int die1, int die2, int die3, int die4){
        int count = 1; //die1 always matches itself
        if(die1 == die2){count++;}
        if(die1 == die3){count++;}
        if(die1 == die4){count++;}
        if(count == 3){return die1;}
        if(die2 == die3 && die2 == die4){return die2;}
        return 0;
    }

    protected int isTwoPair(int die1, int die2, int die3, int die4, int firstPair){
        if(die1 != firstPair){
            if(die1 == die2 || die1 == die3 || die1 == die4){return die1;}
        }
        if(die2 != firstPair){
            if(die2 == die3 || die2 == die4){return die2;}
        }
        if(die3 != firstPair){
            if(die3 == die4){return die3;}
        }
        return 0;
    }

    protected int isPair(int die1, int die2, int die3, int die4){
        if(die1 == die2 || die1 == die3 || die1 == die4){return die1;}
        if(die2 == die3 || die2 == die4){return die2;}
        if(die3 == die4){return die3;}
        return 0;
    }

    protected int isStraight(int die1, int die2, int die3, int die4){
        //get lowest
        int lowest = die1;
        if(die2 < lowest){lowest = die2;}
        if(die3 < lowest){lowest = die3;}
        if(die4 < lowest){lowest = die4;}
        //check if any equal lowest+1,lowest+2,and lowest+3
        if((die1 == lowest+1 || die2 == lowest+1 || die3 == lowest+1 || die4 == lowest+1)
                && (die1 == lowest+2 || die2 == lowest+2 || die3 == lowest+2 || die4 == lowest+2)
                && (die1 == lowest+3 || die2 == lowest+3 || die3 == lowest+3 || die4 == lowest+3))
        {return lowest;}
        return 0;
    }

    protected TypedActionResult<ItemStack> onFourOfAKind(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int kind){
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    protected TypedActionResult<ItemStack> onThreeOfAKind(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int kind){
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    protected TypedActionResult<ItemStack> onTwoPair(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int pair1, int pair2){
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    protected TypedActionResult<ItemStack> onPair(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int pair){
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    protected TypedActionResult<ItemStack> onStraight(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4, int lowest){
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    protected TypedActionResult<ItemStack> onNoScore(World world, PlayerEntity user, Hand hand, int die1, int die2, int die3, int die4){
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
