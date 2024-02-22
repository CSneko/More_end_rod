package org.cneko.more_end_rod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class InvisibleEntity extends MobEntity {

    private int tickCount = 0;
    private final int clearTime = 100; // 5秒的tick数

    public InvisibleEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    // 创建不可见实体的静态方法
    /*public static InvisibleEntity spawnInvisibleEntity(World world, BlockPos pos) {
        InvisibleEntity entity = new InvisibleEntity(Better_end_rod.INVISIBLE_ENTITY, world);
        entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        world.spawnEntity(entity);
        return entity;
    }*/

    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return false;
    }

    public boolean canSpawn(WorldView world) {
        return false;
    }

    @Override
    public boolean cannotDespawn() {
        return true; // 防止实体消失
    }


    @Override
    public boolean isPushable() {
        return false; // 禁止被推动
    }


    @Override
    public boolean handleAttack(Entity player) {
        return false; // 禁止受到玩家攻击
    }

    @Override
    public void tick() {
        super.tick();
        // 每 tick 计数器加一
        tickCount++;
        // 如果计数器超过清除时间或者有玩家骑乘了实体，则清除实体
        if (tickCount > clearTime && !this.hasPassengers()) {
            this.remove(RemovalReason.DISCARDED);
        }
    }
}

