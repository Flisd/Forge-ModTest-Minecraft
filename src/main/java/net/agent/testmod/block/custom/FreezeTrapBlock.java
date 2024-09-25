package net.agent.testmod.block.custom;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class FreezeTrapBlock extends Block {

    public FreezeTrapBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));

            world.scheduleTick(pos, this, 10);
        }
        super.stepOn(world, pos, state, entity);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);

        ItemStack instantDamagePotion = new ItemStack(Items.SPLASH_POTION);
        PotionUtils.setPotion(instantDamagePotion, Potions.STRONG_HARMING);

        List<MobEffectInstance> customEffects = Lists.newArrayList();
        customEffects.add(new MobEffectInstance(MobEffects.HARM, 1, 1)); // Instant Harming II

        PotionUtils.setCustomEffects(instantDamagePotion, customEffects);

        for (int i = 0; i < 3; i++) {
            ThrownPotion thrownPotion = new ThrownPotion(world, pos.getX(), pos.getY() + 2, pos.getZ());
            thrownPotion.setItem(instantDamagePotion);
            thrownPotion.setDeltaMovement(Vec3.ZERO);
            world.addFreshEntity(thrownPotion);
        }
    }
}