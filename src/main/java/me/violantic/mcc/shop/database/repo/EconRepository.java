package me.violantic.mcc.shop.database.repo;

import me.violantic.mcc.shop.MCC;
import me.violantic.mcc.shop.database.MCCDB;
import me.violantic.mcc.shop.database.acount.Account;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ethan on 5/5/2016.
 */
public class EconRepository implements Repository {

    // ---------------- Queries ---------------- \\
    /**0**/
    String ADD_NEW_USER    = "INSERT INTO {schema.table} VALUES ({values})";
    /**1**/
    String DEL_USER        = "REMOVE * FROM {schema.table} WHERE uuid='{uuid}'";
    /**2**/
    String MODIFY_USER     = "UPDATE {schema.table} SET {values} WHERE uuid='{uuid}'";
    /**3**/
    String GET_ACCOUNT     = "SELECT * FROM {schema.table.} WHERE uuid='{uuid}'";
    // ---------------- ======= ---------------- \\

    public MCCDB database;
    public Map<UUID, Account> repositoryCache;

    public String[] queries = {
        ADD_NEW_USER, DEL_USER, MODIFY_USER, GET_ACCOUNT
    };

    public EconRepository(Map<UUID, Account> repositoryCache) {
       database = MCC.getInstance().getDB();
        this.repositoryCache = repositoryCache;
    }

    public Map<UUID, Account> getRepositoryCache() {
        return repositoryCache;
    }

    @Override
    public String[] getQueries() {
        return queries;
    }

    @Override
    public ResultSet getCollection(String queryKey) {
        try {
            ResultSet set = database.query(getQueries()[3].replace("{uuid}", queryKey));
            if(set != null) {
                return set;
            }

            Bukkit.getLogger().severe("MCC tried to query " + ChatColor.RED + queryKey + ChatColor.RESET + " but return null!");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAmount(String queryKey) {
        try {
            ResultSet set = getCollection(queryKey);
            if (set != null) {
                while (set.next()) {
                    return set.getInt("value");
                }
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public boolean existsIfNotCreate(Account account) {
        String uuid = account.getUuid();
        if(getCollection(uuid) != null) {
            return true;
        }

        database.update(getQueries()[0].replace("{schema.table}", "mcc.economy").replace("{values}", uuid + "," + 1000));
        return false;
    }

}
