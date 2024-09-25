package net.agent.testmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.agent.testmod.block.entity.HologramBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.joml.Matrix4f;

public class HologramBlockEntityRenderer implements BlockEntityRenderer<HologramBlockEntity> {
    public HologramBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(HologramBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        String text = entity.getHologramText();
        Font textRenderer = Minecraft.getInstance().font;
        matrices.pushPose();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.scale(-0.01F, -0.01F, 0.01F);
        Matrix4f matrix4f = matrices.last().pose();
        MutableComponent textComponent = Component.literal(text);
        textRenderer.drawInBatch(textComponent, -textRenderer.width(textComponent) / 2, 0, 0xFFFFFF, false, matrix4f, vertexConsumers, Font.DisplayMode.NORMAL, 0, light);
        matrices.popPose();
    }

}
