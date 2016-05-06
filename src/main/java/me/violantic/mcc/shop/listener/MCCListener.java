package me.violantic.mcc.shop.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCCListener<T extends JavaPlugin> implements Listener {

    private T plugin;

    public MCCListener(T plugin) {
        this.plugin = plugin;
    }

    public T getPlugin() {
        return this.plugin;
    }

}
