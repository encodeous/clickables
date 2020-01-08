package ca.encodeous.clickables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClickableBuilder implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(ClickableBuilderManager.playerClickableMap.containsKey(((Player) sender).getUniqueId())){
                sender.sendMessage("You are already in a Builder Session, please type \"cancel\" or \"save\" in chat to exit builder.");
            }else{
                ClickableBuilderManager.playerClickableMap.put(((Player) sender).getUniqueId(), null);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"     &f&lClickable Builder"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&rWelcome to the builder. " +
                        "Your currently held item will be used when you chose an id to assign to it. If you wish to cancel this process at any time, please type &c&lcancel&r in chat. To save the item, type &a&lsave&r and the builder will save & exit. To start the process, please type an clickable identifier in chat:"));
            }
        }else{
            sender.sendMessage("This command can only be executed by a player");
        }
        return true;
    }
}
