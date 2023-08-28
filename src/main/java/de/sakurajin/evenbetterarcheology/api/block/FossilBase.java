package de.sakurajin.evenbetterarcheology.api.block;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockItemGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockStateGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.LootTableGenerateable;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public abstract class FossilBase extends HorizontalFacingBlock implements BlockItemGenerateable, BlockStateGenerateable, LootTableGenerateable {
    private final String[] textureVariants;
    private final int blockItemIndex;
    protected final Map<Direction, VoxelShape> SHAPE_DIRECTED;

    //used to give all fossil blocks their own tooltip
    //gets blocks translationkey itself and appends "_tooltip" to get the xyz_tooltip lang content
    //also appends the [x/y] indicator for a set
    private final String translationSuffixKey;

    public FossilBase(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED, String translationSuffixKey) {
        super(settings);
        if(textureVariants.length <= blockItemIndex){
            throw new IllegalArgumentException("BlockItemIndex must be smaller than the length of the textureVariants array");
        }

        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));

        this.textureVariants = textureVariants;
        this.blockItemIndex = blockItemIndex;
        this.SHAPE_DIRECTED = SHAPE_DIRECTED;
        this.translationSuffixKey = translationSuffixKey;
    }

    public FossilBase(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE, String translationSuffixKey) {
        this(
            settings,
            textureVariants,
            blockItemIndex,
            ImmutableMap.of(
                Direction.NORTH, SHAPE,
                Direction.SOUTH, SHAPE,
                Direction.EAST, SHAPE,
                Direction.WEST, SHAPE
            ),
            translationSuffixKey
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient()) {
            world.playSound(null, pos, SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.BLOCKS, 0.1f, 0.35f);
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_DIRECTED.get(state.get(FACING));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        MutableText tooltipText = Text.translatable(this.getTranslationKey() + "_tooltip").formatted(Formatting.GRAY);

        if(translationSuffixKey != null){
            tooltipText.append(Text.translatable(translationSuffixKey).formatted(Formatting.BLUE));
        }

        tooltip.add(tooltipText);
        super.appendTooltip(stack, world, tooltip, options);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
    }

    @Override
    public ItemConvertible generateBlockItem(DatagenModContainer container, String identifier) {
        return container.generateBlockItem(this, container.settings().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.generateBlockStateOrientable(identifier, textureVariants);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        String texture = container.getStringID(textureVariants[blockItemIndex], "block");
        container.generateItemModel(identifier, texture);
    }

    @Override
    public void generateTags(DatagenModContainer container, String identifier) {
        container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
    }

    @Override
    public void generateLootTable(DatagenModContainer container, String identifier){
        container.createBlockLootTable(identifier, null);
    }

}
