package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.hellishmaterials.items.luss.Luckstone;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LussLuck {
    private static final float LUCK_EFFECTIVENESS = 2;  //effects how quickly luck causes the average roll to shift
                                                        //if LUCK_EFFECTIVENESS = 1, luck will have no effect at all.
                                                        //bad things could happen if it is set to less than 1

    public static float RandomFloatWithLuck(PlayerEntity player)
    {
        return RandomFloatWithLuck(player,player.getLuck());
    }
    public static float RandomFloatWithLuck(@Nullable PlayerEntity player, float luckFactor)
    {
        Random random;
        float n = 0;
        if(player != null){
            random = player.getRandom();
        }
        else{
            random = new Random();
        }
        //TODO: Add and move to RandomFloatWithLuckSetResult event
        //search for a luckstone. If one is found, extract a roll from it
        if(player != null) {
            int luckStonePos = Luckstone.FindLuckstone(player.getInventory());
            if (luckStonePos != -1) {
                ItemStack luckstone = player.getInventory().getStack(luckStonePos);
                n = Luckstone.popRoll(luckstone);
            }
            //if the luckstone wasn't there or ran out, roll dice
            if(n == 0){
                n = random.nextFloat();
            }
        }
        else{
            n = random.nextFloat();
        }


        //TODO: Add and move to RandomFloatWithLuckModifyResult event
        //apply luck formula
        if(luckFactor>0){ //as luckFactor approaches infinity, the average roll approaches .75 as correctionFactor approaches 1
            float m = random.nextFloat();
            float imperfection = 1-n;
            float correctionFactor = 1-(1/(float)Math.pow(LUCK_EFFECTIVENESS,luckFactor));
            n = n + imperfection*correctionFactor*m;
        }
        else if(luckFactor<0){ //as luckFactor approaches negative infinity, the average roll approaches .25 as correctionFactor approaches 1
            float m = random.nextFloat();
            float correctionFactor = 1-(1/(float)Math.pow(LUCK_EFFECTIVENESS,-luckFactor));
            n = n - n*correctionFactor*m;
        }

        return n;
    }

    public static int StackSizeRandomizer(float compFactor, @Nullable PlayerEntity player){
        float playerLuck = 0;
        if(player != null){
            playerLuck = player.getLuck();
        }
        return StackSizeRandomizer(compFactor, player, playerLuck);
    }
    public static int StackSizeRandomizer(float compFactor, @Nullable PlayerEntity player, float luckFactor)
    {
        Random rand = new Random();
        float n = RandomFloatWithLuck(player,luckFactor);

        if(n>=.99){ //major jackpot
            if(rand.nextInt(100)==99){
                //grand jackpot
                return Math.round(64*compFactor);
            }
            return Math.round(32*compFactor/9);
        }
        if(n>=.97){ //minor jackpot
            return Math.round(24*compFactor/9);
        }
        if(n>=.94){
            return Math.round(16*compFactor/9);
        }
        if(n>=.90){
            return Math.round(15*compFactor/9);
        }
        if(n>=.85){
            return Math.round(14*compFactor/9);
        }
        if(n>=.79){
            return Math.round(13*compFactor/9);
        }
        if(n>=.72){
            return Math.round(12*compFactor/9);
        }
        if(n>=.64){
            return Math.round(11*compFactor/9);
        }
        if(n>=.55){
            return Math.round(10*compFactor/9);
        }
        if(n>=.45){
            return (int)compFactor;
        }
        if(n>=.36){
            return Math.round(8*compFactor/9);
        }
        if(n>=.28){
            return Math.round(7*compFactor/9);
        }
        if(n>=.21){
            return Math.round(6*compFactor/9);
        }
        if(n>=.15){
            return Math.round(5*compFactor/9);
        }
        if(n>=.10){
            return Math.round(4*compFactor/9);
        }
        if(n>=.06){
            return Math.round(3*compFactor/9);
        }
        if(n>=.03){
            return Math.round(2*compFactor/9);
        }
        if(n>=.01){
            return Math.round(1*compFactor/9);
        }
        else{
            return 0;
        }
    }

    public static int ToolSingleStackRandomizer(float percentUsed, int dropCount, @Nullable PlayerEntity player){
        if(percentUsed < 0){
            return StackSizeRandomizer(dropCount,player);
        }
        float playerLuck = 0;
        if(player != null){
            playerLuck = player.getLuck();
        }
        return StackSizeRandomizer(dropCount,player,playerLuck+(percentUsed-.2f));
    }

    public static List<ItemStack> ToolListItemStackRandomizer(List<ItemStack> target, ItemStack tool, @Nullable PlayerEntity player){
        float percentUsed;
        int returnDrops;
        List<ItemStack> returnlist = new ArrayList<>();
        for( ItemStack singleStack : target){
            if(tool.isDamageable() && tool.getMaxDamage() != 0){
                percentUsed = (float)tool.getDamage() / (float)tool.getMaxDamage();
            }
            else{
                percentUsed = -1;
            }

            returnDrops = LussLuck.ToolSingleStackRandomizer(percentUsed, singleStack.getCount(), player);
            
            if (singleStack.isStackable()){
                if(returnDrops > singleStack.getMaxCount()){
                    singleStack.setCount(singleStack.getMaxCount());
                }
                while(returnDrops > singleStack.getMaxCount()){
                    //insert new max size stack to front of list
                    returnlist.add(singleStack.copy());
                    //decrement returnDrops by getMaxCount
                    returnDrops -= singleStack.getMaxCount();
                }
                singleStack.setCount(returnDrops);
                returnlist.add(singleStack.copy());
            }
            else{
                for(int i = 0; i < returnDrops; i++){
                    //load em up
                    returnlist.add(singleStack.copy());
                }
            }
        }
        return returnlist;
    }


}