package org.cneko.more_end_rod.types;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static org.cneko.more_end_rod.More_end_rod.modId;
import static org.cneko.more_end_rod.types.MERItems.*;

public class MERItemGroups {
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NORMAL_ROD))
            .displayName(Text.translatable("itemGroup.more_end_rod.end_rod"))
            .entries((context, entries) -> {
                entries.add(REMOVAL);
                entries.add(SUPER_ROD);
                entries.add(ELECTRIC_ROD);
                entries.add(NORMAL_ROD);
                entries.add(INDUSTRIAL_ROD);
            })
            .build();

    public static void register(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(modId, "end_rod"), ITEM_GROUP);//物品组
    }
}
