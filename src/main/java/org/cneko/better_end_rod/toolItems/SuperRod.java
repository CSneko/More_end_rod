package org.cneko.better_end_rod.toolItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.cneko.better_end_rod.enchantment.oily;

import java.util.Random;

public class SuperRod extends ToolItem {
    public SuperRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        Identifier targetId = Registries.ENTITY_TYPE.getId(entity.getType());
        // 判断目标是否为末影龙
        if(targetId.getPath().equalsIgnoreCase("ender_dragon")){
            entity.damage(player.getDamageSources().generic(),10.0F);
            double successRateUp = 0;
            //获取润滑附魔
            int oily_lvl = oily.getLvl(stack);
            if (oily_lvl != 0) {
                //添加插入成功几率
                successRateUp = oily_lvl * 0.05;
            }
            double successRate = 0.1 + successRateUp;
            Random random = new Random();
            boolean isStick = random.nextDouble() < successRate;
            if (isStick) {
                player.sendMessage(Text.translatable("message.better_end_rod.normal_rod.stick.success"), true);
                player.setStackInHand(hand,ItemStack.EMPTY);
            }else {
                player.sendMessage(Text.translatable("message.better_end_rod.normal_rod.stick.failure"), true);
            }
        }else {
            player.sendMessage(Text.translatable("message.better_end_rod.normal_rod.stick.failure"), true);
        }
        return super.useOnEntity(stack, player, entity, hand);
    }
}
