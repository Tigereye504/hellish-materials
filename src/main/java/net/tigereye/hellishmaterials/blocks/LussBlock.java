package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class LussBlock extends Block {
    
    public LussBlock(){
        super(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)
            .strength(3f,6f)
            .requiresTool()
            .lightLevel(10)
            .sounds(BlockSoundGroup.METAL));
    }
}