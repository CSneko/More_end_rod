package org.cneko.more_end_rod.types;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cneko.more_end_rod.effects.Orgasm;

import static org.cneko.more_end_rod.More_end_rod.modId;

public class MEREffects {

    // ------------------------------------------------------状态效果--------------------------------------------------
    public static final Orgasm ORGASM = new Orgasm();
    public static void register() {
        // 注册状态效果
        Registry.register(Registries.STATUS_EFFECT,new Identifier(modId,"orgasm"), MEREffects.ORGASM); //高潮
    }
}
