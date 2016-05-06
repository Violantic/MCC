package me.violantic.mcc.shop.database.repo;

import java.sql.ResultSet;

/**
 * Created by Ethan on 5/5/2016.
 */
public interface Repository {

    String[] getQueries();

    ResultSet getCollection(String queryKey);

}
