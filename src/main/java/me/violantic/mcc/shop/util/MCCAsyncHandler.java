package me.violantic.mcc.shop.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCCAsyncHandler implements AsyncHandler {

    JavaPlugin plugin;

    public MCCAsyncHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }


    @Override
    public void async(Runnable r) {
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), r);
    }

    @Override
    public void sync(Runnable r) {
        if(!Bukkit.getServer().isPrimaryThread()) {
            Bukkit.getScheduler().runTask(getPlugin(), r);
        } else {
            r.run();
        }
    }
}
