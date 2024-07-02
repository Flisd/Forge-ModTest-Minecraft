package net.agent.testmod.block.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LaunchYou1000BlocksInTheAirBlock extends Block {
    public LaunchYou1000BlocksInTheAirBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getJumpFactor() {
        return super.getJumpFactor() + 70f;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal("What could go wrong walking over this..."));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }
}
