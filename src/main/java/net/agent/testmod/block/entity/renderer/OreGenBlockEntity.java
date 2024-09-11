package net.agent.testmod.block.entity.renderer;

import net.agent.testmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OreGenBlockEntity extends BlockEntity {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public OreGenBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORE_GEN_BLOCK_ENTITY.get(), pos, state);
        startOreDispensingTask();
    }

    private void startOreDispensingTask() {
        scheduler.scheduleAtFixedRate(() -> {
            if (level != null && !level.isClientSide) {
                System.out.println("Scheduler running"); // Debug message
                ((net.agent.testmod.block.custom.OreGenBlock) getBlockState().getBlock()).dispenseOre(level, worldPosition);
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public void tick() {
        // Custom tick logic if needed
        System.out.println("Ticking"); // Debug message
    }
}
