package net.agent.testmod.enchantment;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LighthingStrikerEnchantment extends Enchantment {
    protected LighthingStrikerEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public void doPostAttack(LivingEntity p_44686_, Entity p_44687_, int p_44688_) {
        if(!p_44686_.level().isClientSide()){
            ServerLevel world = ((ServerLevel) p_44686_.level());
            BlockPos blockPos = p_44687_.blockPosition();

            for (int i = 0; i < p_44688_; i++) {
                EntityType.LIGHTNING_BOLT.spawn(world, (ItemStack) null, null, blockPos,
                        MobSpawnType.TRIGGERED, true, true);
            }
        }
        super.doPostAttack(p_44686_, p_44687_, p_44688_);
    }
}
