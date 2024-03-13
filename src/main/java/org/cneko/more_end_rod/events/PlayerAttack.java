package org.cneko.more_end_rod.events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.cneko.more_end_rod.enchantment.Oily;

import java.util.Random;

public class PlayerAttack {
    public static void init(){
        AttackEntityCallback.EVENT.register((player, world, hand, entity, stack) -> {
            if(Registries.ENTITY_TYPE.getId(entity.getType()).getPath().equalsIgnoreCase("ender_dragon")){
                entity.damage(player.getDamageSources().generic(),10.0F);
                double successRateUp = 0;
                //获取润滑附魔
                int oily_lvl = Oily.getLvl(player.getStackInHand(hand));
                if (oily_lvl != 0) {
                    //添加插入成功几率
                    successRateUp = oily_lvl * 0.05;
                }
                double successRate = 0.1 + successRateUp;
                Random random = new Random();
                boolean isStick = random.nextDouble() < successRate;
                if (isStick) {
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.success"), true);
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    // 掉落龙蛋
                    entity.dropItem(Items.DRAGON_EGG);
                }else {
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.failure"), true);
                }
            }
            return ActionResult.PASS;
        });
    }
}
