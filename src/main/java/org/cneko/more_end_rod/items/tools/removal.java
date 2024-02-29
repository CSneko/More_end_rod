package org.cneko.more_end_rod.items.tools;

import org.cneko.more_end_rod.datas;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.Random;


public class removal extends ToolItem {
    public removal(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if(player.getWorld().isClient()){
            return ActionResult.PASS;
        }
        //判断目标是否为玩家
        if(entity instanceof PlayerEntity target){
            //取出的概率
            double successRate = 0.3;
            Random random = new Random();
            boolean isRemoval = random.nextDouble() < successRate;
            if (isRemoval) {
                //对玩家执行取出操作
                if (datas.removal(player,stack,hand,target)) {
                    //取出成功
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.removal.success"), true);
                } else {
                    stack.setDamage(stack.getDamage() - 1); //扣除耐久
                    if(stack.getDamage() <= 0){
                        player.setStackInHand(hand,ItemStack.EMPTY); //删除物品
                    }else {
                        player.setStackInHand(hand,stack); //把修改后的物品给予玩家
                    }
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.removal.failure"), true);
                }
            }else {
                player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.removal.failure"), true);
            }
        }
        return ActionResult.PASS;
    }
}
