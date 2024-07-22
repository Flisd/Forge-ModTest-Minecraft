package net.agent.testmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.*;

public class ExplosiveSwordItem extends SwordItem {

    public ExplosiveSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        // 10% chance to cause an explosion
        if (random.nextFloat() < 0.1) {
            Level world = attacker.getCommandSenderWorld();
            // Create a small explosion at the target's location
            world.explode(null, target.getX(), target.getY(), target.getZ(), 2.0F, Level.ExplosionInteraction.BLOCK);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    public InteractionResult useOn(UseOnContext useOnContext) {
        BlockPos blockPos = useOnContext.getClickedPos();
        Player player = useOnContext.getPlayer();
        Level world = useOnContext.getLevel();
        InteractionHand hand = useOnContext.getHand();
        ItemStack stack = player.getItemInHand(hand);
        // Create a small explosion at the player's location
        world.explode(null, player.getX(), player.getY(), player.getZ(), 3.0F, Level.ExplosionInteraction.BLOW);
        // Damage the item in the player's hand
        if (stack.isDamageableItem()) {
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                stack.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

}
