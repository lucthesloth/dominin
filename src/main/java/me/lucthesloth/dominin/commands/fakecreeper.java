package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class fakecreeper implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            Dominin.target.playSound(Dominin.target.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 2 ,1);
            sender.sendMessage("Played creeper sound at target.");
            return false;
        }
        return true;
    }
}
