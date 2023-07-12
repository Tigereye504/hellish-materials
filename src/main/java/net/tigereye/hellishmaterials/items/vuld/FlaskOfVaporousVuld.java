package net.tigereye.hellishmaterials.items.vuld;

import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.entity.FlaskOfVaporousVuldEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlaskOfVaporousVuld extends Item {
    public FlaskOfVaporousVuld(Settings settings){
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            FlaskOfVaporousVuldEntity flaskEntity = new FlaskOfVaporousVuldEntity(world, user);
            flaskEntity.setItem(itemStack);
            flaskEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0F, 0.5F, 1.0F);
            world.spawnEntity(flaskEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    public static class DispenserBehaviour extends ProjectileDispenserBehavior{

        @Override
        protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
            return new FlaskOfVaporousVuldEntity(world, position.getX(),position.getY(),position.getZ());
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack,world,tooltip,context);
        tooltip.add(Text.translatable("item." + HellishMaterials.MODID + ".flask_of_vaporous_vuld.tooltip"));
    }
}
