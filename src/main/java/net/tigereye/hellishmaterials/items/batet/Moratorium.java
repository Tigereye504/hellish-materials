package net.tigereye.hellishmaterials.items.batet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;

import java.util.function.Consumer;

public class Moratorium extends Item{
    private static final int DURABILITY = 256;
    private static final float DEBT_FACTOR = 1.25f;

    public Moratorium() {
        super(new Item.Settings().maxCount(1).group(ItemGroup.TOOLS).maxDamage(DURABILITY));
    }
    
    //when a player uses Moratorium, they convert all damage they are suffering to blooddebt
    //if they are at full health, their absorption is instead filled to match their health - not any more it doesn't
    //again at cost of blooddebt
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        float healing;
        if(user.getHealth() < user.getMaxHealth()){
            healing = user.getMaxHealth() - user.getHealth();
            user.heal(healing);
            BatetDeferment.addBloodDebt((BloodDebtTracker) user,healing*DEBT_FACTOR);
            user.getItemCooldownManager().set(this, 400);
            itemStack.damage((int) healing, user, (Consumer<LivingEntity>) ((p) -> p.sendToolBreakStatus(user.getActiveHand())));
        }
        return TypedActionResult.success(itemStack);
    }
}