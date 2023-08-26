package de.sakurajin.evenbetterarcheology.block.fossils;

import de.sakurajin.evenbetterarcheology.api.block.FossilBaseBodyBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShape;

public class ChickenFossilBodyBlock extends FossilBaseBodyBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 6, 15);

    public ChickenFossilBodyBlock() {
        super(
            FabricBlockSettings.copyOf(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/chicken_fossil_body_0",
                "fossils/chicken_fossil_body_1"},
            0,
            SHAPE
        );
    }

}
