package ca.encodeous.clickables;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
@SerializableAs("ItemStackClickable")
public class ItemStackClickable implements  ConfigurationSerializable{
    public ItemStack clickItems;
    public ArrayList<String> consoleCommandActions = new ArrayList<>();
    public ArrayList<String> playerCommandActions = new ArrayList<>();
    public ArrayList<String> playerMessage = new ArrayList<>();
    public ArrayList<String> serverMessage = new ArrayList<>();

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("clickitems",clickItems);
        result.put("server-command",consoleCommandActions);
        result.put("player-command",playerCommandActions);
        result.put("message",playerMessage);
        result.put("server-message",serverMessage);
        return result;
    }
    public static ItemStackClickable deserialize(Map<String, Object> args){
        ItemStackClickable inst = new ItemStackClickable();
        inst.clickItems = (ItemStack) args.get("clickitems");
        inst.consoleCommandActions = (ArrayList) args.get("server-command");
        inst.playerCommandActions = (ArrayList) args.get("player-command");
        inst.playerMessage = (ArrayList) args.get("message");
        inst.serverMessage = (ArrayList) args.get("server-message");
        return inst;
    }
}
