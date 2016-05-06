package me.violantic.mcc.shop.listener.other;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.listener.MCCListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Ethan on 5/5/2016.
 */
public class ShopClickListener extends MCCListener<MCC> {

    public ShopClickListener(MCC plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {

    }
}
