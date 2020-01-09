package ca.encodeous.clickables;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.io.InvalidObjectException;
import java.util.UUID;

public class ClickableMessageHandler implements Listener {
    @EventHandler
    public void OnPlayerAsyncMessage(AsyncPlayerChatEvent e){
        try{
            UUID playerID = e.getPlayer().getUniqueId();
            if(ClickableBuilderManager.playerClickableMap.containsKey(playerID)){
                String message = e.getMessage().trim();
                e.setCancelled(true);
                if(message.trim().equals("cancel")){
                    ClickableBuilderManager.playerClickableMap.remove(playerID);
                    e.getPlayer().sendMessage("Cancelling builder!");
                    return;
                }
                if(message.trim().equals("save")){
                    if(ClickableBuilderManager.playerClickableMap.get(playerID) == null){
                        e.getPlayer().sendMessage("No ID given! Cancelling builder");
                        ClickableBuilderManager.playerClickableMap.remove(playerID);
                    }else{
                        Clickables.items.idMap.put(
                                (ClickableBuilderManager.playerClickableMap.get(playerID)).ID,
                                ClickableBuilderManager.playerClickableMap.get(playerID).ClickActions);
                        e.getPlayer().sendMessage("§aSaved item!");
                        ClickableBuilderManager.playerClickableMap.remove(playerID);
                        Clickables.SaveData();
                        return;
                    }
                }
                if(ClickableBuilderManager.playerClickableMap.get(playerID) == null){
                    ItemStack clickedItem = e.getPlayer().getInventory().getItemInMainHand();
                    if(!(clickedItem.getType() == Material.AIR)){
                        try {
                            ClickableBuilderManager.playerClickableMap.put(playerID,new ClickableBuilder(message,clickedItem));
                            e.getPlayer().sendMessage("§6Successfully assigned: "+clickedItem.getType()+" to clickable id: §b"+message);
                            e.getPlayer().sendMessage("§6§lPlease enter actions to execute when a player right-clicks the item: ");
                            e.getPlayer().sendMessage("§c§lValid Actions: ");
                            e.getPlayer().sendMessage("§7message - §fSend a message to the user that right-clicked the item, chat formatting with \"&\" symbol.");
                            e.getPlayer().sendMessage("§7server-message - §fSend a message to the rest of the server when the item is right-clicked, chat formatting with \"&\" symbol.");
                            e.getPlayer().sendMessage("§7command - §fMake the player execute a command when they right-click the item, without the \"/\".");
                            e.getPlayer().sendMessage("§7server-command - §fExecutes a server command when the item is right-clicked, without the \"/\".");
                            e.getPlayer().sendMessage("§bWith all of these actions, you can use the placeholder §c%player%§b to fill in as the player who clicked the item.");
                        } catch (InvalidObjectException ex) {
                            e.getPlayer().sendMessage("§cThe item selected is already a Clickable item. Please try again.");
                        }
                    }else{
                        e.getPlayer().sendMessage("§cPlease have an item in your main hand when you assign an id. Please try again.");
                    }
                }else{
                    ClickableBuilder cb = ClickableBuilderManager.playerClickableMap.get(playerID);
                    if(cb.Add(message,e.getPlayer())){
                    }else{
                        e.getPlayer().sendMessage("§c§lValid Actions: ");
                        e.getPlayer().sendMessage("§7message - §fSend a message to the user that right-clicked the item, chat formatting with \"&\" symbol.");
                        e.getPlayer().sendMessage("§7server-message - §fSend a message to the rest of the server when the item is right-clicked, chat formatting with \"&\" symbol.");
                        e.getPlayer().sendMessage("§7command - §fMake the player execute a command when they right-click the item, without the \"/\".");
                        e.getPlayer().sendMessage("§7server-command - §fExecutes a server command when the item is right-clicked, without the \"/\".");
                        e.getPlayer().sendMessage("§bWith all of these actions, you can use the placeholder §c%player%§b to fill in as the player who clicked the item.");
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void HandleMessage(String message){

    }
}
