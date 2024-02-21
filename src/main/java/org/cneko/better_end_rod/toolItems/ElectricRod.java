package org.cneko.better_end_rod.toolItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.cneko.better_end_rod.entity.InvisibleEntity;

import static org.cneko.better_end_rod.Better_end_rod.ORGASM;

public class ElectricRod extends ToolItem {
    private long timer = 0;
    public ElectricRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(entity instanceof PlayerEntity player && selected && player.getOffHandStack().getItem() == this.asItem()){
            // 当持有对象是玩家且玩家选择了该物品并且在副手
            // 添加高潮效果
            StatusEffectInstance orgasm = new StatusEffectInstance(ORGASM,5,1);
            player.addStatusEffect(orgasm);
            // 设置玩家动作
            player.setPose(EntityPose.SWIMMING);
            // 播放粒子效果
            world.addParticle(ParticleTypes.END_ROD,player.getX(),player.getY(),player.getZ(),0,0,0);
            // 每3秒播放一次音效
            long time = System.currentTimeMillis();
            if(time - timer > 3){
                timer = time;
                world.playSound(player,player.getBlockPos(), SoundEvent.of(new Identifier("better_end_rod","electric_rod.use")), SoundCategory.VOICE,1,1);
            }
        }
    }
}
