package net.agent.testmod.item.custom;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.agent.testmod.item.ModItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class ChanceOrb extends Item {

    public ChanceOrb(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) throws CommandSyntaxException {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemStack = event.getItem();
            if (itemStack.getItem() instanceof ChanceOrb) {
                Random random = new Random();
                int chance = random.nextInt(4); // 0 to 3, 25% chance for each effect

                switch (chance) {
                    case 0:
                        // Drop all items and spawn junk items
                        dropAllItems(player);
                        spawnJunkItems(player);
                        break;
                    case 1:
                        // Levitation and spawn mobs
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 600, 0));
                        spawnMobs(player);
                        break;
                    case 2:
                        // Run command
                        runGearMeUpMonkeyCommand(player);
                        break;
                    case 3:
                        // Throw player up 100 blocks
                        throwPlayerUp(player);
                        break;
                }
            }
        }
    }

    private static void dropAllItems(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty()) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
            }
        }
        player.getInventory().clearContent();
    }

    private static void spawnJunkItems(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        List<ItemStack> junkItems = List.of(
                new ItemStack(Items.POISONOUS_POTATO),
                new ItemStack(Items.ROTTEN_FLESH),
                new ItemStack(Items.SPIDER_EYE),
                new ItemStack(Items.BONE),
                new ItemStack(Items.STRING),
                new ItemStack(Items.DIRT),
                new ItemStack(Items.GRAVEL),
                new ItemStack(Items.SAND),
                new ItemStack(Items.COBBLESTONE),
                new ItemStack(Items.DEAD_BUSH),
                new ItemStack(Items.STICK),
                new ItemStack(Items.WOODEN_SWORD),
                new ItemStack(Items.WOODEN_PICKAXE),
                new ItemStack(Items.WOODEN_SHOVEL),
                new ItemStack(Items.WOODEN_AXE),
                new ItemStack(Items.WOODEN_HOE),
                new ItemStack(Items.LEATHER_BOOTS),
                new ItemStack(Items.LEATHER_HELMET),
                new ItemStack(Items.LEATHER_CHESTPLATE),
                new ItemStack(Items.LEATHER_LEGGINGS)
        );

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            ItemStack junkItem = junkItems.get(random.nextInt(junkItems.size()));
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), junkItem));
        }
    }

    private static void spawnMobs(Player player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();
        for (int i = 0; i < 10; i++) {
            Vex vex = new Vex(EntityType.VEX, level);
            vex.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(vex);

            Stray stray = new Stray(EntityType.STRAY, level);
            stray.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(stray);
        }
    }

    private static void runGearMeUpMonkeyCommand(Player player) throws CommandSyntaxException {
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            CommandSourceStack source = serverPlayer.createCommandSourceStack();
            Commands commands = serverPlayer.getServer().getCommands();
            ParseResults<CommandSourceStack> parseResults = commands.getDispatcher().parse("GearMeUpMonkey", source);
            commands.getDispatcher().execute(parseResults);
        }
    }

    private static void throwPlayerUp(Player player) {
        player.setDeltaMovement(player.getDeltaMovement().add(0, 100, 0));
    }
}
