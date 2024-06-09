package org.cneko.more_end_rod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.cneko.more_end_rod.More_end_rod;
import org.cneko.more_end_rod.items.tools.EndRod;
import org.cneko.more_end_rod.types.MEREnchantments;


public class Fluorescent extends Enchantment {
    public Fluorescent() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);
    }
    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof EndRod;
    }
    public static int getLvl(ItemStack stack){
        return EnchantmentHelper.getLevel(MEREnchantments.FLUORESCENT,stack);
    }
}
