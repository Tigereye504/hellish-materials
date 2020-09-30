package net.tigereye.hellishmaterials.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.mechanics.VuldCorruption;

import java.util.function.Consumer;

public class Moratorium extends Item{
    private static final int DURABILITY = 256;
    private static final float DEBT_FACTOR = 1.25f;

    public Moratorium() {
        super(new Item.Settings().maxCount(1).group(ItemGroup.TOOLS).maxDamage(DURABILITY));
    }
    
    //when a player uses Moratorium, they convert all damage they are suffering to blooddebt
    //if they are at full health, their absorption is instead filled to match their health
    //again at cost of blooddebt
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        float healing = 0;
        if(user.getHealth() < user.getMaxHealth()){
            healing = user.getMaxHealth() - user.getHealth();
            user.heal(healing);
        }
        else if(user.getAbsorptionAmount() < user.getMaxHealth()){
            healing = user.getMaxHealth() - user.getAbsorptionAmount();
            user.setAbsorptionAmount(user.getMaxHealth());
        }
        if(healing > 0) {
            BatetDeferment.deferDamage(user,healing*DEBT_FACTOR,1);
            itemStack.damage((int) healing, user, (Consumer<LivingEntity>) ((p) -> {
                p.sendToolBreakStatus(user.getActiveHand());
            }));
        }
        return TypedActionResult.success(itemStack);
    }
}