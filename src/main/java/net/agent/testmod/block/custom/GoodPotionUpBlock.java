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
public class GoodPotionUpBlock extends Block {

    public GoodPotionUpBlock(Properties properties) {
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
        customEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 2));
        customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1));
        customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.GLOWING, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.JUMP, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.WATER_BREATHING, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.SATURATION, 1000, 1));
        customEffects.add(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1000, 4));

        ArrayList<MobEffectInstance> effectList = Lists.newArrayList();
        effectList.add(customEffects.get(new Random().nextInt(customEffects.size())));

        PotionUtils.setCustomEffects(instantDamagePotion, effectList);

        ThrownPotion thrownPotion = new ThrownPotion(world, pos.getX(), pos.getY() + 2, pos.getZ());
        thrownPotion.setItem(instantDamagePotion);
        thrownPotion.setDeltaMovement(Vec3.ZERO);
        world.addFreshEntity(thrownPotion);

    }
}