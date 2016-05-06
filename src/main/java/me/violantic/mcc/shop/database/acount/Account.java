package me.violantic.mcc.shop.database.acount;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.database.repo.EconRepository;

/**
 * Created by Ethan on 5/5/2016.
 */
public class Account {

    public String uuid;
    public EconRepository repository;

    public Account(String uuid) {
        this.uuid = uuid;
        this.repository = MCC.getInstance().getEconomy();
    }

    public String getUuid() {
        return uuid;
    }

    public int getAmount() {
        return repository.getAmount(getUuid());
    }
}
