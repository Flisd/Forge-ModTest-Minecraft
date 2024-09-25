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
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class BadPotionUpBlock extends Block {

    public BadPotionUpBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        world.scheduleTick(pos, this, 5);
        super.stepOn(world, pos, state, entity);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);

        ItemStack instantDamagePotion = new ItemStack(Items.SPLASH_POTION);
        PotionUtils.setPotion(instantDamagePotion, Potions.EMPTY);

        List<MobEffectInstance> customEffects = Lists.newArrayList();
        customEffects.add(new MobEffectInstance(MobEffects.HARM, 1, 1));
        customEffects.add(new MobEffectInstance(MobEffects.POISON, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.WITHER, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.HUNGER, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.BAD_OMEN, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 1));
        customEffects.add(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.LEVITATION, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1000, 1));

        ArrayList<MobEffectInstance> effectList = Lists.newArrayList();
        effectList.add(customEffects.get(new Random().nextInt(customEffects.size())));

        PotionUtils.setCustomEffects(instantDamagePotion, effectList);

        ThrownPotion thrownPotion = new ThrownPotion(world, pos.getX(), pos.getY() + 2, pos.getZ());
        thrownPotion.setItem(instantDamagePotion);
        thrownPotion.setDeltaMovement(Vec3.ZERO);
        world.addFreshEntity(thrownPotion);

    }
}