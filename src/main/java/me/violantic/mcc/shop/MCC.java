package me.violantic.mcc.shop;

import me.violantic.mcc.shop.database.MCCDB;
import me.violantic.mcc.shop.database.acount.Account;
import me.violantic.mcc.shop.database.repo.EconRepository;
import me.violantic.mcc.shop.io.Config;
import me.violantic.mcc.shop.listener.ListenerRegistry;
import me.violantic.mcc.shop.listener.MCCListenerRegistry;
import me.violantic.mcc.shop.util.AsyncHandler;
import me.violantic.mcc.shop.util.MCCAsyncHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCC extends JavaPlugin {

    private static MCC instance;

    private ListenerRegistry listeners;
    private Config config;
    private MCCDB database;
    private EconRepository econRepository;
    private AsyncHandler asyncHandler;
    private Logger logger;

    public Map<String, String> dbCredentials;

    public Map<UUID, Account> userAccounts;

    @Override
    public void onEnable() {
        instance = new MCC();

        logger = getLogger();

        listeners = new MCCListenerRegistry(this);
        listeners.registerAll();

        if (!this.getServerSettingsFile().exists()) {
            try {
                this.getServerSettingsFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        config = new Config(getServerSettingsFile());
        dbCredentials = new HashMap<>();
        loadCredentials();

        userAccounts = new ConcurrentHashMap<>();

        database = new MCCDB();
        econRepository = new EconRepository(userAccounts);

        asyncHandler = new MCCAsyncHandler(this);
    }

    public void loadCredentials() {
        dbCredentials.put("host", config.getString("host"));
        dbCredentials.put("user", config.getString("user"));
        dbCredentials.put("pass", config.getString("pass"));
    }

    public MCCDB getDB() {
        return database;
    }

    public void log(String message) {
        logger.severe("[MCC][ECONOMY] " + message);
    }

    public EconRepository getEconomy() {
        return econRepository;
    }

    public AsyncHandler getAsyncHandler() {
        return asyncHandler;
    }

    public MCCListenerRegistry getListenerRegistry() {
        return (MCCListenerRegistry) listeners;
    }

    public Config getConfiguration() {
        return config;
    }

    public File getServerSettingsFile() {
        return new File(super.getDataFolder(), "luna.conf");
    }


    public static MCC getInstance() {
        return instance;
    }

}
