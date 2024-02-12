package org.cneko.better_end_rod;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import org.cneko.better_end_rod.commands.command;
import org.cneko.better_end_rod.enchantment.oily;
import org.cneko.better_end_rod.events.PlayerAttack;
import org.cneko.better_end_rod.fluid.WhiteFluid;
import org.cneko.better_end_rod.toolItems.normalRod;
import org.cneko.better_end_rod.toolItems.normalRodMaterial;
import org.cneko.better_end_rod.toolItems.removal;
import org.cneko.better_end_rod.toolItems.removalMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Better_end_rod implements ModInitializer{
    //--------------------------------------------------------物品工具----------------------------------------------------
    public static final normalRod NORMAL_ROD = new normalRod(normalRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(20).maxDamageIfAbsent(20)); //普通末地烛
    public static final removal REMOVAL = new removal(removalMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(30).maxDamageIfAbsent(30)); //取物器
    //-----------------------------------------------------------流体----------------------------------------------------
    public static BucketItem WHITE_FLUID_BUCKET;
    public static WhiteFluid.Flowing FLOWING_WHITE_FLUID;
    public static WhiteFluid.Still STILL_WHITE_FLUID;
    public static Block WHITE_FLUID;

    //--------------------------------------------------------附魔----------------------------------------------------
    public static Enchantment OILY = new oily(); //润滑

    public static boolean toNekoInstalled;
    @Override
    public void onInitialize(){
        //判断是否安装了toNeko
        toNekoInstalled = FabricLoader.getInstance().isModLoaded("toneko");
        //-------------------------------------------------------物品工具--------------------------------------------------
        Registry.register(Registries.ITEM,new Identifier("better_end_rod","normal_rod"),NORMAL_ROD); //普通末地烛
        Registry.register(Registries.ITEM,new Identifier("better_end_rod","removal"),REMOVAL); //取物器
        //-------------------------------------------------------流体--------------------------------------------------
        FLOWING_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier("better_end_rod", "flowing_white_fluid"), new WhiteFluid.Flowing());
        STILL_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier("better_end_rod", "white_fluid"), new WhiteFluid.Still());
        WHITE_FLUID_BUCKET = Registry.register(Registries.ITEM, new Identifier("better_end_rod", "white_fluid_bucket"),
                new BucketItem(STILL_WHITE_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        //-------------------------------------------------------附魔--------------------------------------------------
        Registry.register(Registries.ENCHANTMENT,new Identifier("better_end_rod","oily"),OILY); //润滑


        Registry.register(Registries.ITEM_GROUP, new Identifier("better_end_rod", "end_rod"), ITEM_GROUP);//物品组

        new command();

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            new PlayerAttack();
        });
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NORMAL_ROD))
            .displayName(Text.translatable("itemGroup.better_end_rod.end_rod"))
            .entries((context, entries) -> {
                entries.add(REMOVAL);
                entries.add(NORMAL_ROD);
            })
            .build();


}
