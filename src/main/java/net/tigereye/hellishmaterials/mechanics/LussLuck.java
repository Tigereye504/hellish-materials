package net.tigereye.hellishmaterials.mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

public class LussLuck {
    
    public static int Randomizer(float compFactor, float luckFactor)
    {
        Random rand = new Random();
        int n = 0;
        n = rand.nextInt(100); //0-99
        //apply luck, increasing or decreasing the roll by up to luck*10% of
        //the difference between the roll and 99 or 0
        //bound luck within [10,-10], as any more would lead to jackpots being very common.
        if(luckFactor>0){
        n = n + (int)(Math.min(luckFactor,10)*rand.nextInt(100-n))/10;
        }
        else if(luckFactor<0){
            n = n + (int)(Math.max(luckFactor,-10)*rand.nextInt(n+1))/10;
        }

        if(n>=99){ //major jackpot
            if(rand.nextInt(100)==99){
                //grand jackpot
                return (int)(64*compFactor);
            }
            return Math.round(32*compFactor/9);
        }
        if(n>=97){ //minor jackpot
            return Math.round(24*compFactor/9);
        }
        if(n>=94){
            return Math.round(16*compFactor/9);
        }
        if(n>=90){
            return Math.round(15*compFactor/9);
        }
        if(n>=85){
            return Math.round(14*compFactor/9);
        }
        if(n>=79){
            return Math.round(13*compFactor/9);
        }
        if(n>=72){
            return Math.round(12*compFactor/9);
        }
        if(n>=64){
            return Math.round(11*compFactor/9);
        }
        if(n>=55){
            return Math.round(10*compFactor/9);
        }
        if(n>=45){
            return (int)compFactor;
        }
        if(n>=36){
            return Math.round(8*compFactor/9);
        }
        if(n>=28){
            return Math.round(7*compFactor/9);
        }
        if(n>=21){
            return Math.round(6*compFactor/9);
        }
        if(n>=15){
            return Math.round(5*compFactor/9);
        }
        if(n>=10){
            return Math.round(4*compFactor/9);
        }
        if(n>=6){
            return Math.round(3*compFactor/9);
        }
        if(n>=3){
            return Math.round(2*compFactor/9);
        }
        if(n>=1){
            return Math.round(1*compFactor/9);
        }
        else{
            return 0;
        }
    }

    public static int ToolSingleStackRandomizer(int remainingToolDurability, int dropCount, float luckFactor){
        if(remainingToolDurability < 0){
            return Randomizer(dropCount,luckFactor);
        }
        return Randomizer(dropCount,luckFactor-((float)remainingToolDurability/5)+4);
    }

    public static List<ItemStack> ToolListItemStackRandomizer(List<ItemStack> target, ItemStack tool, float luckFactor){
        int remainingDamage = 0;
        int returnDrops = 0;
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