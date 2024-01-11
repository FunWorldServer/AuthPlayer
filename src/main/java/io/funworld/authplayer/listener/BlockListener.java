package io.funworld.authplayer.listener;

import io.funworld.authplayer.AuthService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
    AuthService service;
    public BlockListener(AuthService service){
        this.service = service;
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(service.shouldCancelListener(event.getPlayer())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(service.shouldCancelListener(event.getPlayer())){
            event.setCancelled(true);
        }
    }
}
