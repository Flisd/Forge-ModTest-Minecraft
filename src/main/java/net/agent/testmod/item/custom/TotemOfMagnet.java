package net.agent.testmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class TotemOfMagnet extends Item {

    public TotemOfMagnet(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack mainHand = player.getMainHandItem();

        if (mainHand.getItem() instanceof TotemOfMagnet) {
            // Reduce durability by 1 every second
            if (event.phase == TickEvent.Phase.END && player.level().getGameTime() % 20 == 0) {
                mainHand.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }

            Level world = player.level();
            List<Entity> entities = world.getEntities(player, player.getBoundingBox().inflate(5)); // 5-block radius

            for (Entity entity : entities) {
                if (entity instanceof ItemEntity) {
                    pullEntityTowardsPlayer((ItemEntity) entity, player);
                }
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();

        if (player != null) {
            ItemStack stack = player.getItemInHand(hand);
            // Break the item completely
            stack.hurtAndBreak(50, player, (p) -> p.broadcastBreakEvent(hand));
            // Trigger an explosion
            world.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 3.0F, Level.ExplosionInteraction.TNT);
        }
        return InteractionResult.SUCCESS;
    }

    private static void pullEntityTowardsPlayer(ItemEntity itemEntity, Player player) {
        double speed = 0.05; // Pull speed
        double dx = player.getX() - itemEntity.getX();
        double dy = player.getY() + 1.0 - itemEntity.getY(); // +1 to adjust for height
        double dz = player.getZ() - itemEntity.getZ();

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add(dx / distance * speed, dy / distance * speed, dz / distance * speed));
    }
}
