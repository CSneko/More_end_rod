package org.cneko.more_end_rod.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class nyaCommand {
    public static int nya(CommandContext<ServerCommandSource> context){
        String msg = context.getArgument("neko", String.class);
        context.getSource().getPlayer().sendMessage(Text.of(msg),true);

        return 1;
    }
}
