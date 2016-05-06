package me.violantic.mcc.shop.util;

/**
 * Created by Ethan on 5/5/2016.
 */
public interface AsyncHandler {

    void async(Runnable r);

    void sync(Runnable r);

}
