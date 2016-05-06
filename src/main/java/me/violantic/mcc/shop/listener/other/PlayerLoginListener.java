package me.violantic.mcc.shop.listener.other;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.database.acount.Account;
import me.violantic.mcc.shop.listener.MCCListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Ethan on 5/5/2016.
 */
public class PlayerLoginListener extends MCCListener<MCC> {

    public PlayerLoginListener(MCC plugin) {
        super(plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if(player != null) {
            Account account = new Account(player.getUniqueId().toString());
            if(MCC.getInstance().getEconomy().existsIfNotCreate(account)) {
                MCC.getInstance().getAsyncHandler().async(() -> MCC.getInstance().getEconomy().getRepositoryCache().put(player.getUniqueId(), account));
                player.sendMessage(ChatColor.DARK_AQUA + "You have logged into MCC");
                player.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "--------------------");
                player.sendMessage(ChatColor.AQUA + "Balance: " + ChatColor.YELLOW + account.getAmount());
                player.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "--------------------");
            }
        }
    }

}
