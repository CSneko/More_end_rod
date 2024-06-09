package org.cneko.more_end_rod.items.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.cneko.more_end_rod.api.EndRodEvents;

public class EndRod extends ToolItem {
    public EndRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof PlayerEntity player){
            // 处理监听事件
            EndRodEvents.USE_ON_PLAYER.invoker().useOnPlayer(stack,user,player,hand);
        }
        return ActionResult.PASS;
    }
}
