package org.cneko.more_end_rod.types;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cneko.more_end_rod.enchantment.Fluorescent;
import org.cneko.more_end_rod.enchantment.Oily;
import org.cneko.more_end_rod.enchantment.Quick;

import static org.cneko.more_end_rod.More_end_rod.modId;

public class MEREnchantments {
    public static Enchantment OILY = new Oily(); //润滑
    public static final Fluorescent FLUORESCENT = new Fluorescent(); // 荧光
    public static final Enchantment QUICK = new Quick(); // 快速

    public static void register(){
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"oily"),OILY); //润滑
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"fluorescent"),FLUORESCENT); //荧光
        Registry.register(Registries.ENCHANTMENT,new Identifier(modId,"quick"),QUICK); //荧光
    }
}
