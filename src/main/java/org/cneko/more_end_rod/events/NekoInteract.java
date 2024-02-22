package org.cneko.more_end_rod.events;

import com.crystalneko.tonekofabric.api.NekoEntityEvents;
import com.crystalneko.tonekofabric.entity.nekoEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class NekoInteract {
    public static boolean playerCanInteract = false;
    public static Map<PlayerEntity,Integer> playerFuckLoli = new HashMap<>();
    public static void init(){
        NekoEntityEvents.ON_INTERACT.register((neko, player, hand) -> {
            ItemStack itemStack = player.getStackInHand(hand);
            if(Registries.ITEM.getId(itemStack.getItem()).getPath().equalsIgnoreCase("normal_rod")){
                interact(neko,player,hand);
            }
            return ActionResult.PASS;
        });
    }


    public static void interact(nekoEntity neko, PlayerEntity player, Hand hand){
        //每点击两次算一次
        if(playerCanInteract){
            playerCanInteract = false;
            return;
        }
        Vec3d scale = neko.getScale();
        neko.setScale(scale.x+0.01,scale.y+0.01,scale.z+0.01);
        playerCanInteract = true;
        if(neko.isBaby()){
            int time = 0;
            if(playerFuckLoli.containsKey(player)){
                //如果保存过次数，则获取保存的次数
                time = playerFuckLoli.get(player);
            }
            switch (time){
                case 0:
                case 1:
                case 2:
                case 4:
                case 5:
                    player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use."+time));
                    playerFuckLoli.put(player,time +1);
                    break;
                case 3:
                    player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use.3"));
                    for (int i = 0;i<3;) {
                        player.sendMessage(Text.translatable("entity.neko.loli.end_rod.use.sys.3"), true);
                        i++;
                    }
                    playerFuckLoli.put(player,time +1);
                    break;
                case 6:
                    //踢出玩家
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        serverPlayer.networkHandler.disconnect(Text.translatable("entity.neko.loli.end_rod.use.kick"));
                    }
                    playerFuckLoli.put(player,0);
                    break;
                default:
                    playerFuckLoli.put(player,0);
            }

        }else {
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
    }
}
