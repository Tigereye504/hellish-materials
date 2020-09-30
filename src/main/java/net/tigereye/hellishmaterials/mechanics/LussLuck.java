package net.tigereye.hellishmaterials.mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.hellishmaterials.items.Luckstone;

public class LussLuck {

    public static float RandomFloatWithLuck(PlayerEntity player)
    {
        return RandomFloatWithLuck(player,player.getLuck());
    }
    public static float RandomFloatWithLuck(PlayerEntity player,float luckFactor)
    {
        float n = 0;
        float m = 0;

        //search for a luckstone. If one is found, extract up to two rolls from it
        //don't worry about it running out; it returns 0 if empty
        int luckStonePos = Luckstone.FindLuckstone(player.inventory);
        if(luckStonePos != -1){
            ItemStack luckstone = player.inventory.getStack(luckStonePos);
            n = Luckstone.popRoll(luckstone);
            m = Luckstone.popRoll(luckstone);
        }
        //if the luckstone wasn't there or ran out, roll dice
        if(n == 0){
            n = player.getRandom().nextFloat();
        }
        if(m == 0){
            m = player.getRandom().nextFloat();
        }
        //apply luck formula
        if(luckFactor>0){ //average result is .5+luck*.025
            n = n + ((luckFactor/10)*m*(1-n));
        }
        else if(luckFactor<0){
            n = n + ((luckFactor/10)*m*n);
        }

        return n;
    }

    public static int StackSizeRandomizer(float compFactor, PlayerEntity player){
        return StackSizeRandomizer(compFactor, player, player.getLuck());
    }
    public static int StackSizeRandomizer(float compFactor, PlayerEntity player, float luckFactor)
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

    public static int ToolSingleStackRandomizer(int remainingToolDurability, int dropCount, PlayerEntity player){
        if(remainingToolDurability < 0){
            return StackSizeRandomizer(dropCount,player);
        }
        return StackSizeRandomizer(dropCount,player,player.getLuck()-((float)remainingToolDurability/5)+4);
    }

    public static List<ItemStack> ToolListItemStackRandomizer(List<ItemStack> target, ItemStack tool, PlayerEntity player){
        int remainingDamage;
        int returnDrops;
        List<ItemStack> returnlist = new ArrayList<>();
        for( ItemStack singleStack : target){
            if(tool.isDamageable()){
                remainingDamage = tool.getMaxDamage()-tool.getDamage();
            }
            else{
                remainingDamage = -1;
            }

            returnDrops = LussLuck.ToolSingleStackRandomizer(remainingDamage, singleStack.getCount(), player);
            
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