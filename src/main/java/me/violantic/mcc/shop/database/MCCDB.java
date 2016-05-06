package me.violantic.mcc.shop.database;

import me.violantic.mcc.shop.MCC;

import java.sql.*;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCCDB {

    String host;
    String user;
    String pass;

    Connection connection;

    @MySQL(getDB = "mcc")
    public MCCDB() {
        String name = this.getClass().getAnnotation(MySQL.class).getDB();
        host = MCC.getInstance().dbCredentials.get("host");
        user = MCC.getInstance().dbCredentials.get("user");
        pass = MCC.getInstance().dbCredentials.get("pass");

        try {
            openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + 3306 + "/" + getDB(), user, pass);
    }

    public String getDB() {
        //return this.getClass().getAnnotation(MySQL.class).getDB();
        return "mcc";
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet query(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        ResultSet set = statement.executeQuery();
        while(set.next()) {
            return set;
        }
        return null;
    }

    public void update(String query) {
        MCC.getInstance().getAsyncHandler().async(() -> {
            try {
                PreparedStatement statement = getConnection().prepareStatement(query);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
