package de.sakurajin.evenbetterarcheology.block.entity.client;

import de.sakurajin.evenbetterarcheology.api.block.FossilCraftableWithEntity;
import de.sakurajin.evenbetterarcheology.block.fossils.blockEntity.VillagerFossilBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class VillagerFossilBlockEntityRenderer implements BlockEntityRenderer<VillagerFossilBlockEntity> {
    public VillagerFossilBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(VillagerFossilBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        matrices.push();

        BlockState state = Objects.requireNonNull(entity.getWorld()).getBlockState(entity.getPos());
        Direction facing = state.getBlock() instanceof FossilCraftableWithEntity ? state.get(FossilCraftableWithEntity.FACING) : Direction.NORTH;

        //rotation based on direction the Block ist facing
        switch (facing) {
            case EAST -> {
                matrices.translate(0.75f, 0.95f, 0.5f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90));
            }
            case WEST -> {
                matrices.translate(0.25f, 0.95f, 0.5f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
            }
            case NORTH -> matrices.translate(0.5f, 0.95f, 0.25f);
            case SOUTH -> {
                matrices.translate(0.5f, 0.95f, 0.75f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));;
            }
            default -> matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90));
        }

        //scale item to 0.5x size
        matrices.scale(0.5f, 0.5f, 0.5f);

        //render item in inventory to hand position with lightlevel at blockpos
        itemRenderer.renderItem(entity.getInventoryContents().get(0), ModelTransformationMode.FIXED, getLightLevel(Objects.requireNonNull(entity.getWorld()), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);

        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
