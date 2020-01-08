package ca.encodeous.clickables;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.InvalidObjectException;

public class ClickableBuilder {
    public String ID;

    public ItemStack ClickItem;
    public ItemStackClickable ClickActions;

    public ClickableBuilder(String id, ItemStack item) throws InvalidObjectException {
        ID = id;
        ClickItem = item;
        // Add identifier to prevent renaming, crafting and etc
        NBTContainer container = NBTItem.convertItemtoNBT(item);
        if(container.getString("CLICKABLE_ID") == null){
            container.setString("CLICKABLE_ID",ID);
        }else{
            throw new InvalidObjectException("Item selected for clickable is already used by clickable.");
        }
        ClickActions = new ItemStackClickable();
    }
    public void Add(String input){
        input = input.trim();
        int split = input.indexOf(" ");
        String command = input.substring(0,split);
        String info = input.substring(split+1);
        command = command.toLowerCase();
        info = info.trim();
        switch (command){
            case "message":
                ClickActions.
        }
    }
}
