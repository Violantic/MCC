package me.violantic.mcc.shop.listener;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.listener.other.PlayerLoginListener;
import me.violantic.mcc.shop.listener.other.ShopClickListener;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCCListenerRegistry extends ListenerRegistry<MCC> {

    public MCCListenerRegistry(MCC plugin) {
        super(plugin);
    }

    @Override
    public MCCListener<MCC>[] getListeners() {
        return new MCCListener[] {
                new ShopClickListener(getPlugin()),
                new PlayerLoginListener(getPlugin())
        };
    }

}
