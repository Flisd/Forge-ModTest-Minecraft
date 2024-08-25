package net.agent.testmod.item.custom;

import net.agent.testmod.entity.custom.GodOrbProjectileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GodOrbItem extends Item {
    public GodOrbItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            GodOrbProjectileEntity godOrbProjectileEntity = new GodOrbProjectileEntity(level, player);
            godOrbProjectileEntity.setItem(itemstack);
            godOrbProjectileEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(godOrbProjectileEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    private static final ChatFormatting[] RAINBOW_COLORS = {
            ChatFormatting.RED, ChatFormatting.GOLD, ChatFormatting.YELLOW,
            ChatFormatting.GREEN, ChatFormatting.AQUA, ChatFormatting.BLUE,
            ChatFormatting.LIGHT_PURPLE
    };

    @Override
    public Component getName(ItemStack stack) {
        String name = super.getName(stack).getString();
        MutableComponent rainbowName = Component.literal("");

        for (int i = 0; i < name.length(); i++) {
            char letter = name.charAt(i);
            ChatFormatting color = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
            rainbowName.append(Component.literal(String.valueOf(letter)).withStyle(color));
        }

        return rainbowName;
    }
}