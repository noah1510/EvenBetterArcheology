package de.sakurajin.evenbetterarcheology.block.custom;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilBaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Map;
import java.util.stream.Stream;

public class CreeperFossilBlock extends FossilBaseBlock {
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

    public CreeperFossilBlock(Settings settings) {
        super(settings);
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

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CREEPER_SHAPES_FOR_DIRECTION.get(state.get(FACING));
    }
}
