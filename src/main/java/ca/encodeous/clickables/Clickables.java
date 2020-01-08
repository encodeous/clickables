package ca.encodeous.clickables;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class Clickables extends JavaPlugin {

    public static File dataFile;
    public static ClickableItems items;
    public static Gson gson = new Gson();
    @Override
    public void onEnable() {
        // Plugin startup logic
        dataFile = new File(getDataFolder()+"/data.json");
        try {
            LoadData();
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().log(new LogRecord(Level.SEVERE,"Failed Loading data!"));
        }
    }

    public void LoadData() throws IOException {
        getLogger().log(new LogRecord(Level.INFO,"Loading data..."));
        if(dataFile.exists()){
            JsonReader jr = new JsonReader(new FileReader(dataFile));
            items = gson.fromJson(jr,ClickableItems.class);
        }else{
            items = new ClickableItems();
            dataFile.createNewFile();
        }
    }

    public void SaveData() throws IOException {
        getLogger().log(new LogRecord(Level.INFO,"Saving data..."));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
        bw.write(gson.toJson(items));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            SaveData();
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().log(new LogRecord(Level.SEVERE,"Failed Saving data!"));
        }
    }
}
