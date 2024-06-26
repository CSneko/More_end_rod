package org.cneko.more_end_rod.items.energy;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.cneko.more_end_rod.items.tools.ElectricRod;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;

public class IndustrialRod extends ElectricRod implements SimpleEnergyItem {
    public long CAPACITY = 100000;
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
        //EnergyStorage e = EnergyStorage.ITEM.find(stack, ContainerItemContext.ofPlayerHand(player, Hand.OFF_HAND));
        //if (e != null) {
            this.setStoredEnergy(stack, this.getStoredEnergy(stack) - amount* 10L);
        //}
        // 将剩下的电量设置为显示耐久
        //if (e != null) {
            stack.setDamage(
                    100-(int)(
                            (
                                    (double)this.getStoredEnergy(stack) / (double)this.getEnergyCapacity(stack)
                            )*100
                    )
            );
        //}
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        try {
            //EnergyStorage e = EnergyStorage.ITEM.find(stack,ContainerItemContext.withConstant(stack));
            // 获取电量
            long amount = this.getStoredEnergy(stack);
            // 将电量转换为 k 为单位
            amount = amount / 1000;
            tooltip.add(Text.translatable("item.more_end_rod.universal.energy", amount + "k", this.getEnergyCapacity(stack) / 1000 + "k"));
        }catch (Exception ignored){}
    }
    @Override
    public void setStoredEnergy(ItemStack stack, long amount) {
        SimpleEnergyItem.setStoredEnergyUnchecked(stack, amount);
        stack.setDamage(
                100-(int)(
                        (
                                (double)this.getStoredEnergy(stack) / (double)this.getEnergyCapacity(stack)
                        )*100
                )
        );
    }


    public static class IndustrialRodMaterial implements ToolMaterial {
        public static IndustrialRodMaterial INSTANCE = new IndustrialRodMaterial();
        @Override
        public int getDurability() {
            return 100;
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
