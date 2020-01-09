package ca.encodeous.clickables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class CommandClickList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§6§lClickable List:");
        for(Map.Entry<String,ItemStackClickable> e : Clickables.items.idMap.entrySet()){
            sender.sendMessage("§6§lClickable ID: "+e.getKey()+"§6§l assigned to "+e.getValue().clickItems.getType()+"§6§l.");
        }
        return true;
    }
}
