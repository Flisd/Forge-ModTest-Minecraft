package net.agent.testmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.agent.testmod.TestMod;
import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.agent.testmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.FARMER){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // PARM 1 -> trader level
                // 1-5 levels

            // for the strawberry -> 12 [amount], 10[max uses], 20 [xp] 0.2f [price multiplier]
            trades.get(1).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.STRAWBERRY.get(), 12),
                    10, 20,0.2f));

        }

        if(event.getType() == VillagerProfession.LIBRARIAN){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack speedBoots =  EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.SPEED_BOOTS.get(),1));
            ItemStack lightingStriker1 =  EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.LIGHTING_STRIKER.get(),1));
            ItemStack lightingStriker2 =  EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.LIGHTING_STRIKER.get(),2));
            ItemStack lightingStriker3 =  EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.LIGHTING_STRIKER.get(),3));

            trades.get(1).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    speedBoots,
                    4, 70,0.32f));

            trades.get(1).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    lightingStriker1,
                    4, 70,0.32f));

            trades.get(2).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 57),
                    lightingStriker2,
                    2, 120,0.32f));

            trades.get(5).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    lightingStriker3,
                    1, 170,0.32f));
        }

        if(event.getType() == ModVillagers.ORE_LUCK_MASTER.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((p_219693_, p_219694_) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.SAPPHIRE.get(), 1),
                    10, 20,0.2f));

        }

    }
}
