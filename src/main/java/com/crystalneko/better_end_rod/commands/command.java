package com.crystalneko.better_end_rod.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class command {
    public command(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("nya")
                    .then(argument("neko", StringArgumentType.string())
                            .executes(nyaCommand::nya)
                    )
            );
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("crystalneko")
                    .executes(crystalnekoCommand::noArgs)
            );
        });
    }
}
