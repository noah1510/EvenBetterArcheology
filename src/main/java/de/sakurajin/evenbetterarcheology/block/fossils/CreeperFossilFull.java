package de.sakurajin.evenbetterarcheology.block.fossils;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilCraftable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import java.util.Map;
import java.util.stream.Stream;

public class CreeperFossilFull extends FossilCraftable {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> CREEPER_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    Block.createCuboidShape(3.5, 17.25, 3.5, 12.5, 26.25, 12.5),
                    Block.createCuboidShape(3.5, 5.25, 5.5, 12.5, 17.25, 10.5),
                    Block.createCuboidShape(3, 0, 9.5, 13, 6.5, 14.5),
                    Block.createCuboidShape(3, 0, 1.5, 13, 6.5, 6.5)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    Block.createCuboidShape(3.5, 17.25, 3.5, 12.5, 26.25, 12.5),
                    Block.createCuboidShape(3.5, 5.25, 5.5, 12.5, 17.25, 10.5),
                    Block.createCuboidShape(3, 0, 9.5, 13, 6.5, 14.5),
                    Block.createCuboidShape(3, 0, 1.5, 13, 6.5, 6.5)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    Block.createCuboidShape(3.5, 17.25, 3.5, 12.5, 26.25, 12.5),
                    Block.createCuboidShape(5.5, 5.25, 3.5, 10.5, 17.25, 12.5),
                    Block.createCuboidShape(1.5, 0, 3, 6.5, 6.5, 13),
                    Block.createCuboidShape(9.5, 0, 3, 14.5, 6.5, 13)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(Block.createCuboidShape(3.5, 17.25, 3.5, 12.5, 26.25, 12.5),
                    Block.createCuboidShape(5.5, 5.25, 3.5, 10.5, 17.25, 12.5),
                    Block.createCuboidShape(1.5, 0, 3, 6.5, 6.5, 13),
                    Block.createCuboidShape(9.5, 0, 3, 14.5, 6.5, 13)).reduce(VoxelShapes::union).get());

    public CreeperFossilFull() {
        super(
            FabricBlockSettings.copy(Blocks.BONE_BLOCK),
            new String[]{
                "fossils/creeper_fossil_0",
                "fossils/creeper_fossil_1",
                "fossils/creeper_fossil_2"
            },
            0,
            CREEPER_SHAPES_FOR_DIRECTION
        );

        this.craftingParts = new String[]{"creeper_fossil_head", "creeper_fossil_body"};
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        DefaultParticleType particle = random.nextBoolean() ? ParticleTypes.SMALL_FLAME : ParticleTypes.SMOKE; //50:50 chance for either spawning Smoke or Flames
        Vec3d center = pos.toCenterPos();

        if (world.isClient()) {
            //spawns particle at center of Block with random offset & velocity
            world.addParticle(particle,
                    center.getX() + random.nextFloat() * getRandomSign(random),
                    center.getY() + random.nextFloat() * getRandomSign(random),
                    center.getZ() + random.nextFloat() * getRandomSign(random),
                    random.nextFloat() / 50f * getRandomSign(random),
                    random.nextFloat() / 30f,
                    random.nextFloat() / 50f * getRandomSign(random)
            );
        }
    }

    private static int getRandomSign(Random rand) {
        return (rand.nextBoolean() ? 1 : -1);
    }

}
