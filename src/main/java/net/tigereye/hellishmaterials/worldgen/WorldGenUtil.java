package net.tigereye.hellishmaterials.worldgen;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.tigereye.hellishmaterials.registration.HMItems;

public class WorldGenUtil {

    public static void GenerateLuss(FeatureContext<DefaultFeatureConfig> context){
        BlockPos lussBlockPos;
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos origin = context.getOrigin();
        //for each block in the spawning area of a glowstone blob
        for(int y = -12; y <= 0; y++){
            for(int z = -8; z <= 8; z++){
                for(int x = -8; x <= 8; x++){
                    lussBlockPos = origin.add(x, y, z);
                    //if that block is glowstone and the block above is neither glowstone nor air
                    if(structureWorldAccess.getBlockState(lussBlockPos).isOf(Blocks.GLOWSTONE)
                            && !structureWorldAccess.getBlockState(lussBlockPos.up()).isOf(Blocks.GLOWSTONE)
                            && !structureWorldAccess.getBlockState(lussBlockPos.up()).isOf(Blocks.AIR))
                    {
                        //transform the block above the glowstone into luss
                        structureWorldAccess.setBlockState(lussBlockPos.up(), HMItems.LUSS_ORE.getDefaultState(), 2);
                    }
                }
            }
        }
    }


    public static void GenerateBatetCrystalGrowth(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        //Generate Batet crystals in lava lakes below glowstone blobs
        BlockPos batetSeedPos;
        //Drop below the blob and drift up to 16 blocks on both horizontal axis.
        batetSeedPos = origin.add(random.nextInt(33) - 16, -12, random.nextInt(33) - 16);
        //decend until something non-air is found
        while (structureWorldAccess.getBlockState(batetSeedPos).isOf(Blocks.AIR)){
            batetSeedPos = batetSeedPos.down();
        }
        //if its lava, sink to the bottom
        //if not, abort crystal creation attempt
        if (structureWorldAccess.getBlockState(batetSeedPos).isOf(Blocks.LAVA)){
            while (structureWorldAccess.getBlockState(batetSeedPos.down()).isOf(Blocks.LAVA)){
                batetSeedPos = batetSeedPos.down();
            }
            //grow a crystal
            int spikelength = 2 + random.nextInt(3) + random.nextInt(3);
            for (int i = 0; i < spikelength; i++){
                structureWorldAccess.setBlockState(batetSeedPos.up(i), HMItems.BATET_ORE.getDefaultState(), 2);
            }
            GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH);
            GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH);
            GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.EAST);
            GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.WEST);
            GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH,  Direction.EAST);
            GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH,  Direction.WEST);
            GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH,  Direction.EAST);
            GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH,  Direction.WEST);
        }
    }


    private static void GrowHorizontalBatetSpike(StructureWorldAccess structureWorldAccess, Random random, BlockPos blockPos, Direction direction){
        //check if growth point is obstructed
        BlockPos blockPos2 = blockPos.offset(direction);
        if(!structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.AIR)
                && !structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.LAVA))
        {
            return;
        }
        int spikelength = 2 + random.nextInt(3) + random.nextInt(3);
        //check if area below growth point is open, if so move down & get bonus length
        if(!structureWorldAccess.getBlockState(blockPos2.down()).isOf(Blocks.AIR)
                && !structureWorldAccess.getBlockState(blockPos2.down()).isOf(Blocks.LAVA))
        {
            blockPos2 = blockPos2.down();
            spikelength += 2;
        }
        //grow the spike
        while(spikelength > 0){
            if(spikelength > 1){
                structureWorldAccess.setBlockState(blockPos2, HMItems.BATET_ORE.getDefaultState(), 2);
                structureWorldAccess.setBlockState(blockPos2.up(), HMItems.BATET_ORE.getDefaultState(), 2);
                blockPos2 = blockPos2.offset(direction).up();
                spikelength -= 2;
            }
            else{
                structureWorldAccess.setBlockState(blockPos2, HMItems.BATET_ORE.getDefaultState(), 2);
                spikelength = 0;
            }
        }
    }

    private static void GrowDiagonalBatetSpike(StructureWorldAccess structureWorldAccess, Random random, BlockPos blockPos, Direction direction, Direction direction2){
        //check if growth point is obstructed
        BlockPos blockPos2 = blockPos.offset(direction).offset(direction2);
        if(!structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.AIR)
                && !structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.LAVA))
        {
            return;
        }
        int spikelength = 2 + random.nextInt(3) + random.nextInt(3);
        //check if area below growth point is open, if so move down & get bonus length
        if(!structureWorldAccess.getBlockState(blockPos2.down()).isOf(Blocks.AIR)
                && !structureWorldAccess.getBlockState(blockPos2.down()).isOf(Blocks.LAVA))
        {
            blockPos2 = blockPos2.down();
            spikelength += 2;
        }
        //grow the spike
        while(spikelength > 0){
            if(spikelength > 1){
                structureWorldAccess.setBlockState(blockPos2, HMItems.BATET_ORE.getDefaultState(), 2);
                structureWorldAccess.setBlockState(blockPos2.up(), HMItems.BATET_ORE.getDefaultState(), 2);
                blockPos2 = blockPos2.offset(direction).offset(direction2).up();
                spikelength -= 2;
            }
            else{
                structureWorldAccess.setBlockState(blockPos2, HMItems.BATET_ORE.getDefaultState(), 2);
                spikelength = 0;
            }
        }
    }

}
