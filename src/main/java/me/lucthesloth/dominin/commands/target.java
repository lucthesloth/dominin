package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class target implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /target <player>"));
            return true;
        }
        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer not found."));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTarget set to " + t.getName()));
        Dominin.target = t;
        return false;
    }
}
