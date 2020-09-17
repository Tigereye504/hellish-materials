package net.tigereye.hellishmaterials.mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

public class LussLuck {

    public static float RandomFloatWithLuck(float luckFactor)
    {
        Random rand = new Random();
        return RandomFloatWithLuck(luckFactor, rand);
    }

    public static float RandomFloatWithLuck(float luckFactor, Random rand)
    {
        float n = rand.nextFloat();
        //apply luck, increasing or decreasing the roll by up to luck*10% of
        //the difference between the roll and 99 or 0
        if(luckFactor>0){ //average result is .5+luck*.025
            n = n + ((luckFactor/10)*rand.nextFloat()*(1-n));
        }
        else if(luckFactor<0){
            n = n + ((luckFactor/10)*rand.nextFloat()*n);
        }
        return n;
    }

    public static int StackSizeRandomizer(float compFactor, float luckFactor)
    {
        Random rand = new Random();
        float n = RandomFloatWithLuck(luckFactor, rand);

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

    public static int ToolSingleStackRandomizer(int remainingToolDurability, int dropCount, float luckFactor){
        if(remainingToolDurability < 0){
            return StackSizeRandomizer(dropCount,luckFactor);
        }
        return StackSizeRandomizer(dropCount,luckFactor-((float)remainingToolDurability/5)+4);
    }

    public static List<ItemStack> ToolListItemStackRandomizer(List<ItemStack> target, ItemStack tool, float luckFactor){
        int remainingDamage;
        int returnDrops;
        List<ItemStack> returnlist = new ArrayList<ItemStack>();
        for( ItemStack singleStack : target){
            if(tool.isDamageable()){
                remainingDamage = tool.getMaxDamage()-tool.getDamage();
            }
            else{
                remainingDamage = -1;
            }

            returnDrops = LussLuck.ToolSingleStackRandomizer(remainingDamage, singleStack.getCount(), luckFactor);
            
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