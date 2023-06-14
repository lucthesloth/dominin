package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class explodeOre implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            me.lucthesloth.dominin.EventListener.explodeNextOre = true;
            sender.sendMessage("Next ore will explode.");
            return false;
        }
        return true;
    }
}
