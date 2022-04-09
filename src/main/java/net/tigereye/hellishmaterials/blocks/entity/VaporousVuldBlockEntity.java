package net.tigereye.hellishmaterials.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.List;
import java.util.Optional;

import static java.lang.Float.NaN;

public class VaporousVuldBlockEntity extends BlockEntity{

    private static final int DAMAGE_FREQUENCY = 8;
    private static final int SPREAD_FREQUENCY = 16;
    //private static final Random random = new Random();
    public int timeOffset = -1;
    public double age = 0;
    public double progress = 0;
    public double lifespan = 18;
    public double[] decayRes = {NaN, NaN, NaN, NaN, NaN, NaN};

    public VaporousVuldBlockEntity(BlockPos pos, BlockState state) {
        super(HMItems.VAPOROUS_VULD_BLOCK_ENTITY, pos, state);
        //timeOffset = random.nextInt(SPREAD_FREQUENCY);
    }

    public void clearDecayResistance(Direction direction){
        int id = direction.getId();
        if(id >= 0 && id < 6) {
            decayRes[direction.getId()] = NaN;
        }
        else{
            System.out.println("Out of bound direction given to clearDecayResistance");
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("timeOffset",timeOffset);
        tag.putDouble("age",age);
        tag.putDouble("progress",progress);
        tag.putDouble("lifespan",lifespan);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        timeOffset = tag.getInt("timeOffset");
        age = tag.getDouble("age");
        progress = tag.getDouble("progress");
        lifespan = tag.getDouble("lifespan");
    }

    public static void tick(World world, BlockPos pos, BlockState state, VaporousVuldBlockEntity be) {
        if(state.getBlock() != HMItems.VAPOROUS_VULD){
            world.removeBlockEntity(pos);
        }
        if(be.timeOffset == -1){
            //be.timeOffset = 0;
            be.timeOffset = world.getRandom().nextInt(SPREAD_FREQUENCY);
        }
        if((!world.isClient()) && world.getTime() % DAMAGE_FREQUENCY == 0) {
            Box hitbox = new Box(pos);
            List<LivingEntity> livingEntityList = world.getNonSpectatingEntities(LivingEntity.class, hitbox);
            List<ItemEntity> itemEntityList = world.getNonSpectatingEntities(ItemEntity.class, hitbox);
            for (ItemEntity ie :
                    itemEntityList) {
                if (!(ie.getStack().isIn(HMItems.ITEM_TAG_IMMUNE_TO_VULD))) {
                    int damage = 3;
                    for (double side:be.decayRes) if(side == -1) damage++;
                    ie.damage(DamageSource.WITHER, damage);
                }
            }
            for (LivingEntity le :
                    livingEntityList) {
                int damage = 3;
                for (double side:be.decayRes) if(side == -1) damage++;
                le.damage(DamageSource.WITHER, damage);
            }
        }
        if ((!world.isClient()) && world.getTime() % SPREAD_FREQUENCY == be.timeOffset) {
            if (be.age > be.lifespan) {
                world.removeBlockEntity(pos);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                return;
            }

            be.decayRes[0] = tryConsumeBlock(world, pos.down(), be, be.decayRes[0], world.getBlockState(pos.down()));
            if (world.getTime() % (SPREAD_FREQUENCY*2) == be.timeOffset) {
                be.decayRes[1] = tryConsumeBlock(world, pos.up(), be, be.decayRes[1], world.getBlockState(pos.up()));
            }
            be.decayRes[2] = tryConsumeBlock(world, pos.north(), be, be.decayRes[2], world.getBlockState(pos.north()));
            be.decayRes[3] = tryConsumeBlock(world, pos.south(), be, be.decayRes[3], world.getBlockState(pos.south()));
            be.decayRes[4] = tryConsumeBlock(world, pos.west(), be, be.decayRes[4], world.getBlockState(pos.west()));
            be.decayRes[5] = tryConsumeBlock(world, pos.east(), be, be.decayRes[5], world.getBlockState(pos.east()));

            be.age++;
            be.progress++;
        }
    }

    private static double tryConsumeBlock(World world, BlockPos pos, VaporousVuldBlockEntity be, double decayRes, BlockState target) {
        //if the block is excluded from consumption, skip it
        if(decayRes == -1){
            //refund a bit of the material's age gains
            be.age -= .05;
            return -1;
        }
        //if decay resistance has not been set and so is NaN, calculate it
        if(decayRes != decayRes){
            if(target.isIn(HMItems.BLOCK_TAG_IMMUNE_TO_VULD)){
                be.age -= .05;
                return -1;
            }
            decayRes = Math.cbrt(target.getBlock().getBlastResistance());
        }
        //if the vuld's progress is greater than or equal to the target's decay resistance, consume it. Then set resistance to -1
        if(be.progress >= decayRes){
            world.breakBlock(pos,false);
            world.setBlockState(pos,HMItems.VAPOROUS_VULD.getDefaultState());
            Optional<VaporousVuldBlockEntity> optional = world.getBlockEntity(pos, HMItems.VAPOROUS_VULD_BLOCK_ENTITY);
            if(optional.isPresent()) {
                VaporousVuldBlockEntity newBE = optional.get();
                newBE.lifespan = Math.min(be.lifespan - decayRes - 1,be.lifespan-be.age);

            }
            return -1;
        }
        else{
            be.age -= .05;
        }
        return decayRes;
    }



}
