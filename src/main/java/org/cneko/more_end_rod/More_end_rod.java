package org.cneko.more_end_rod;

import org.cneko.more_end_rod.events.PlayerAttack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.cneko.more_end_rod.types.MEREffects;
import org.cneko.more_end_rod.types.MEREnchantments;
import org.cneko.more_end_rod.types.MERItemGroups;
import org.cneko.more_end_rod.types.MERItems;

public class More_end_rod implements ModInitializer{
    public static final String modId = "more_end_rod";

    //-----------------------------------------------------------流体----------------------------------------------------
    //public static BucketItem WHITE_FLUID_BUCKET;
    //public static WhiteFluid.Flowing FLOWING_WHITE_FLUID;
    //public static WhiteFluid.Still STILL_WHITE_FLUID;
    //public static Block WHITE_FLUID;
    @Override
    public void onInitialize(){
        MEREffects.register();
        MERItems.register();
        MEREnchantments.register();
        MERItemGroups.register();

        //-------------------------------------------------------流体--------------------------------------------------
        //FLOWING_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier(modId, "flowing_white_fluid"), new WhiteFluid.Flowing());
        //STILL_WHITE_FLUID = Registry.register(Registries.FLUID, new Identifier(modId, "white_fluid"), new WhiteFluid.Still());
        //WHITE_FLUID_BUCKET = Registry.register(Registries.ITEM, new Identifier(modId, "white_fluid_bucket"),
                //new BucketItem(STILL_WHITE_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            PlayerAttack.init();
        });
    }




}
