package ca.encodeous.clickables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandRemoveClickable implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try{
            String key = args[0];
            if(Clickables.items.idMap.containsKey(key)){
                Clickables.items.idMap.remove(key);
                sender.sendMessage("§6§lRemoved Clickable with ID: "+key);
                return true;
            }else{
                sender.sendMessage("§c§lInvalid Key.");
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
}
