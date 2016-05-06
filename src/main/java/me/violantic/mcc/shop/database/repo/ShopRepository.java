package me.violantic.mcc.shop.database.repo;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.database.MCCDB;

/**
 * Created by Ethan on 5/5/2016.
 */
public class ShopRepository {

    MCCDB database;

    public ShopRepository() {
        database = MCC.getInstance().getDB();
    }

    public MCCDB getDatabase() {
        return database;
    }

}
