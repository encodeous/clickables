package ca.encodeous.clickables;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public final class Clickables extends JavaPlugin {
    public static Clickables instance;
    public static ClickableItems items;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        ConfigurationSerialization.registerClass(ItemStackClickable.class,"ItemStackClickable");
        try {
            LoadData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load Clickables data!");
        }
        // register commands
        getCommand("clickables").setExecutor(new CommandClickable());
        getCommand("clbuilder").setExecutor(new CommandClickableBuilder());
        getCommand("clgive").setExecutor(new CommandGiveClickable());
        getCommand("clremove").setExecutor(new CommandRemoveClickable());
        getCommand("clist").setExecutor(new CommandClickList());
        getCommand("clreload").setExecutor(new CommandReload());
        // register events
        getServer().getPluginManager().registerEvents(new ClickableMessageHandler(),this);
        getServer().getPluginManager().registerEvents(new ClickHandler(),this);
        new BukkitRunnable() {
            public void run() {
                SaveData();
            }
        }.runTaskTimer(instance, 0, 5 * 60 * 20);
    }

    public static void LoadData() throws IOException, InvalidConfigurationException {
        System.out.println("Loading Clickables Data...");
        items = new ClickableItems();
        instance.getConfig().load(instance.getDataFolder()+"/config.yml");
        if(items.idMap == null){
            items.idMap = new HashMap<>();
        }
        for(String key : instance.getConfig().getConfigurationSection("data").getKeys(false)){
            items.idMap.put(key,(ItemStackClickable) instance.getConfig().get("data."+key));
        }
    }

    public static void SaveData(){
        try{
            System.out.println("Saving Clickables Data...");
            instance.getConfig().set("data",items.idMap);
            instance.getConfig().save(instance.getDataFolder()+"/config.yml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SaveData();
    }
}
