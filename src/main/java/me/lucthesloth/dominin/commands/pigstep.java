package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class pigstep implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()) {
            sender.sendMessage("No target.");
            return false;
        }
        for (int i = 0; i < 5; i++) {
            Dominin.target.playSound(Dominin.target.getLocation(), Sound.MUSIC_DISC_PIGSTEP, 3, 1);
        }
        sender.sendMessage(String.format("Played pigstep for %s.", Dominin.target.getName()));
        return false;
    }
}
