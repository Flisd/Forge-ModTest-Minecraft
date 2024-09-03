package net.agent.testmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.agent.testmod.block.entity.BeaconSquareBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.List;

public class BeaconSquareBlockEntityRenderer implements BlockEntityRenderer<BeaconSquareBlockEntity> {
    private static final ResourceLocation BEAM_TEXTURE = new ResourceLocation("textures/entity/beacon_beam.png");
    private static final List<Block> STAINED_GLASS_BLOCKS = Arrays.asList(
            Blocks.WHITE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS,
            Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS, Blocks.LIME_STAINED_GLASS,
            Blocks.PINK_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS,
            Blocks.CYAN_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS,
            Blocks.BROWN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, Blocks.RED_STAINED_GLASS,
            Blocks.BLACK_STAINED_GLASS, Blocks.WHITE_STAINED_GLASS_PANE, Blocks.ORANGE_STAINED_GLASS_PANE,
            Blocks.MAGENTA_STAINED_GLASS_PANE, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, Blocks.YELLOW_STAINED_GLASS_PANE,
            Blocks.LIME_STAINED_GLASS_PANE, Blocks.PINK_STAINED_GLASS_PANE, Blocks.GRAY_STAINED_GLASS_PANE,
            Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, Blocks.CYAN_STAINED_GLASS_PANE, Blocks.PURPLE_STAINED_GLASS_PANE,
            Blocks.BLUE_STAINED_GLASS_PANE, Blocks.BROWN_STAINED_GLASS_PANE, Blocks.GREEN_STAINED_GLASS_PANE,
            Blocks.RED_STAINED_GLASS_PANE, Blocks.BLACK_STAINED_GLASS_PANE
    );

    public BeaconSquareBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    private boolean isGlass(BlockState state) {
        return state.is(Blocks.GLASS) || state.is(Blocks.GLASS_PANE) || STAINED_GLASS_BLOCKS.contains(state.getBlock());
    }

    private float[] getGlassColor(BlockState state) {
        Block block = state.getBlock();
        if (block instanceof StainedGlassBlock) {
            DyeColor color = ((StainedGlassBlock) block).getColor();
            return color.getTextureDiffuseColors();
        }
        return new float[]{1.0F, 1.0F, 1.0F}; // Default white color
    }

    @Override
    public void render(BeaconSquareBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        // Render the beacon beam in multiple directions
        float beamHeight = 256.0F; // Adjust the height as needed
        float[] colors = {1.0F, 1.0F, 1.0F}; // RGB colors for the beam

        BlockPos pos = blockEntity.getBlockPos();

        // Render beam upwards
        if (canRenderBeam(blockEntity, pos, 0, 1, 0, colors)) {
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
        }

        // Render beams in other directions (example: north, south, east, west)
        // Adjust the position and direction as needed
        if (canRenderBeam(blockEntity, pos, 0, 0, 1, colors)) {
            poseStack.pushPose();
            poseStack.translate(0, 0, 1); // North
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
            poseStack.popPose();
        }

        if (canRenderBeam(blockEntity, pos, 0, 0, -1, colors)) {
            poseStack.pushPose();
            poseStack.translate(0, 0, -1); // South
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
            poseStack.popPose();
        }

        if (canRenderBeam(blockEntity, pos, 1, 0, 0, colors)) {
            poseStack.pushPose();
            poseStack.translate(1, 0, 0); // East
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
            poseStack.popPose();
        }

        if (canRenderBeam(blockEntity, pos, -1, 0, 0, colors)) {
            poseStack.pushPose();
            poseStack.translate(-1, 0, 0); // West
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
            poseStack.popPose();
        }
    }

    private boolean canRenderBeam(BeaconSquareBlockEntity blockEntity, BlockPos pos, int xOffset, int yOffset, int zOffset, float[] colors) {
        BlockPos newPos = pos.offset(xOffset, yOffset, zOffset);
        BlockState state = blockEntity.getLevel().getBlockState(newPos);
        if (isGlass(state)) {
            float[] glassColor = getGlassColor(state);
            colors[0] *= glassColor[0];
            colors[1] *= glassColor[1];
            colors[2] *= glassColor[2];
            return true;
        }
        return state.isAir();
    }
}
