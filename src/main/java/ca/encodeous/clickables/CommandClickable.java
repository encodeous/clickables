package ca.encodeous.clickables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandClickable implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&fClickable &6&lCommands:"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7/clbuilder &f- Hold an item when running this command to create a clickable item - Requires encodeous.clickables.builder"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f/clgive <player> <amount> <id> &7- Gives a specified amount of a clickable item to the player - Requires encodeous.clickables.give"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7/clremove <id> &f- Removes a clickable item from Clickable - Requires encodeous.clickables.remove"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f/clist &7- Lists all Clickables - Requires encodeous.clickables.list"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7/clreload &f- Reloads all Clickables data - Requires encodeous.clickables.reload"));
        return true;
    }
}
