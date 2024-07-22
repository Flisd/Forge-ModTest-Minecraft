package net.agent.testmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Random;

public class LightingSwordItem extends SwordItem {

    public LightingSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        // 15% chance to causing lighting
        if (random.nextFloat() < 0.15) {
            Level world = attacker.getCommandSenderWorld();
            // Create lightning bolt at the target's location
            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightningBolt.moveTo(target.getX(), target.getY(), target.getZ());
            world.addFreshEntity(lightningBolt);
        }

        // Call the superclass method
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        BlockPos blockPos = useOnContext.getClickedPos();
        Player player = useOnContext.getPlayer();
        Level world = useOnContext.getLevel();
        InteractionHand hand = useOnContext.getHand();
        ItemStack stack = player.getItemInHand(hand);
        // Create a lightning bolt at the clicked block's location
        stack.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(hand));
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.moveTo(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
        world.addFreshEntity(lightningBolt);
        return InteractionResult.SUCCESS;
    }
}
