package org.cneko.more_end_rod.events;

import com.crystalneko.tonekofabric.api.NekoEntityEvents;
import com.crystalneko.tonekofabric.entity.nekoEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.cneko.more_end_rod.More_end_rod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NekoEvents {
    public static boolean playerCanInteract = false;
    public static Map<PlayerEntity,Integer> playerFuckLoli = new HashMap<>();
    public static Map<nekoEntity,UUID> playerCanIntoNeko = new HashMap<>();
    public static void init(){
        NekoEntityEvents.ON_INTERACT.register((neko, player, hand) -> {
            ItemStack itemStack = player.getStackInHand(hand);
            if(Registries.ITEM.getId(itemStack.getItem()).getNamespace().equalsIgnoreCase(More_end_rod.modId)){
                interact(neko,player,hand,itemStack);
            }
            return ActionResult.PASS;
        });
        NekoEntityEvents.TICK.register(NekoEvents::tick);
    }


    public static void interact(nekoEntity neko, PlayerEntity player, Hand hand,ItemStack stack){
        //每点击两次算一次
        if(playerCanInteract){
            playerCanInteract = false;
            return;
        }
        playerCanInteract = true;
        Vec3d scale = neko.getScale();
        neko.setScale(scale.x+0.01,scale.y+0.01,scale.z+0.01);
        Identifier itemId = Registries.ITEM.getId(stack.getItem());
        String itemPath = itemId.getPath();
        if(itemPath.equalsIgnoreCase("normal_rod")) {
            if (neko.isBaby()) {
                int time = 0;
                if (playerFuckLoli.containsKey(player)) {
                    //如果保存过次数，则获取保存的次数
                    time = playerFuckLoli.get(player);
                }
                switch (time) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use." + time));
                        playerFuckLoli.put(player, time + 1);
                        break;
                    case 3:
                        player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use.3"));
                        for (int i = 0; i < 3; ) {
                            player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use.sys.3"), true);
                            i++;
                        }
                        playerFuckLoli.put(player, time + 1);
                        break;
                    case 6:
                        //踢出玩家
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            serverPlayer.networkHandler.disconnect(Text.translatable("entity.neko.loli.end_rod.use.kick"));
                        }
                        playerFuckLoli.put(player, 0);
                        break;
                    default:
                        playerFuckLoli.put(player, 0);
                }

            } else {
                //添加仇恨
                neko.increaseHatred(player, 100);
                World world = player.getWorld();
                double x = neko.getX();
                double y = neko.getY();
                double z = neko.getZ();
                //播放被伤害音频
                world.playSound(neko, neko.getBlockPos(), SoundEvent.of(new Identifier("toneko", "entity.neko.hurt_0")),
                        SoundCategory.NEUTRAL, 1.0F, 1.0F);
                //末地烛粒子效果
                int i = 0;
                while (i < 10) {
                    world.addParticle(ParticleTypes.FALLING_HONEY, x, y, z, 0.1D, 0.1D, 0.1D);
                    world.addParticle(ParticleTypes.DRIPPING_HONEY, x, y, z, 0.1D, 0.1D, 0.1D);
                    world.addParticle(ParticleTypes.FALLING_WATER, x, y, z, 0.1D, 0.1D, 0.1D);
                    world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0.1D, 0.1D, 0.1D);
                    i++;
                }
            }
        }else if(itemPath.equalsIgnoreCase("electric_rod")){
            neko.setScale(new Vec3d(scale.x + 0.03,scale.y + 0.03,scale.z + 0.03));
            if(scale.x > 1.75 && scale.y >1.75 && scale.z >1.75 && player.distanceTo(neko) <= 1.0){
                // 如果玩家距离猫娘小于1.0格且猫娘的大小大于1.75,则可以进入猫娘
                player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.stick.success"));
                playerCanIntoNeko.put(neko,player.getUuid());
            }
        }
    }
    public static void tick(nekoEntity neko){
        // 获取能进入的玩家
        if(playerCanIntoNeko.containsKey(neko)){
            PlayerEntity player = neko.getWorld().getPlayerByUuid(playerCanIntoNeko.get(neko));
            if(player != null) {
                // 将玩家传送到猫娘
                player.teleport(neko.getX(), neko.getY(), neko.getZ());
                // 给予玩家失明和缓慢效果
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 1));
                // 给予猫娘缓慢效果
                neko.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 1));
                // 扣除玩家和猫娘0.01血量
                player.damage(neko.getDamageSources().generic(), 0.01F);
                neko.damage(player.getDamageSources().generic(), 0.01F);
                // 如果玩家血量过低则生成小neko
                if(player.getHealth() <= 4 && player instanceof ServerPlayerEntity serverPlayer){
                    ServerWorld serverWorld = serverPlayer.getServerWorld();
                    serverWorld.spawnEntity(neko.createChild(serverWorld,neko));
                    // 将玩家移除
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.removal.success"));
                    playerCanIntoNeko.remove(neko);
                }
                if (player.distanceTo(neko) >= 1.0) {
                    // 如果距离太远则离开
                    player.sendMessage(Text.translatable("message.more_end_rod.normal_rod.removal.success"));
                    playerCanIntoNeko.remove(neko);
                }
            }
        }
    }
}
