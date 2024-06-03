package org.cneko.more_end_rod;

import net.minecraft.item.*;
import org.cneko.more_end_rod.effects.Orgasm;
import org.cneko.more_end_rod.enchantment.Fluorescent;
import org.cneko.more_end_rod.enchantment.Oily;
import org.cneko.more_end_rod.enchantment.Quick;
import org.cneko.more_end_rod.events.PlayerAttack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.cneko.more_end_rod.items.armors.HelmetRod;
import org.cneko.more_end_rod.items.tools.*;

public class More_end_rod implements ModInitializer{
    public static final String modId = "more_end_rod";
    //--------------------------------------------------------物品----------------------------------------------------
    public static final NormalRod NORMAL_ROD = new NormalRod(NormalRod.normalRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(20).maxDamageIfAbsent(20)); //普通末地烛
    public static final ElectricRod ELECTRIC_ROD = new ElectricRod(ElectricRod.ElectricRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(2000)); //红石末地烛
    public static final SuperRod SUPER_ROD = new SuperRod(SuperRod.SuperRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(50).maxDamageIfAbsent(50)); //超级末地烛
    public static final Removal REMOVAL = new Removal(Removal.removalMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(30).maxDamageIfAbsent(30)); //取物器

    public static final HelmetRod HELMET_ROD = new HelmetRod(HelmetRod.HelmetRodMaterial.INSTANCE, ArmorItem.Type.HELMET,new Item.Settings().maxCount(1).maxDamage(20).maxDamageIfAbsent(20)); //头戴末地烛
    //-----------------------------------------------------------流体----------------------------------------------------
    //public static BucketItem WHITE_FLUID_BUCKET;
    //public static WhiteFluid.Flowing FLOWING_WHITE_FLUID;
    //public static WhiteFluid.Still STILL_WHITE_FLUID;
    //public static Block WHITE_FLUID;

    //--------------------------------------------------------附魔----------------------------------------------------
    public static Enchantment OILY = new Oily(); //润滑
    public static final Fluorescent FLUORESCENT = new Fluorescent(); // 荧光
    public static final Enchantment QUICK = new Quick(); // 快速
    // ------------------------------------------------------状态效果--------------------------------------------------
    public static final Orgasm ORGASM = new Orgasm();

    // ------------------------------------------------------实体------------------------------------------------------
    // public static final EntityType<InvisibleEntity> INVISIBLE_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier(modId,"invisible_entity"), FabricEntityTypeBuilder.create(SpawnGroup.MISC,InvisibleEntity::new).dimensions(EntityDimensions.fixed(0.1F,0.1F)).build());
    public static boolean toNekoInstalled;
    @Override
    public void onInitialize(){
        //-------------------------------------------------------物品--------------------------------------------------
        Registry.register(Registries.ITEM,new Identifier(modId,"normal_rod"),NORMAL_ROD); //普通末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"electric_rod"),ELECTRIC_ROD); //电动末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"super_rod"),SUPER_ROD); //超级末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"removal"),REMOVAL); //取物器
        Registry.register(Registries.ITEM,new Identifier(modId,"helmet_rod"),HELMET_ROD); //头戴末地烛
        //-------------------------------------------------------流体--------------------------------------------------
        //FLOWING_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier(modId, "flowing_white_fluid"), new WhiteFluid.Flowing());
        //STILL_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier(modId, "white_fluid"), new WhiteFluid.Still());
        //WHITE_FLUID_BUCKET = Registry.register(Registries.ITEM, new Identifier(modId, "white_fluid_bucket"),
                //new BucketItem(STILL_WHITE_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        //-------------------------------------------------------附魔--------------------------------------------------
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"oily"),OILY); //润滑
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"fluorescent"),FLUORESCENT); //荧光
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"quick"),QUICK); //荧光
        // ------------------------------------------------------状态效果------------------------------------------------
        Registry.register(Registries.STATUS_EFFECT,new Identifier(modId,"orgasm"),ORGASM); //高潮

        Registry.register(Registries.ITEM_GROUP, new Identifier(modId, "end_rod"), ITEM_GROUP);//物品组


        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            PlayerAttack.init();
        });
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NORMAL_ROD))
            .displayName(Text.translatable("itemGroup.more_end_rod.end_rod"))
            .entries((context, entries) -> {
                entries.add(REMOVAL);
                entries.add(SUPER_ROD);
                entries.add(ELECTRIC_ROD);
                entries.add(NORMAL_ROD);
            })
            .build();


}
