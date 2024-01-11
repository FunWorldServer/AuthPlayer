package io.funworld.authplayer.command;

import io.funworld.authplayer.AuthService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {
    AuthService service;
    public RegisterCommand(AuthService service){
        this.service = service;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

        }else sender.sendMessage(ChatColor.RED + "Console cannot use this command.");
        return false;
    }
}
