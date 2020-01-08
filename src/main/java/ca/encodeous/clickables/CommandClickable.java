package ca.encodeous.clickables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandClickable implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&lClickable &6&lCommands:"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&l/clickable builder - Hold an item when running this command to create a clickable item"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&l/clickable remove <id> - Removes a clickable item from Clickable"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&l/clickable remove - Removes the held item from Clickable"));
        return true;
    }
}
