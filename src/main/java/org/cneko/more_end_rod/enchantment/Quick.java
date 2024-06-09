package org.cneko.more_end_rod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.cneko.more_end_rod.items.tools.EndRod;
import org.cneko.more_end_rod.types.MEREnchantments;

public class Quick extends Enchantment {
    public Quick() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof EndRod;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    public static int getLvl(ItemStack stack){
        return EnchantmentHelper.getLevel(MEREnchantments.QUICK,stack);
    }
}
