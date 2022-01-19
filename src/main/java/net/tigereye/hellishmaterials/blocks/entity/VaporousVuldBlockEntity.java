package net.tigereye.hellishmaterials.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.List;
import java.util.Optional;

import static java.lang.Float.NaN;

public class VaporousVuldBlockEntity extends BlockEntity{

    public double age = 0;
    public double progress = 0;
    public double lifespan = 18;
    public double[] decayRes = {NaN, NaN, NaN, NaN, NaN, NaN};

    public VaporousVuldBlockEntity(BlockPos pos, BlockState state) {
        super(HMItems.VAPOROUS_VULD_BLOCK_ENTITY, pos, state);
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
        tag.putDouble("age",age);
        tag.putDouble("progress",progress);
        tag.putDouble("lifespan",lifespan);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        age = tag.getDouble("age");
        progress = tag.getDouble("progress");
        lifespan = tag.getDouble("lifespan");
    }

    public static void tick(World world, BlockPos pos, BlockState state, VaporousVuldBlockEntity be) {
        if((!world.isClient()) && world.getTime() % 8 == 0) {
            Box hitbox = new Box(pos);
            List<LivingEntity> livingEntityList = world.getNonSpectatingEntities(LivingEntity.class, hitbox);
            List<ItemEntity> itemEntityList = world.getNonSpectatingEntities(ItemEntity.class, hitbox);
            for (ItemEntity ie :
                    itemEntityList) {
                if (!(ie.getStack().isIn(HMItems.TAG_VULD))) {
                    ie.damage(DamageSource.WITHER, (float) Math.max(be.lifespan - be.age / 2, 2));
                }
            }
            for (LivingEntity le :
                    livingEntityList) {
                le.damage(DamageSource.WITHER, (float) Math.max(be.lifespan - be.age / 2, 2));
            }
            if (world.getTime() % 16 == 0) {
                if (be.age > be.lifespan) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    world.removeBlockEntity(pos);
                    return;
                }

                be.decayRes[0] = tryConsumeBlock(world, pos.down(), be, be.decayRes[0], world.getBlockState(pos.down()));
                if (world.getTime() % 32 == 0) {
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
            if(target.isIn(HMItems.TAG_IMMUNE_TO_VULD)){
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
