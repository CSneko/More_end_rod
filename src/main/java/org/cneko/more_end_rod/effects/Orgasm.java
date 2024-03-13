package org.cneko.more_end_rod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

import static org.cneko.more_end_rod.More_end_rod.modId;

public class Orgasm extends StatusEffect {
    private long timer = 0;
    private long damageTimer = 0;
    public Orgasm() {
        super(StatusEffectCategory.BENEFICIAL, 0XFFA500);
    }

    // 这个方法在每个 tick 都会调用，以检查是否应应用药水效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // 这个方法在应用药水效果时的每个tick会被调用。
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        World world = entity.getWorld();
        Random random=new Random();
        // 每3~5秒播放一次音效
        long time = System.currentTimeMillis();
        if(time - timer > random.nextInt(3)+3){
            timer = time;
            // 考虑到会有人对这些音效感到不适，因此请自己添加sounds.json
            // 随机播放0~7的音效
            int sound = random.nextInt(8);
            world.playSound(entity,entity.getBlockPos(), SoundEvent.of(new Identifier(modId,"effect.orgasm."+sound)), SoundCategory.VOICE,1,1);
        }
        // 添加爱心效果
        world.addParticle(ParticleTypes.HEART,entity.getX() + random.nextFloat(2) - 1,entity.getY() + random.nextFloat(1) + 2, entity.getZ() + random.nextFloat(2) - 1,0,2,0);
        // 随机移动玩家的位置
        int x = random.nextInt(10) - 5;
        int z = random.nextInt(10) - 5;
        entity.move(MovementType.SHULKER_BOX,new Vec3d(x*0.001,0,z*0.001));
        if(time - damageTimer > random.nextInt(10) +1){
            damageTimer = time;
            entity.damage(entity.getDamageSources().generic(), 0.2F);
        }

    }


}
