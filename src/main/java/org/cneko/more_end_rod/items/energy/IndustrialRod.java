package org.cneko.more_end_rod.items.energy;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.impl.transfer.transaction.TransactionManagerImpl;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.cneko.more_end_rod.items.tools.ElectricRod;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

public class IndustrialRod extends ElectricRod implements SimpleEnergyItem {
    public long CAPACITY = 10^5;
    public static final int MAX_INSERT = 1000;
    public static final int MAX_EXTRACT = 1000;
    public IndustrialRod() {
        super(IndustrialRodMaterial.INSTANCE, new Settings().maxCount(1));
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return CAPACITY;
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return MAX_INSERT;
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return MAX_EXTRACT;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void damage(ItemStack stack, int amount, Random random, ServerPlayerEntity player) {
        // 将减少耐久改为减少电量
        EnergyStorage e = EnergyStorage.ITEM.find(stack, ContainerItemContext.ofPlayerHand(player, Hand.OFF_HAND));
        if (e != null) {
            this.setStoredEnergy(stack, e.getAmount() - amount);
        }
    }


    public static class IndustrialRodMaterial implements ToolMaterial {
        public static IndustrialRodMaterial INSTANCE = new IndustrialRodMaterial();
        @Override
        public int getDurability() {
            return 2000;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 3.0F;
        }

        @Override
        public float getAttackDamage() {
            return 3.0F;
        }

        @Override
        public int getMiningLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 15;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.END_ROD);
        }
    }
}
