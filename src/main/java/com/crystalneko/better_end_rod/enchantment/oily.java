package com.crystalneko.better_end_rod.enchantment;

import com.crystalneko.better_end_rod.Better_end_rod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import java.util.Map;

public class oily extends Enchantment {
    public oily() {
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
    public static int getLvl(ItemStack stack){
        if(stack.hasEnchantments()){
            //获取润滑附魔
            Enchantment oily = Better_end_rod.OILY;
            //获取物品的附魔列表
            NbtList enchantmentsList = stack.getEnchantments();
            for (int i = 0; i < enchantmentsList.size(); i++) {
                NbtCompound enchantmentTag = enchantmentsList.getCompound(i);
                Identifier id = Identifier.tryParse(enchantmentTag.getString("id"));
                int level = enchantmentTag.getInt("lvl");
                Map<Enchantment, Integer> itemEnchantments = EnchantmentHelper.get(stack);
                if (itemEnchantments.containsKey(oily)) {
                    return level;
                }
            }
        }
        return 0;
    }


}
