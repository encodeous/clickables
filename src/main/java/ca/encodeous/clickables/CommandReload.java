package ca.encodeous.clickables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class CommandReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Clickables.LoadData();
            sender.sendMessage("§aReloaded Data files!");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            sender.sendMessage("§cError reloading configuration files!");
        }
        return true;
    }
}
