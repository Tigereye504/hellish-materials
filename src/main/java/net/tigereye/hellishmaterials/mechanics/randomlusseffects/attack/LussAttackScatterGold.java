package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;

import java.util.HashMap;
import java.util.Map;

public class LussAttackScatterGold implements LussAttackEffect{
    private static final Map<Integer,Item> lootTable = new HashMap<Integer,Item>();
    static{
        lootTable.put(0, Items.GOLDEN_HOE);
        lootTable.put(1, Items.GOLDEN_AXE);
        lootTable.put(2, Items.GOLDEN_PICKAXE);
        lootTable.put(3, Items.GOLDEN_SHOVEL);
        lootTable.put(4, Items.GOLDEN_SWORD);
        lootTable.put(5, Items.GOLDEN_HELMET);
        lootTable.put(6, Items.GOLDEN_CHESTPLATE);
        lootTable.put(7, Items.GOLDEN_LEGGINGS);
        lootTable.put(8, Items.GOLDEN_BOOTS);
        lootTable.put(9, Items.NETHER_GOLD_ORE);
        lootTable.put(10, Items.DEEPSLATE_GOLD_ORE);
        lootTable.put(11, Items.GOLD_ORE);
        lootTable.put(12, Items.RAW_GOLD);
        lootTable.put(13, Items.RAW_GOLD);
        lootTable.put(14, Items.RAW_GOLD);
        lootTable.put(15, Items.RAW_GOLD);
        lootTable.put(16, Items.GOLD_INGOT);
        lootTable.put(17, Items.GOLD_INGOT);
        lootTable.put(18, Items.GOLD_INGOT);
        lootTable.put(19, Items.GOLD_INGOT);
    }


    Quality quality;
    int weight;

    public LussAttackScatterGold(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
    }
    @Override
    public Quality getQuality() {
        return this.quality;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        int drops = LussLuck.StackSizeRandomizer(damage*damage,null,luck);
        World w = defender.getWorld();
        double x = defender.getX();
        double y = defender.getBodyY(.5);
        double z = defender.getZ();
        for(int i = 0; i < drops; i++){
            int rand = w.getRandom().nextInt(200);
            Item item = lootTable.getOrDefault(rand,Items.GOLD_NUGGET);
            ItemEntity entity = new ItemEntity(w,
                    x, y, z, new ItemStack(item, 1));
            double angle = 2*Math.PI*i/drops;
            entity.setVelocity(Math.sin(angle)*.2,.8,Math.cos(angle)*.2);
            w.spawnEntity(entity);
        }
    }
}
