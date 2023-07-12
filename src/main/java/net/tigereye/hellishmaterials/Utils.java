package net.tigereye.hellishmaterials;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.random.Random;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.items.batet.BatetArmorMaterial;
import net.tigereye.hellishmaterials.items.batet.BatetMaterial;
import net.tigereye.hellishmaterials.items.luss.LussMaterial;
import net.tigereye.hellishmaterials.items.vuld.armor.VuldArmorMaterial;
import net.tigereye.hellishmaterials.items.vuld.tools.VuldMaterial;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import net.tigereye.hellishmaterials.registration.HMItems;
import net.tigereye.hellishmaterials.registration.HMStatusEffects;

public class Utils {

    private Utils() {}

    public static boolean isVuld(ItemStack tool) {
        if (tool == null) {
            return false;
        }
        boolean isValidMaterial = false;
        Item item = tool.getItem();
        if (item instanceof ToolItem) {
            isValidMaterial = ((ToolItem) item).getMaterial() instanceof VuldMaterial;
        } else if (item instanceof ArmorItem) {
            isValidMaterial = ((ArmorItem) item).getMaterial() instanceof VuldArmorMaterial;
        }
        return tool.isIn(HMItems.TAG_VULD) || isValidMaterial;
    }

    public static boolean isBatet(ItemStack tool) {
        if (tool == null) {
            return false;
        }
        boolean isValidMaterial = false;
        Item item = tool.getItem();
        if (item instanceof ToolItem) {
            isValidMaterial = ((ToolItem) item).getMaterial() instanceof BatetMaterial;
        } else if (item instanceof ArmorItem) {
            isValidMaterial = ((ArmorItem) item).getMaterial() instanceof BatetArmorMaterial;
        }
        return tool.isIn(HMItems.TAG_BATET) || isValidMaterial;
    }

    public static boolean isLuss(ItemStack tool) {
        if (tool == null) {
            return false;
        }
        boolean isValidMaterial = false;
        Item item = tool.getItem();
        if (item instanceof ToolItem) {
            isValidMaterial = ((ToolItem) item).getMaterial() instanceof LussMaterial;
        }
        return tool.isIn(HMItems.TAG_LUSS) || isValidMaterial;
    }

    public static float applyDamageEffects(LivingEntity target, float amount, DamageSource source){
        float bleedFactor = 0;
        if(source.getAttacker() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) (source.getAttacker());
            ItemStack weapon = attacker.getStackInHand(((LivingEntity) (source.getAttacker())).getActiveHand());
            if (Utils.isVuld(weapon) && attacker instanceof PlayerEntity && target instanceof PlayerEntity) {
                amount /= 2;
            }
            if (Utils.isBatet(weapon)) {
                bleedFactor += .25f;
            }
            if (Utils.isLuss(weapon)) {
                LussLuck.tryRandomAttackEffect(attacker,target,amount);
            }
        }
        if(target.hasStatusEffect(HMStatusEffects.BLEEDING)){
            bleedFactor += (target.getStatusEffect(HMStatusEffects.BLEEDING).getAmplifier()+1)*amount/4;
        }
        BatetDeferment.addBloodDebt((BloodDebtTracker) target,amount * bleedFactor);

        if(source != HMDamageSource.HM_BLOOD_DEBT && source != DamageSource.OUT_OF_WORLD){
            amount = BatetDeferment.deferDamage(target, amount);
        }
        return amount;
    }

    public static void GenerateEquipmentAtChance(Random random, LivingEntity entity, EquipmentSlot slot, ItemStack stack, float odds){
        if (random.nextFloat() < odds) {
            entity.equipStack(slot, stack);
        }
    }
}
