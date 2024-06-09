package org.cneko.more_end_rod.api;


import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class EndRodEvents {
    /**
     * 对玩家使用末地烛的回调
     * <p>
     * 我相信你仅通过行参名就知道是做什么的呢
     */
    public static Event<UseOnPlayer> USE_ON_PLAYER =  EventFactory.createArrayBacked(UseOnPlayer.class,
            (listeners) -> (stack, user, entity, hand) -> {
                for (UseOnPlayer listener : listeners) {
                    ActionResult result = listener.useOnPlayer(stack, user, entity, hand);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    /**
     * 紫薇时的回调
     */
    public static Event<UseOnSelf> USE_ON_SELF =  EventFactory.createArrayBacked(UseOnSelf.class,
            (listeners) -> (stack, user) -> {
                for (UseOnSelf listener : listeners) {
                    ActionResult result = listener.useOnSelf(stack, user);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    public interface UseOnPlayer {
        ActionResult useOnPlayer(ItemStack stack, PlayerEntity user, PlayerEntity entity, Hand hand);
    }
    public interface UseOnSelf {
        ActionResult useOnSelf(ItemStack stack, PlayerEntity user);
    }
}
