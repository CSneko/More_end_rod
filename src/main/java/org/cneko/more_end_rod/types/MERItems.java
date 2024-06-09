package org.cneko.more_end_rod.types;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cneko.more_end_rod.items.armors.HelmetRod;
import org.cneko.more_end_rod.items.tools.ElectricRod;
import org.cneko.more_end_rod.items.tools.NormalRod;
import org.cneko.more_end_rod.items.tools.Removal;
import org.cneko.more_end_rod.items.tools.SuperRod;

import static org.cneko.more_end_rod.More_end_rod.modId;

public class MERItems {
    public static final NormalRod NORMAL_ROD = new NormalRod(NormalRod.normalRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(20).maxDamageIfAbsent(20)); //普通末地烛
    public static final ElectricRod ELECTRIC_ROD = new ElectricRod(ElectricRod.ElectricRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(2000)); //红石末地烛
    public static final SuperRod SUPER_ROD = new SuperRod(SuperRod.SuperRodMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(50).maxDamageIfAbsent(50)); //超级末地烛
    public static final Removal REMOVAL = new Removal(Removal.removalMaterial.INSTANCE,new Item.Settings().maxCount(1).maxDamage(30).maxDamageIfAbsent(30)); //取物器

    public static final HelmetRod HELMET_ROD = new HelmetRod(HelmetRod.HelmetRodMaterial.INSTANCE, ArmorItem.Type.HELMET,new Item.Settings().maxCount(1).maxDamage(20).maxDamageIfAbsent(20)); //头戴末地烛

    public static void register(){
        Registry.register(Registries.ITEM,new Identifier(modId,"normal_rod"),NORMAL_ROD); //普通末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"electric_rod"),ELECTRIC_ROD); //电动末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"super_rod"),SUPER_ROD); //超级末地烛
        Registry.register(Registries.ITEM,new Identifier(modId,"removal"),REMOVAL); //取物器
        Registry.register(Registries.ITEM,new Identifier(modId,"helmet_rod"),HELMET_ROD); //头戴末地烛
    }
}
