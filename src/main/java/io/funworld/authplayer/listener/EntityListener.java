package io.funworld.authplayer.listener;

import io.funworld.authplayer.AuthService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
    public void onAttack(EntityDamageByEntityEvent e) {


    }

}
