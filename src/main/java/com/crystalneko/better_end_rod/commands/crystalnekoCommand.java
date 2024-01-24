package com.crystalneko.better_end_rod.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.function.Supplier;
public class crystalnekoCommand {
    static Supplier<String> systemInfo = () -> {
        String gameVersion = "1.21 内部版";
        String javaVersion = "openjdk-17-jdk-CrystalNeko特供版";
        String launcher = "Genshin Impact 4.3";
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        String cpu = osBean.getName();
        return String.format("§a游戏版本: %s\n§aJava版本: %s\n§a启动器: %s\n§aCPU: %s", gameVersion, javaVersion, launcher, cpu);
    };
    public static int noArgs(CommandContext<ServerCommandSource> context){
        PlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.of(systemInfo.get()));
        return 1;
    }
    /*
    §a游戏版本: §b 1.21 内部版\n
    §aJava版本: §b openjdk-17-jdk-CrystalNeko特供版\n
    §a启动器: §b Genshin Impact 4.3
    §aCPU: §b xxx
    §aGPU: §b xxx
     */
}
