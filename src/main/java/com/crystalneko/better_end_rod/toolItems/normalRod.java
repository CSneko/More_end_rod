package com.crystalneko.better_end_rod.toolItems;


import com.crystalneko.better_end_rod.datas;
import com.crystalneko.better_end_rod.enchantment.oily;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import java.util.Random;

public class normalRod extends ToolItem {

    public normalRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        //判断目标是否为玩家
        if(entity instanceof PlayerEntity){
            PlayerEntity target = (PlayerEntity) entity;
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
                //对玩家执行插入操作
                if (datas.stick(player,stack,hand,target)) {
                    //插入成功
                    player.sendMessage(Text.translatable("message.better_end_rod.normal_rod.stick.success"), true);
                } else {
                    stack.setDamage(stack.getDamage() - 1); //扣除耐久
                    if(stack.getDamage() == 0){ //故意留的特性
                        player.setStackInHand(hand,ItemStack.EMPTY); //删除物品
                    }else {
                        player.setStackInHand(hand,stack);
                    }
                    player.sendMessage(Text.translatable("message.better_end_rod.normal_rod.stick.failure"), true);
                }
            }
        }
        return ActionResult.PASS;
    }


}
