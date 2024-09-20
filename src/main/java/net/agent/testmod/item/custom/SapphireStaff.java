package net.agent.testmod.item.custom;

import net.agent.testmod.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class SapphireStaff extends Item {
    public SapphireStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        BlockPos pos = p_41427_.getClickedPos();
        if(p_41427_.getLevel().isClientSide())
            spawnParticles(p_41427_,pos);
        return InteractionResult.SUCCESS;
    }

    private void spawnParticles(UseOnContext context, BlockPos pos) {
        System.out.println("Spawning particles at: " + pos);
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                context.getLevel().addParticle(ModParticles.SAPPHIRE_PARTICLES.get(),
                        pos.getX() + 0.5d, pos.getY() + 1d, pos.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

}