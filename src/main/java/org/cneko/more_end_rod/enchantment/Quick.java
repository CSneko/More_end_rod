package org.cneko.more_end_rod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.cneko.more_end_rod.More_end_rod;

public class Quick extends Enchantment {
    public Quick() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        return item == More_end_rod.NORMAL_ROD || item == More_end_rod.ELECTRIC_ROD || item == More_end_rod.SUPER_ROD || item == More_end_rod.REMOVAL || item == More_end_rod.HELMET_ROD;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    public static int getLvl(ItemStack stack){
        return EnchantmentHelper.getLevel(More_end_rod.QUICK,stack);
    }
}
