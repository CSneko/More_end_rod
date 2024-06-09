package org.cneko.more_end_rod.items.tools;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.cneko.more_end_rod.datas;
import org.cneko.more_end_rod.enchantment.Fluorescent;
import org.cneko.more_end_rod.enchantment.Oily;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.Random;

public class NormalRod extends EndRod {

    public NormalRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        super.useOnEntity(stack, player, entity, hand);
        //判断目标是否为玩家
        if(entity instanceof PlayerEntity target){
            double successRateUp = 0;
            //获取润滑附魔
            int oily_lvl = Oily.getLvl(stack);
            if (oily_lvl != 0) {
                //添加插入成功几率
                successRateUp = oily_lvl * 0.05;
            }
            double successRate = 0.1 + successRateUp;
            Random random = new Random();
            boolean isStick = random.nextDouble() < successRate;
            if (isStick) {
                //对玩家执行插入操作
                if (datas.stick(player,stack,hand,target)) {
                    //插入成功
                    if(Fluorescent.getLvl(stack) != 0){
                        // 添加发光效果
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000, 1));
                    }
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.success"), true);
                } else {
                    stack.setDamage(stack.getDamage() - 1); //扣除耐久
                    if(stack.getDamage() == 0){ //故意留的特性
                        player.setStackInHand(hand,ItemStack.EMPTY); //删除物品
                    }else {
                        player.setStackInHand(hand,stack);
                    }
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.failure"), true);
                }
            }else {
               player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.failure"), true);
            }
        }
        return ActionResult.PASS;
    }


    public static class normalRodMaterial implements ToolMaterial{
        public static normalRodMaterial INSTANCE = new normalRodMaterial();
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
