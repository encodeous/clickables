package ca.encodeous.clickables;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.io.InvalidObjectException;

public class ClickableMessageHandler implements Listener {
    @EventHandler
    public void OnPlayerAsyncMessage(AsyncPlayerChatEvent e){
        if(ClickableBuilderManager.playerClickableMap.containsKey(e.getPlayer().getUniqueId())){
            String message = e.getMessage().trim();
            e.setCancelled(true);
            if(message.trim().equals("cancel")){
                ClickableBuilderManager.playerClickableMap.remove(e.getPlayer().getUniqueId());
                e.getPlayer().sendMessage("Cancelling builder!");
                return;
            }
            if(message.trim().equals("save")){
                if(ClickableBuilderManager.playerClickableMap.get(e.getPlayer().getUniqueId()) == null){
                    e.getPlayer().sendMessage("No item has been specified for Clickables, cancelling builder!");
                    ClickableBuilderManager.playerClickableMap.remove(e.getPlayer().getUniqueId());
                }else{
                    Clickables.items.idMap.put(ClickableBuilderManager.playerClickableMap.get(e.getPlayer().getUniqueId()).ID, ClickableBuilderManager.playerClickableMap.get(e.getPlayer().getUniqueId()));
                    e.getPlayer().sendMessage("Saved item!");
                }
            }
            if(ClickableBuilderManager.playerClickableMap.get(e.getPlayer().getUniqueId()) == null){
                ItemStack clickedItem = e.getPlayer().getInventory().getItemInMainHand();
                if(!(clickedItem.getType() == Material.AIR)){
                    try {
                        ClickableBuilderManager.playerClickableMap.put(e.getPlayer().getUniqueId(),new ClickableBuilder(message,clickedItem));
                        e.getPlayer().sendMessage(clickedItem.getType()+" assigned to clickable id "+message);
                        e.getPlayer().sendMessage("Success!");
                        e.getPlayer().sendMessage("Please enter actions to execute when a player right-clicks the item: ");
                        e.getPlayer().sendMessage("Valid Actions: ");
                        e.getPlayer().sendMessage("message - Send a message to the user that right-clicked the item");
                        e.getPlayer().sendMessage("server-message - Send a message to the rest of the server when the item is right-clicked");
                        e.getPlayer().sendMessage("command - Make the player execute a command when they right-click the item.");
                        e.getPlayer().sendMessage("server-command - Executes a server command when the item is right-clicked");


                    } catch (InvalidObjectException ex) {
                        e.getPlayer().sendMessage("The item selected is already a Clickable item. Please try again.");
                    }
                }else{
                    e.getPlayer().sendMessage("Please have an item in your main hand when you assign an id. Please try again.");
                }
            }else{

            }
        }
    }
    public void HandleMessage(String message){

    }
}
