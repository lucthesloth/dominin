package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class adventure implements CommandExecutor {
    private BukkitTask task;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()) {
            sender.sendMessage("No target.");
            return false;
        }
        if (task != null && !task.isCancelled() && Dominin.target.getGameMode() == org.bukkit.GameMode.ADVENTURE) {
            sender.sendMessage("Player is already in adventure mode!");
            return false;
        }
        Dominin.target.setGameMode(org.bukkit.GameMode.ADVENTURE);
        sender.sendMessage("Set target to adventure mode.");
        task = Bukkit.getScheduler().runTaskLater(Dominin.instance, () -> {
            if (Dominin.target != null && Dominin.target.isOnline()){
                if (Dominin.target.getGameMode() == org.bukkit.GameMode.ADVENTURE)
                    Dominin.target.setGameMode(org.bukkit.GameMode.SURVIVAL);
                sender.sendMessage("Set target to survival mode.");
            }
        }, 20 * 15
        );
        return false;
    }
}
