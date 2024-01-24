package com.crystalneko.better_end_rod.events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import com.crystalneko.ctlibPublic.File.JsonConfiguration;

public class PlayerAttack {
    public PlayerAttack(){
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            //获取主手物品
            ItemStack itemStack = player.getMainHandStack();
            if(itemStack.getItem() == Items.DIAMOND_AXE){
                //如果玩家拿着钻石斧头，直接通过gson崩溃
                JsonConfiguration exitJson = new JsonConfiguration("{\"编程大\":\"佬\"}");
                exitJson.get("编程大佬");
            } else if (itemStack.getItem() == Items.END_ROD) {
                //如果是末地烛，则通过Null崩溃
                player.giveItemStack(null);
            }
            return ActionResult.PASS;
        });
    }
}
