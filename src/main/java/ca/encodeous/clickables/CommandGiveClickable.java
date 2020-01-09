package ca.encodeous.clickables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CommandGiveClickable implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try{
            String player = args[0];
            PlayerInventory pi = Clickables.instance.getServer().getPlayer(player).getInventory();
            int count = Integer.parseInt(args[1]);
            if(count>64){
                sender.sendMessage("§c§lPlease set a stack size of less or equal to 64.");
                return true;
            }
            String key = args[2];
            if(Clickables.items.idMap.containsKey(key)){
                ItemStack istack = Clickables.items.idMap.get(key).clickItems.clone();
                istack.setAmount(count);
                if(pi.firstEmpty() == -1){
                    sender.sendMessage("§c§lPlease empty your inventory!");
                }else{
                    pi.setItem(pi.firstEmpty(),istack);
                    sender.sendMessage("§aItem Added to your Inventory");
                }
            }else{
                sender.sendMessage("§cThe ID specified does not exist!");
            }

        }catch(Exception e){
            return false;
        }
        return true;
    }
}
