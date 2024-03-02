package org.cneko.more_end_rod.items.armors;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class HelmetRodMaterial implements ArmorMaterial {
    public static HelmetRodMaterial INSTANCE = new HelmetRodMaterial();
    @Override
    public int getDurability(ArmorItem.Type type) {
        return 100;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.END_ROD);
    }

    @Override
    public String getName() {
        return "helmet_rod";
    }

    @Override
    public float getToughness() {
        return -1;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
