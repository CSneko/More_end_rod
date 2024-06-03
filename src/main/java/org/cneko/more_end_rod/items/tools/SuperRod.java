package org.cneko.more_end_rod.items.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class SuperRod extends EndRod {
    public SuperRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        // 如果目标是或玩家
        if(entity instanceof PlayerEntity){
            player.sendMessage(Text.translatable("message.more_end_rod.super_rod.stick.big"));
        }
        return super.useOnEntity(stack, player, entity, hand);
    }

    public static class SuperRodMaterial implements ToolMaterial {
        public static NormalRod.normalRodMaterial INSTANCE = new NormalRod.normalRodMaterial();
        @Override
        public int getDurability() {
            return 20;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 3.0F;
        }

        @Override
        public float getAttackDamage() {
            return 10.0F;
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
