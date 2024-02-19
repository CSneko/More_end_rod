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

public class SuperRod extends ToolItem {
    public SuperRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        Identifier targetId = Registries.ENTITY_TYPE.getId(entity.getType());
        // 如果目标是猫娘或玩家
        if(targetId.getPath().equalsIgnoreCase("neko") || entity instanceof PlayerEntity){
            player.sendMessage(Text.translatable("message.better_end_rod.super_rod.stick.big"));
        }
        return super.useOnEntity(stack, player, entity, hand);
    }
}
