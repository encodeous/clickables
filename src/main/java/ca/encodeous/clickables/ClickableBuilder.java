package ca.encodeous.clickables;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.InvalidObjectException;

public class ClickableBuilder {
    public String ID;

    public ItemStack ClickItem;
    public ItemStackClickable ClickActions;

    public ClickableBuilder(String id, ItemStack item) throws InvalidObjectException {
        ID = id;
        ClickItem = item.clone();
        // Add identifier to prevent renaming, crafting and etc
        NBTItem container = new NBTItem(ClickItem);
        if(container.getString("CLICKABLE_ID") == null){
            container.setString("CLICKABLE_ID",ID);
        }else{
            throw new InvalidObjectException("Item selected for clickable is already used by clickable.");
        }
        ClickItem = container.getItem();
        ClickActions = new ItemStackClickable();
        ClickActions.clickItems = ClickItem;
    }
    public boolean Add(String input, Player player){
        input = input.trim();
        int split = input.indexOf(" ");
        String command = input.substring(0,split);
        String info = input.substring(split+1);
        command = command.toLowerCase();
        info = info.trim();
        switch (command){
            case "message":
                ClickActions.playerMessage.add(info);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lAdded &c&lmessage &a&laction to item right-click action."));
                break;
            case "server-message":
                ClickActions.serverMessage.add(info);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lAdded &c&lserver-message &a&laction to item right-click action."));
                break;
            case "command":
                ClickActions.playerCommandActions.add(info);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lAdded &c&lcommand &a&laction to item right-click action."));
                break;
            case "server-command":
                ClickActions.consoleCommandActions.add(info);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lAdded &c&lserver-command &a&laction to item right-click action."));
                break;
            default:
                return false;
        }
        return true;
    }
}
