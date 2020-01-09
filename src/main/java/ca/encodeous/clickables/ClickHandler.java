package ca.encodeous.clickables;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.InvalidObjectException;

public class ClickHandler implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void OnPlayerUse(PlayerInteractEvent e){
        ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(is);
        if(!nbtItem.getString("CLICKABLE_ID").equals("") && Clickables.items.idMap.containsKey(nbtItem.getString("CLICKABLE_ID"))){
            if(!e.getPlayer().hasPermission("encodeous.clickables.use")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§cYou do not have the encodeous.clickables.use permission to use this item.");
                return;
            }
            // perform item actions
            e.setCancelled(true);
            // remove 1 of item
            is.setAmount(is.getAmount()-1);
            e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), is);

            String id = nbtItem.getString("CLICKABLE_ID");
            ItemStackClickable isc = Clickables.items.idMap.get(id);
            for(String cmd : isc.consoleCommandActions){
                cmd = cmd.replace("%player%",e.getPlayer().getName());
                Clickables.instance.getServer().dispatchCommand(Clickables.instance.getServer().getConsoleSender(),cmd);
            }
            for(String cmd : isc.playerCommandActions){
                cmd = cmd.replace("%player%",e.getPlayer().getName());
                Clickables.instance.getServer().dispatchCommand(e.getPlayer(),cmd);
            }
            for(String msg : isc.playerMessage){
                msg = msg.replace("%player%",e.getPlayer().getName());
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
            }
            for(String msg : isc.serverMessage){
                msg = msg.replace("%player%",e.getPlayer().getName());
                Clickables.instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',msg));
            }
        }
    }
}
