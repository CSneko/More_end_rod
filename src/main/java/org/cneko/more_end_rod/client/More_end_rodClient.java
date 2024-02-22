package org.cneko.more_end_rod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.cneko.more_end_rod.More_end_rod;


public class More_end_rodClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
       /* FluidRenderHandlerRegistry.INSTANCE.register(More_end_rod.STILL_WHITE_FLUID, More_end_rod.FLOWING_WHITE_FLUID, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x4CC248
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), More_end_rod.STILL_WHITE_FLUID, More_end_rod.FLOWING_WHITE_FLUID);
    */
    }
}
