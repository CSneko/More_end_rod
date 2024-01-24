package com.crystalneko.better_end_rod.commands;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class nyaCommand {
    public static int nya(CommandContext<ServerCommandSource> context){
        Text msg = context.getArgument("neko", Text.class);
        String command = "title @a title \""+msg+"\"";
        ServerCommandSource commandSource = context.getSource();
        MinecraftServer server = context.getSource().getServer();
        ParseResults<ServerCommandSource> parseResults = server.getCommandManager().getDispatcher().parse(command, commandSource);
        try {
            server.getCommandManager().getDispatcher().execute(parseResults);
        } catch (CommandSyntaxException e) {
            System.out.println(e.getMessage());
        }

        return 1;
    }
}
