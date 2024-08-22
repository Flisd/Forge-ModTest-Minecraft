package net.agent.testmod.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

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
        ItemStack offHand = player.getOffhandItem();

        boolean hasTotem = mainHand.getItem() instanceof TotemOfMagnet || offHand.getItem() instanceof TotemOfMagnet;

        if (hasTotem) {
            Level world = player.level();
            List<Entity> entities = world.getEntities(player, player.getBoundingBox().inflate(5)); // 5-block radius

            for (Entity entity : entities) {
                if (entity instanceof ItemEntity) {
                    pullEntityTowardsPlayer((ItemEntity) entity, player);
                }
            }
        }
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
