package com.crystalneko.better_end_rod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class datas {
    public static Map<String, List<ItemStack>> sticks = new HashMap<>();

    //对玩家进行插入操作,成功返回true,否则返回false
    public static Boolean stick(PlayerEntity player, ItemStack stack, Hand hand, PlayerEntity target){
        target.damage(player.getDamageSources().generic(),3.0F); //对目标造成一点伤害
        //判断玩家是否已经插入了
        if(isStick(target)){
            return false;
        }else {
            //获取玩家被插入的末地烛
            List<ItemStack> stickeds = sticks.get(target.getName().getString());

            if(stickeds == null){stickeds = new ArrayList<>();}

            stickeds.add(stack);
            sticks.put(target.getName().getString(),stickeds);
            player.setStackInHand(hand,ItemStack.EMPTY); //删除玩家手上物品
            return true;
        }
    }


     //判断玩家是否已经被插入了
    public static Boolean isStick(PlayerEntity player){
        return sticks.containsKey(player.getName().getString());
    }

    //取出末地烛,成功返回true,否则返回false
    public static Boolean removal(PlayerEntity player, ItemStack stack, Hand hand, PlayerEntity target){
        target.damage(player.getDamageSources().generic(),3.0F); //对目标造成一点伤害
        if(!isStick(target)){
            //玩家没被插入
            return false;
        }else {
            //获取被插入的物品
            List<ItemStack> stickeds = sticks.get(target.getName().getString());
            //获取第一个stack
            ItemStack stacked = stickeds.get(0);
            stickeds.remove(stacked);
            //添加lore
            stacked = setLore(stacked,new String[]{"§7它似乎带有奇怪的味道","§1还是不要继续使用为好"});
            stacked = setNbt(stacked,"used","true");
            //将物品还给玩家
            player.dropStack(stacked);
            //删除插入
            sticks.put(target.getName().getString(),stickeds);
            return true;
        }
    }

    public static ItemStack setLore(ItemStack itemStack, String[] lore) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        NbtCompound displayTag = tag.getCompound("display");
        NbtList loreList = new NbtList();

        for (String line : lore) {
            loreList.add(NbtString.of(line));
        }

        displayTag.put("Lore", loreList);
        tag.put("display", displayTag);
        itemStack.setNbt(tag);
        return  itemStack;
    }

    public static ItemStack setNbt(ItemStack stack,String key,String value){
        //获取nbt
        NbtCompound tag = stack.getOrCreateNbt();
        //放置nbt
        tag.putString(key,value);
        //设置nbt
        stack.setNbt(tag);
        return stack;
    }
}
