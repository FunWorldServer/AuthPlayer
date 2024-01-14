package io.funworld.authplayer.command;

import io.funworld.authplayer.service.AuthService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {
    AuthService service;
    public LoginCommand(AuthService service){
        this.service = service;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(service.shouldCancelListener(player)){
                String password = service.getUserPassword(player.getName(),player.getUniqueId());

                if(password == null){
                    player.sendMessage(ChatColor.RED+"[AuthPlayer]: 账户信息不匹配,请使用'/reg <p> <p>'注册或联系管理员.");
                    return false;
                }

                if(args[0].equals(password)){
                    service.removeUnLoginPlayer(player);
                    player.sendMessage(ChatColor.GREEN+"[AuthPlayer]: 登录成功!");
                    return true;
                }else player.sendMessage(ChatColor.RED+"[AuthPlayer]: 密码错误! 请检查您的输入!");
            }else player.sendMessage(ChatColor.RED+"[AuthPlayer]: 您已经登录至 开Fun世界服务器! 请勿重复登陆!");
        }else sender.sendMessage(ChatColor.RED + "Console cannot use this command.");
        return false;
    }
}
