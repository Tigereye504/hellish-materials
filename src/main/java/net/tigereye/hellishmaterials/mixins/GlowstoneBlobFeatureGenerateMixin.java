package net.tigereye.hellishmaterials.mixins;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.GlowstoneBlobFeature;
import net.tigereye.hellishmaterials.registration.HM_Items;

@Mixin(GlowstoneBlobFeature.class)
public class GlowstoneBlobFeatureGenerateMixin {
    @Inject(
        at = @At("TAIL"),
        method = "generate")
    public void HellishMaterialsGenerateLussAndBatet(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig, CallbackInfoReturnable<Boolean> info){
        //generate luss where glowstone and ceiling meet
        BlockPos lussBlockPos;
        //for each block in the spawning area of a glowstone blob
        for(int y = -12; y <= 0; y++){
            for(int z = -8; z <= 8; z++){
                for(int x = -8; x <= 8; x++){
                    lussBlockPos = blockPos.add(x, y, z);
                    //if that block is glowstone and the block above is neither glowstone nor air
                    if(structureWorldAccess.getBlockState(lussBlockPos).isOf(Blocks.GLOWSTONE)
                    && !structureWorldAccess.getBlockState(lussBlockPos.up()).isOf(Blocks.GLOWSTONE)
                    && !structureWorldAccess.getBlockState(lussBlockPos.up()).isOf(Blocks.AIR))
                    {
                        //transform the block above the glowstone into luss
                        structureWorldAccess.setBlockState(lussBlockPos.up(), HM_Items.LUSS_ORE.getDefaultState(), 2);
                    }
                }
            }
        }

        //Generate Batet crystals in lava lakes below glowstone blobs
        BlockPos batetSeedPos;
        //Drop below the blob and drift up to 16 blocks on both horizontal axis.
        batetSeedPos = blockPos.add(random.nextInt(33) - 16, -12, random.nextInt(33) - 16);
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
                structureWorldAccess.setBlockState(batetSeedPos.up(i), HM_Items.BATET_ORE.getDefaultState(), 2);
            }
            HM_GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH);
            HM_GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH);
            HM_GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.EAST);
            HM_GrowHorizontalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.WEST);
            HM_GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH,  Direction.EAST);
            HM_GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.NORTH,  Direction.WEST);
            HM_GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH,  Direction.EAST);
            HM_GrowDiagonalBatetSpike(structureWorldAccess, random, batetSeedPos, Direction.SOUTH,  Direction.WEST);
        }
    }

    private void HM_GrowHorizontalBatetSpike(StructureWorldAccess structureWorldAccess, Random random, BlockPos blockPos, Direction direction){
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
                structureWorldAccess.setBlockState(blockPos2, HM_Items.BATET_ORE.getDefaultState(), 2);
                structureWorldAccess.setBlockState(blockPos2.up(), HM_Items.BATET_ORE.getDefaultState(), 2);
                blockPos2 = blockPos2.offset(direction).up();
                spikelength -= 2;
            }
            else{
                structureWorldAccess.setBlockState(blockPos2, HM_Items.BATET_ORE.getDefaultState(), 2);
                spikelength = 0;
            }
        }
    }

    private void HM_GrowDiagonalBatetSpike(StructureWorldAccess structureWorldAccess, Random random, BlockPos blockPos, Direction direction, Direction direction2){
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
                structureWorldAccess.setBlockState(blockPos2, HM_Items.BATET_ORE.getDefaultState(), 2);
                structureWorldAccess.setBlockState(blockPos2.up(), HM_Items.BATET_ORE.getDefaultState(), 2);
                blockPos2 = blockPos2.offset(direction).offset(direction2).up();
                spikelength -= 2;
            }
            else{
                structureWorldAccess.setBlockState(blockPos2, HM_Items.BATET_ORE.getDefaultState(), 2);
                spikelength = 0;
            }
        }
    }
}
