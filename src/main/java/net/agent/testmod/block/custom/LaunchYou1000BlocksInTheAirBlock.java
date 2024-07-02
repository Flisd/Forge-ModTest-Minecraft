package net.agent.testmod.block.custom;

import net.minecraft.world.level.block.Block;

public class LaunchYou1000BlocksInTheAirBlock extends Block {
    public LaunchYou1000BlocksInTheAirBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getJumpFactor() {
        return super.getJumpFactor() + 70f;
    }
}
