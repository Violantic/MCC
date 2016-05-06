package me.violantic.mcc.shop.io;

import me.violantic.mcc.shop.exception.MCCException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ethan on 5/5/2016.
 */
public class Config {

    private Map<String, String> config;

    public File file;

    public Config(File file) {
        this.file = file;
        if(file == null) {
            file.mkdirs();
        }
        this.config = new ConcurrentHashMap<>();
    }

    public void loadConfig() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String s;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (!s.startsWith("#") && s.contains("=")) {
                    s = s.trim();
                    String[] command = s.split("=");
                    config.put(command[0], command[1]);
                }
            }
        } catch (Exception e) {
            String string;
            if (this.file == null) {
                throw new MCCException(file);
            }
        }
    }

    public boolean hasKey(String key) {
        return config.containsKey(key);
    }

    public String getString(String key) {
        return (String) config.get(key);
    }

    public String getString(String key, String defaultValue) {
        if (this.hasKey(key)) {
            return getString(key);
        }
        return defaultValue;
    }

    public int getInteger(String key) {
        return Integer.parseInt(config.get(key));
    }

    public int getInteger(String key, int defaultValue) {
        if (this.hasKey(key)) {
            return getInteger(key);
        }
        return defaultValue;
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(config.get(key));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (this.hasKey(key)) {
            return this.getBoolean(key);
        }
        return defaultValue;
    }

}
