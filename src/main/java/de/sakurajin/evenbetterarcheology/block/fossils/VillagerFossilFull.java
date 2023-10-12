package de.sakurajin.evenbetterarcheology.block.fossils;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilCraftableWithEntity;
import de.sakurajin.evenbetterarcheology.block.fossils.blockEntity.VillagerFossilBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class VillagerFossilFull extends FossilCraftableWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty INVENTORY_LUMINANCE = IntProperty.of("inventory_luminance", 0, 15); //used to store the amount of light that the item in its inventory would emit and to emit that luminance itself

    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> VILLAGER_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    createCuboidShape(4.75, 0, 9, 11, 10, 12),
                    createCuboidShape(4, 10, 7, 12, 20, 12.5),
                    createCuboidShape(3, 20, 2, 11, 29, 7.5)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    createCuboidShape(5, 0, 4, 11.25, 10, 7),
                    createCuboidShape(4, 10, 3.5, 12, 20, 9),
                    createCuboidShape(5, 20, 8.5, 13, 29, 14)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(
                    createCuboidShape(4, 0, 4.75, 7, 10, 11),
                    createCuboidShape(3.5, 10, 4, 9, 20, 12),
                    createCuboidShape(8.5, 20, 3, 14, 29, 11)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    createCuboidShape(9, 0, 5, 12, 10, 11.25),
                    createCuboidShape(7, 10, 4, 12.5, 20, 12),
                    createCuboidShape(2, 20, 5, 7.5, 29, 13)).reduce(VoxelShapes::union).get());

    public VillagerFossilFull() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE).luminance((state) -> state.get(VillagerFossilFull.INVENTORY_LUMINANCE)),
            new String[]{
                    "fossils/villager_fossil_0",
                    "fossils/villager_fossil_1",
                    "fossils/villager_fossil_2",
            },
            0,
            VILLAGER_SHAPES_FOR_DIRECTION
        );
        this.setDefaultState(this.stateManager.getDefaultState().with(INVENTORY_LUMINANCE, 0));

        this.craftingParts = new String[]{"villager_fossil_head", "villager_fossil_body"};
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VillagerFossilBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(INVENTORY_LUMINANCE);
    }
}
