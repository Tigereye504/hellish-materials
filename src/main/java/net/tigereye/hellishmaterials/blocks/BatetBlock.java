package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class BatetBlock extends Block {
    
    public BatetBlock(){
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            .strength(3f,9f)
            .requiresTool()
            .sounds(BlockSoundGroup.METAL));
    }
}