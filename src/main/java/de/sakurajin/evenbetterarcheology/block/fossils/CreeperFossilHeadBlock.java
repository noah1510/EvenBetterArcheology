package de.sakurajin.evenbetterarcheology.block.fossils;

import de.sakurajin.evenbetterarcheology.api.block.FossilBaseHeadBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShape;

public class CreeperFossilHeadBlock extends FossilBaseHeadBlock {
    private static final VoxelShape SHAPE = createCuboidShape(3, 0, 3, 13, 8, 13);

    public CreeperFossilHeadBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/creeper_fossil_head_0",
                "fossils/creeper_fossil_head_1",
                "fossils/creeper_fossil_head_2"
            },
            0,
            SHAPE
        );
    }

}
