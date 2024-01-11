package io.funworld.authplayer.listener;

import io.funworld.authplayer.AuthService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {
    AuthService service;
    public GameListener(AuthService service){
        this.service = service;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        service.addUnLoginPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuite(PlayerQuitEvent event){
        service.removeUnLoginPlayer(event.getPlayer());
    }
}
