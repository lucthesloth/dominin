package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class cobwebs implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++){
                    Dominin.target.getWorld().getBlockAt(Dominin.target.getLocation().getBlockX() + i, Dominin.target.getLocation().getBlockY(), Dominin.target.getLocation().getBlockZ() + j).setType(org.bukkit.Material.COBWEB);
                }
            }
            sender.sendMessage("Placed cobweb at target.");
            return false;
        }
        return true;
    }
}
