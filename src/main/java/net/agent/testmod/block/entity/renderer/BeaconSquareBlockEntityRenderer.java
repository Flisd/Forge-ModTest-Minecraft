package net.agent.testmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.agent.testmod.block.entity.BeaconSquareBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BeaconSquareBlockEntityRenderer implements BlockEntityRenderer<BeaconSquareBlockEntity> {
    private static final ResourceLocation BEAM_TEXTURE = new ResourceLocation("textures/entity/beacon_beam.png");

    public BeaconSquareBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(BeaconSquareBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        // Render the beacon beam
        float beamHeight = 256.0F; // Adjust the height as needed
        float[] colors = {1.0F, 1.0F, 1.0F}; // RGB colors for the beam

        // Render beam upwards
        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);

        // Render beams in other directions (example: north, south, east, west)
        renderBeamInDirection(blockEntity, poseStack, bufferSource, partialTicks, beamHeight, colors, 0, 0, 1); // North
        renderBeamInDirection(blockEntity, poseStack, bufferSource, partialTicks, beamHeight, colors, 0, 0, -1); // South
        renderBeamInDirection(blockEntity, poseStack, bufferSource, partialTicks, beamHeight, colors, 1, 0, 0); // East
        renderBeamInDirection(blockEntity, poseStack, bufferSource, partialTicks, beamHeight, colors, -1, 0, 0); // West
    }

    private void renderBeamInDirection(BeaconSquareBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks, float beamHeight, float[] colors, int dx, int dy, int dz) {
        BlockPos pos = blockEntity.getBlockPos();
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        int beamLength = 0;

        while (true) {
            mutablePos.move(dx, dy, dz);
            BlockState blockState = blockEntity.getLevel().getBlockState(mutablePos);
            if (blockState.isAir() || blockState.is(Blocks.GLASS) || blockState.is(Blocks.GLASS_PANE)) {
                beamLength++;
            } else {
                break; // Stop beam if not air or glass variant
            }
        }

        if (beamLength > 0) {
            poseStack.pushPose();
            poseStack.translate(dx * beamLength, dy * beamLength, dz * beamLength);
            BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0,(int) beamHeight, colors, 0.15F, 0.175F);
            poseStack.popPose();
        }
    }
    // 4 beams around the beacon
//    @Override
//    public void render(BeaconSquareBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
//        // Render the beacon beam in multiple directions
//        float beamHeight = 256.0F; // Adjust the height as needed
//        float[] colors = {1.0F, 1.0F, 1.0F}; // RGB colors for the beam
//
//        // Render beam upwards
//        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
//
//        // Render beams in other directions (example: north, south, east, west)
//        // Adjust the position and direction as needed
//        poseStack.pushPose();
//        poseStack.translate(0, 0, 1); // North
//        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
//        poseStack.popPose();
//
//        poseStack.pushPose();
//        poseStack.translate(0, 0, -1); // South
//        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
//        poseStack.popPose();
//
//        poseStack.pushPose();
//        poseStack.translate(1, 0, 0); // East
//        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
//        poseStack.popPose();
//
//        poseStack.pushPose();
//        poseStack.translate(-1, 0, 0); // West
//        BeaconRenderer.renderBeaconBeam(poseStack, bufferSource, BEAM_TEXTURE, partialTicks, 1.0F, blockEntity.getLevel().getGameTime(), 0, (int)beamHeight, colors, 0.15F, 0.175F);
//        poseStack.popPose();
//    }
}
