package io.funworld.authplayer;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

public class AuthService {
    AuthPlayer instance;
    Set<UUID> not_login_players;
    public AuthService(AuthPlayer instance){
        this.instance = instance;
        this.not_login_players = new ConcurrentSkipListSet<>();
    }

    public void addUnLoginPlayer(Player player){
        this.not_login_players.add(player.getUniqueId());
    }

    public void removeUnLoginPlayer(Player player){
        this.not_login_players.remove(player.getUniqueId());
    }

    public boolean shouldCancelListener(Player player){
        return this.not_login_players.contains(player.getUniqueId());
    }
}
