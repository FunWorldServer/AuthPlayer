package io.funworld.authplayer.listener;

import io.funworld.authplayer.service.AuthService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityListener implements Listener {
    AuthService service;
    public EntityListener(AuthService service){
        this.service = service;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(service.shouldCancelListener(event.getPlayer())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            if (service.shouldCancelListener((Player) event.getDamager())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(service.shouldCancelListener(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(service.shouldCancelListener(player)){
            String command_head = event.getMessage().split(" ")[0];
            if(command_head.equals("/email")||
                    command_head.equals("/login")||
                    command_head.equals("/log")||
                    command_head.equals("/l")||
                    command_head.equals("/reg")||
                    command_head.equals("/register")){
                return;
            }

            event.setCancelled(true);
        }
    }

}
