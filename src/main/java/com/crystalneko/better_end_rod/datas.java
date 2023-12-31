package com.crystalneko.better_end_rod;

import com.crystalneko.better_end_rod.enchantment.oily;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class datas {
    public static Map<PlayerEntity, ItemStack> sticks = new HashMap<>();

    //对玩家进行插入操作,成功返回true,否则返回false
    public static Boolean stick(PlayerEntity player, ItemStack stack, Hand hand, PlayerEntity target){
        //判断玩家是否已经插入了
        if(isStick(target)){
            return false;
        }else {
            sticks.put(target,stack);
            target.damage(player.getDamageSources().generic(),3.0F); //对目标造成一点伤害
            player.setStackInHand(hand,ItemStack.EMPTY); //删除玩家手上物品
            return true;
        }
    }


     //判断玩家是否已经被插入了
    public static Boolean isStick(PlayerEntity player){
        return sticks.containsKey(player);
    }

    //取出末地烛,成功返回true,否则返回false
    public static Boolean removal(PlayerEntity player,PlayerEntity target){
        if(!isStick(target)){
            //玩家没被插入
            return false;
        }else {
            ItemStack stack = sticks.get(target);
            double successRateUp = 0;
            //获取润滑附魔
            int oily_lvl = oily.getLvl(stack);
            if(oily_lvl != 0){
                //添加插入成功几率
                successRateUp = oily_lvl * 0.05;
            }
            double successRate = 0.1 + successRateUp;
            Random random = new Random();
            boolean isStick = random.nextDouble() < successRate;
            if(isStick){
                //将物品还给玩家
                player.giveItemStack(stack);
                return true;
            }else {
                return false;
            }

        }
    }
}
