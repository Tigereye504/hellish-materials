package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.interfaces.HM_PlayerEntity;
import net.tigereye.hellishmaterials.items.luss.Luckstone;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack.LussAttackEffect;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack.LussAttackEffectManager;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak.LussBreakEffect;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak.LussBreakEffectManager;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class LussLuck {
    private static final float LUCK_EFFECTIVENESS = 2;  //effects how quickly luck causes the average roll to shift
                                                        //if LUCK_EFFECTIVENESS = 1, luck will have no effect at all.
                                                        //bad things could happen if it is set to less than 1

    private static final float EXTREMELY_LUCKY_ROLL = .99f;     //.99 to 1,    1.0% chance per event
    private static final float VERY_LUCKY_ROLL = .92f;          //.92 to .99,  7.0% chance per event
    private static final float LUCKY_ROLL = .40f;               //.40 to .92, 52.0% chance per event
    private static final float UNLUCKY_ROLL = .05f;             //.05 to .40, 35.0% chance per event
    private static final float VERY_UNLUCKY_ROLL = .005f;       //.005to .05,  4.5% chance per event
    //private static final float EXTREMELY_UNLUCKY_ROLL = 0f;   //.0  to .005,  .5% chance per event
    private static final int AVERAGE_ATTACKS_FOR_LUSS_EVENT = 2;
    private static final int AVERAGE_WOUNDS_FOR_LUSS_EVENT = 8;
    private static final int AVERAGE_BREAKS_FOR_LUSS_EVENT = 12;

    public static final Map<Integer, Item> scatterGoldLootTable = new HashMap<Integer,Item>();

    static{
        scatterGoldLootTable.put(0, Items.GOLDEN_HOE);
        scatterGoldLootTable.put(1, Items.GOLDEN_AXE);
        scatterGoldLootTable.put(2, Items.GOLDEN_PICKAXE);
        scatterGoldLootTable.put(3, Items.GOLDEN_SHOVEL);
        scatterGoldLootTable.put(4, Items.GOLDEN_SWORD);
        scatterGoldLootTable.put(5, Items.GOLDEN_HELMET);
        scatterGoldLootTable.put(6, Items.GOLDEN_CHESTPLATE);
        scatterGoldLootTable.put(7, Items.GOLDEN_LEGGINGS);
        scatterGoldLootTable.put(8, Items.GOLDEN_BOOTS);
        scatterGoldLootTable.put(9, Items.NETHER_GOLD_ORE);
        scatterGoldLootTable.put(10, Items.DEEPSLATE_GOLD_ORE);
        scatterGoldLootTable.put(11, Items.GOLD_ORE);
        scatterGoldLootTable.put(12, Items.RAW_GOLD);
        scatterGoldLootTable.put(13, Items.RAW_GOLD);
        scatterGoldLootTable.put(14, Items.RAW_GOLD);
        scatterGoldLootTable.put(15, Items.RAW_GOLD);
        scatterGoldLootTable.put(16, Items.GOLD_INGOT);
        scatterGoldLootTable.put(17, Items.GOLD_INGOT);
        scatterGoldLootTable.put(18, Items.GOLD_INGOT);
        scatterGoldLootTable.put(19, Items.GOLD_INGOT);
    }

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

            if(!singleStack.isIn(HMItems.TAG_LUSS_BLACKLIST)) {
                returnDrops = LussLuck.ToolSingleStackRandomizer(percentUsed, singleStack.getCount(), player);
            }
            else{
                returnDrops = singleStack.getCount();
            }
            
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


    public static void tryRandomAttackEffect(LivingEntity attacker, @NotNull LivingEntity defender, float damage) {
        float luck = 0;
        if(attacker instanceof PlayerEntity) {
            luck = ((PlayerEntity) attacker).getLuck() - (8*(1-((HM_PlayerEntity)attacker).getLastAttackCooldownProgressResult()));
        }
        else {
            if(attacker.hasStatusEffect(StatusEffects.LUCK)){
                luck = attacker.getStatusEffect(StatusEffects.LUCK).getAmplifier() + 1;
            }
            if(attacker.hasStatusEffect(StatusEffects.UNLUCK)){
                luck -= attacker.getStatusEffect(StatusEffects.UNLUCK).getAmplifier() + 1;
            }
        }
        tryRandomAttackEffect(attacker,defender,damage,luck);
    }

    public static void tryRandomAttackEffect(LivingEntity attacker, @NotNull LivingEntity defender, float damage, float luck) {
        Random random = attacker.getRandom();
        if(random.nextInt(AVERAGE_ATTACKS_FOR_LUSS_EVENT) != 0){
            return;
        }

        float roll;
        LussAttackEffect effect;
        if(attacker instanceof PlayerEntity) {
            roll = RandomFloatWithLuck((PlayerEntity) attacker);
        }
        else {
            roll = RandomFloatWithLuck(null,luck);
        }
        if(roll > UNLUCKY_ROLL && roll < LUCKY_ROLL){
            return;
        }
        if(roll >= EXTREMELY_LUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.EXTREMELY_LUCKY,random);
        else if(roll >= VERY_LUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.VERY_LUCKY,random);
        else if(roll >= LUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.LUCKY,random);
        else if(roll >= UNLUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.UNLUCKY,random);
        else if(roll >= VERY_UNLUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.VERY_UNLUCKY,random);
        else //if(roll >= EXTREMELY_UNLUCKY_ROLL)
            effect = LussAttackEffectManager.getRandomLussAttackEffect(LussRandomEffect.Quality.EXTREMELY_UNLUCKY,random);
        effect.causeEffect(attacker,defender,damage,luck);
    }

    public static void tryRandomBreakEffect(Block block, World world, BlockPos pos, BlockState state, PlayerEntity player) {
        tryRandomBreakEffect(block,world,pos,state,player, player.getLuck());
    }
    public static void tryRandomBreakEffect(Block block, World world, BlockPos pos, BlockState state, PlayerEntity player, float luck) {
        Random random = player.getRandom();
        if(random.nextInt(AVERAGE_BREAKS_FOR_LUSS_EVENT) != 0){
            return;
        }

        float roll;
        LussBreakEffect effect;roll = RandomFloatWithLuck((PlayerEntity) player);
        if(roll >= EXTREMELY_LUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.EXTREMELY_LUCKY,random);
        else if(roll >= VERY_LUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.VERY_LUCKY,random);
        else if(roll >= LUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.LUCKY,random);
        else if(roll >= UNLUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.UNLUCKY,random);
        else if(roll >= VERY_UNLUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.VERY_UNLUCKY,random);
        else //if(roll >= EXTREMELY_UNLUCKY_ROLL)
            effect = LussBreakEffectManager.getRandomLussBreakEffect(LussRandomEffect.Quality.EXTREMELY_UNLUCKY,random);        
        effect.causeEffect(block,world,pos,state,player,luck);
    }

}