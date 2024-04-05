package org.cneko.more_end_rod.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.cneko.more_end_rod.enchantment.Fluorescent;
import org.cneko.more_end_rod.enchantment.Quick;

import static org.cneko.more_end_rod.More_end_rod.ORGASM;
import static org.cneko.more_end_rod.More_end_rod.QUICK;

public class ElectricRod extends ToolItem {
    public ElectricRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(entity instanceof PlayerEntity player && player.getOffHandStack().getItem() == stack.getItem()){
            // 当持有对象是玩家且物品在副手
            // 如果耐久为0
            if(stack.getDamage() == stack.getMaxDamage()){
                //什么都不做
                return;
            }
            if(Quick.getLvl(stack) != 0){
                // 1/400的概率发送文本
                if(new java.util.Random().nextInt(400) == 0){
                    // 生成0~5的随机数
                    int i = new java.util.Random().nextInt(4);
                    player.sendMessage(Text.translatable("message.more_end_rod.electric_rod.using.quick."+i));
                }
                player.damage(player.getDamageSources().generic(), (float) (0.1 * Quick.getLvl(stack)));
            }
            // 添加高潮效果
            StatusEffectInstance orgasm = new StatusEffectInstance(ORGASM,300,1);
            player.addStatusEffect(orgasm);
            if(Fluorescent.getLvl(stack) != 0){
                // 添加发光效果
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000, 1));
            }
            // 1/400的概率发送文本
            if(new java.util.Random().nextInt(400) == 0){
                // 生成0~5的随机数
                int i = new java.util.Random().nextInt(10);
                player.sendMessage(Text.translatable("message.more_end_rod.electric_rod.using."+i));
            }
            // 设置玩家动作
            player.setPose(EntityPose.SWIMMING);
            // 播放粒子效果
            world.addParticle(ParticleTypes.END_ROD,player.getX(),player.getY(),player.getZ(),0,0,0);
            if(player instanceof ServerPlayerEntity serverPlayer) {
                stack.damage(1, Random.create(), serverPlayer);
            }
            player.setStackInHand(Hand.OFF_HAND,stack);
        }
    }

}
