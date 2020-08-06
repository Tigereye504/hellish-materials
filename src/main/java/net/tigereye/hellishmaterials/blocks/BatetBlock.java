package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BatetBlock extends Block {
    
    public BatetBlock(){
        super(FabricBlockSettings.of(Material.METAL)
            .strength(3f,6f)
            .breakByHand(false)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .lightLevel(10)
            .sounds(BlockSoundGroup.METAL));
    }
}