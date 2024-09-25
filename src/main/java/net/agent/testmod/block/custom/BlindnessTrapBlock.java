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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class BlindnessTrapBlock extends Block {

    public BlindnessTrapBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;

            // Apply blindness effect
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 3));

            // Schedule a tick for the block
            world.scheduleTick(pos, this, 10);
        }
        super.stepOn(world, pos, state, entity);
    }
    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        BlockPos lavaPos = pos.above(1);
        BlockState lavaState = Blocks.LAVA.defaultBlockState();
        world.setBlock(lavaPos, lavaState, 3);
    }
}