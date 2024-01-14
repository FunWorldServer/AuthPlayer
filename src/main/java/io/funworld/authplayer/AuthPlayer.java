package io.funworld.authplayer;

import io.funworld.authplayer.command.EmailCommand;
import io.funworld.authplayer.listener.EntityListener;
import io.funworld.authplayer.service.AuthService;
import io.funworld.authplayer.command.AuthCommand;
import io.funworld.authplayer.command.LoginCommand;
import io.funworld.authplayer.command.RegisterCommand;
import io.funworld.authplayer.listener.BlockListener;
import io.funworld.authplayer.listener.GameListener;
import io.funworld.authplayer.service.AuthConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthPlayer extends JavaPlugin {

    AuthService service;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            getLogger().warning("Cannot load jdbc SQL driver.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        service = new AuthService(this);
        registerListeners(service);

        getCommand("register").setExecutor(new RegisterCommand(service));
        getCommand("login").setExecutor(new LoginCommand(service));
        getCommand("authplayer").setExecutor(new AuthCommand());
        getCommand("email").setExecutor(new EmailCommand(service));

        AuthConfig.loadConfig(this);
    }

    @Override
    public void onDisable() {
        AuthConfig.saveConfig();
        saveConfig();
    }

    private void registerListeners(AuthService service){
        getServer().getPluginManager().registerEvents(new GameListener(service),this);
        getServer().getPluginManager().registerEvents(new BlockListener(service),this);
        getServer().getPluginManager().registerEvents(new EntityListener(service),this);
    }
}
