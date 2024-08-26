package net.agent.testmod.block.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.entity.custom.WaterLavaProjectileEntity;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ProjectileShooter extends DispenserBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private final Random random = new Random();

    public ProjectileShooter(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void dispenseFrom(ServerLevel world, BlockState state, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos targetPos = pos.relative(direction);
        DispenserBlockEntity dispenser = (DispenserBlockEntity) world.getBlockEntity(pos);

        if (dispenser != null) {
            int slot = dispenser.getRandomSlot(world.random);
            if (slot >= 0) {
                ItemStack stack = dispenser.getItem(slot);

                if (stack.getItem() == ModItems.WATER_LAVA.get()) {
                    // Create and shoot the projectile
                    WaterLavaProjectileEntity projectile = new WaterLavaProjectileEntity(ModEntities.WATER_LAVA_PROJECTILE.get(), world);
                    projectile.setPos(targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5);
                    projectile.shootFromRotation(null, 0.0F, direction.toYRot(), 0.0F, 1.5F, 1.0F);
                    world.addFreshEntity(projectile);

                    // Play sound
                    world.playSound(null, pos, SoundEvents.DISPENSER_DISPENSE, SoundSource.BLOCKS, 1.0F, 1.0F);

                    // Consume the item
                    stack.shrink(1);
                    dispenser.setItem(slot, stack);
                }
            }
        }
    }
}
