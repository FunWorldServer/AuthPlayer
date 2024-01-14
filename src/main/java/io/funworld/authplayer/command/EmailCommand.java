package io.funworld.authplayer.command;

import io.funworld.authplayer.service.AuthService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EmailCommand implements CommandExecutor {
    AuthService service;
    public EmailCommand(AuthService service){
        this.service = service;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
