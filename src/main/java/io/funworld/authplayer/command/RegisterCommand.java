package io.funworld.authplayer.command;

import io.funworld.authplayer.service.AuthInfo;
import io.funworld.authplayer.service.AuthService;
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
            Player player = (Player) sender;
            if(service.shouldCancelListener(player)){
                String password = service.getUserPassword(player.getName(),player.getUniqueId());

                if(password != null){
                    player.sendMessage(ChatColor.RED+"[AuthPlayer]: 账户信息不匹配,请使用'/login <p>'登录或联系管理员.");
                    return false;
                }

                if(args.length == 2) {
                    if (args[0].equals(args[1])) {
                        AuthInfo info = new AuthInfo();
                        info.setUsername(player.getName());
                        info.setUuid(player.getUniqueId().toString());
                        info.setPassword(args[0]);
                        service.writeUserData(info);
                    } else player.sendMessage(ChatColor.RED + "[AuthPlayer]: 两次输入的密码不匹配!请重新输入!");
                }else player.sendMessage(ChatColor.RED+"[AuthPlayer]: 命令参数长度不正确!");
            }else player.sendMessage(ChatColor.RED+"[AuthPlayer]: 您已经登录至 开Fun世界服务器! 请勿重复登陆!");
        }else sender.sendMessage(ChatColor.RED + "Console cannot use this command.");
        return false;
    }
}
