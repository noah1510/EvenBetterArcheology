package de.sakurajin.evenbetterarcheology.block.fossils;

import de.sakurajin.evenbetterarcheology.api.block.FossilBase;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShape;

public class SheepFossilHeadBlock extends FossilBase {
    private static final VoxelShape SHEEP_HEAD_SHAPE = Block.createCuboidShape(3, 0, 3, 13, 8, 13);
    public SheepFossilHeadBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/sheep_fossil_head",
            },
            0,
            SHEEP_HEAD_SHAPE,
                "block.evenbetterarcheology.fossil_head_set"
        );
    }
}
