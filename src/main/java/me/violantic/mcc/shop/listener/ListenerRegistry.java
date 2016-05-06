package me.violantic.mcc.shop.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ethan on 5/5/2016.
 */
public abstract class ListenerRegistry<T extends JavaPlugin> {

    private T plugin;

    public ListenerRegistry(T plugin) {
        this.plugin = plugin;
    }

    public abstract MCCListener<T>[] getListeners();

    public T getPlugin() {
        return this.plugin;
    }

    private void register(MCCListener<T> listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    public void registerAll() {
        for(MCCListener<T> listener : getListeners()) {
            register(listener);
        }
    }

    public void unregisterAll() {
        for(MCCListener<T> listener : getListeners()) {
            HandlerList.unregisterAll(listener);
        }
    }

}
