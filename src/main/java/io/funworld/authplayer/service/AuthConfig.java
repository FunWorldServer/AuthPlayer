package io.funworld.authplayer.service;

import io.funworld.authplayer.AuthPlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class AuthConfig {
    public static String sql_username,sql_password,sql_name,sql_url;
    static Connection connection;
    static Set<AuthInfo> infos = new HashSet<>();

    public static void loadConfig(AuthPlayer instance){
        Map<String,String> config = (Map<String, String>) instance.getConfig().get("sql");
        sql_name = config.get("name");
        sql_username = config.get("username");
        sql_password = config.get("password");
        sql_url = config.get("url");
        try {
            connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUserData(AuthInfo info){
        infos.add(info);
    }

    public static boolean addUserEmail(String uuid,String email){
        for(AuthInfo info:infos)
            if(info.getUuid().equals(uuid)) {
                info.setEmail(email);
                return true;
            }
        return false;
    }

    public static AuthInfo getUserData(String uuid){
        for(AuthInfo info:infos)
            if(info.getUuid().equals(uuid))
                return info;
        return null;
    }

    public static void saveConfig(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
