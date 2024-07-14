package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Random rand = new Random();
        List<Holder.Reference<SoundEvent>> eventsList = new ArrayList<>();
        Field[] fields = SoundEvents.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().equals(Holder.Reference.class)) {
                if (field.getName().toLowerCase().contains("note_block")) {
                    try {
                        field.setAccessible(true); // This line is added to ensure you can access private fields
                        eventsList.add((Holder.Reference<SoundEvent>) field.get(null)); // Use null for static fields
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

//         eventsList.get(rand.nextInt(eventsList.size()))
        level.playSound(player, blockPos, eventsList.get(rand.nextInt(eventsList.size())).get(), SoundSource.BLOCKS, 2f, rand.nextFloat(5));
//        level.playSound(player, blockPos,SoundEvents.NOTE_BLOCK_DIDGERIDOO.get(), SoundSource.BLOCKS, 2f, rand.nextFloat(5));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal("like a note block, only different..."));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }

    @Override
    public float getJumpFactor() {
        return super.getJumpFactor() + 2f;
    }
}
