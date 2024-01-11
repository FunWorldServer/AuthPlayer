package io.funworld.authplayer;

import io.funworld.authplayer.command.AuthCommand;
import io.funworld.authplayer.command.LoginCommand;
import io.funworld.authplayer.command.RegisterCommand;
import io.funworld.authplayer.listener.BlockListener;
import io.funworld.authplayer.listener.GameListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthPlayer extends JavaPlugin {

    AuthService service;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        service = new AuthService(this);
        registerListeners(service);

        getCommand("register").setExecutor(new RegisterCommand(service));
        getCommand("login").setExecutor(new LoginCommand(service));
        getCommand("authplayer").setExecutor(new AuthCommand());
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void registerListeners(AuthService service){
        getServer().getPluginManager().registerEvents(new GameListener(service),this);
        getServer().getPluginManager().registerEvents(new BlockListener(service),this);
    }
}
