package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class ElementalShotEventHandler {
    static String[] Elements = {"fire", "ice", "lighting", "poison"};

    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent event) {
        if (event.getProjectile().getOwner() instanceof Player) {
            Player player = (Player) event.getProjectile().getOwner();
            ItemStack bow = player.getMainHandItem();

            // Check if the bow has the ElementalShot enchantment
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ELEMENT_SHOT.get(), bow);
            if (enchantmentLevel > 0) {
                if (!player.level().isClientSide) {
                    ServerLevel level = (ServerLevel) player.level();
                    BlockPos pos = event.getProjectile().blockPosition();
                    Random rand = new Random();
                    String element = Elements[rand.nextInt(Elements.length)];
                    HitResult hitResult = event.getRayTraceResult();

                    switch (element) {
                        case "fire":
                            BlockState fireState = Blocks.FIRE.defaultBlockState();
                            level.setBlockAndUpdate(pos, fireState);
                            break;
                        case "ice":
                            if (hitResult.getType() == HitResult.Type.BLOCK) {
                                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                                BlockPos hitPos = blockHitResult.getBlockPos();
                                level.setBlockAndUpdate(hitPos, Blocks.ICE.defaultBlockState());
                            }
                            break;
                        case "lighting":
                            EntityType.LIGHTNING_BOLT.spawn(level, (ItemStack) null, null, pos,
                                    MobSpawnType.TRIGGERED, true, true);
                            break;
                        case "poison":
                            if (hitResult.getType() == HitResult.Type.ENTITY) {
                                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                                Entity hitEntity = entityHitResult.getEntity();
                                if (hitEntity instanceof LivingEntity) {
                                    LivingEntity target = (LivingEntity) hitEntity;
                                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1)); // Poison 2 for 5 seconds
                                }
                            }
                            break;
                    }

                    // Destroy the arrow after the effect
                    event.getProjectile().discard();
                }
            }
        }
    }
}
