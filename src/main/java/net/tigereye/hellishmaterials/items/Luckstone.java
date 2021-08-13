package net.tigereye.hellishmaterials.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.Random;

public class Luckstone extends Item {
    public static final int NUMBER_OF_ROLLS = 4;
    private static final String BANKED_ROLLS_KEY = new Identifier(HellishMaterials.MODID,"banked_rolls").toString();
    public static final String DISPLAY_KEY = new Identifier(HellishMaterials.MODID,"display").toString();

    public Luckstone() {
        super(new Item.Settings().maxCount(1).group(ItemGroup.TOOLS));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!user.world.isClient()) {
            stockFutureRolls(itemStack, user.getRandom());
        }
        return TypedActionResult.success(itemStack);
    }

    public static void stockFutureRolls(ItemStack stack, Random random){
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putInt(BANKED_ROLLS_KEY,NUMBER_OF_ROLLS);
        for(int i = 1; i <= NUMBER_OF_ROLLS; i++){
            if(!tag.contains(BANKED_ROLLS_KEY+i)) {
                tag.putFloat(BANKED_ROLLS_KEY + i, random.nextFloat());
            }
        }
        generateDisplayTier(stack);
        if(HellishMaterials.DEBUG){
            System.out.println("Luckstone Forsaw "+NUMBER_OF_ROLLS+" rolls:");
            for(int i = 1; i <= NUMBER_OF_ROLLS; i++){
                if(tag.contains(BANKED_ROLLS_KEY+i)) {
                    System.out.printf("'%5.4f' ",tag.getFloat(BANKED_ROLLS_KEY+i));
                }
                System.out.print("\n");
            }
        }
    }

    public static int getBankedRollsCount(ItemStack stack){
        NbtCompound tag = stack.getOrCreateNbt();
        if(tag.contains(BANKED_ROLLS_KEY)){
            return tag.getInt(BANKED_ROLLS_KEY);
        }
        return 0;
    }

    public static float popRoll(ItemStack stack){
        //returns 0 if stack is empty
        int count = getBankedRollsCount(stack);
        if(count <= 0){
            return 0;
        }
        NbtCompound tag = stack.getOrCreateNbt();
        if(count == 1){
            tag.putFloat(DISPLAY_KEY,1);
        }
        if(tag.contains(BANKED_ROLLS_KEY+count)){
            float roll = tag.getFloat(BANKED_ROLLS_KEY+count);
            tag.remove(BANKED_ROLLS_KEY+count);
            tag.putInt(BANKED_ROLLS_KEY,count-1);
            return roll;
        }
        else {
            //there wasn't a roll were I expected one. Still, decrement count and act like nothing happened.
            tag.putInt(BANKED_ROLLS_KEY,count-1);
            return 0;
        }
    }

    public static float getAverageRoll(ItemStack stack){
        int count = getBankedRollsCount(stack);
        float total = 0;
        NbtCompound tag = stack.getOrCreateNbt();
        if(count <= 0){
            return 0;
        }
        for(int i = 1; i <= count; i++){
            if(tag.contains(BANKED_ROLLS_KEY+i)) {
                total += tag.getFloat(BANKED_ROLLS_KEY+i);
            }
        }
        if(HellishMaterials.DEBUG){
            System.out.println("Luckstone Total: "+total);
        }
        return total/count;
    }

    private static void generateDisplayTier(ItemStack stack){
        float averageLuck;
        averageLuck = Luckstone.getAverageRoll(stack);
        NbtCompound tag = stack.getOrCreateNbt();
        if(averageLuck > .75f){
            tag.putFloat(DISPLAY_KEY,5);
        }
        else if(averageLuck > .5f){
            tag.putFloat(DISPLAY_KEY,4);
        }
        else if(averageLuck > .25f){
            tag.putFloat(DISPLAY_KEY,3);
        }
        else if(averageLuck > 0){
            tag.putFloat(DISPLAY_KEY,2);
        }
        else{
            tag.putFloat(DISPLAY_KEY,1);//the default look of the luckstone, no little triangle
        }
    }

    public static int FindLuckstone(Inventory inv){
        for(int i = 0; i < inv.size();i++){
            if(inv.getStack(i).getItem() == HMItems.LUCKSTONE){
                return i;
            }
        }
        return -1;
    }
}
