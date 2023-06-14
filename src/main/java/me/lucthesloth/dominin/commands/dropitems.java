package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class dropitems implements org.bukkit.command.CommandExecutor{
    private static Random random = new Random();
    private static BukkitTask task = null;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            if (task != null) {
                task.cancel();
                task = null;
            }
            else task = Bukkit.getScheduler().runTaskTimer(Dominin.instance, new Runnable() {
                @Override
                public void run() {
                    if (random.nextInt(5) == 1){
                        int slot = random.nextInt(36);
                        if (Dominin.target.getInventory().getItem(slot) == null) return;
                        Dominin.target.getWorld().dropItem(Dominin.target.getLocation().add(Math.random() * 5-2, 0, Math.random()*5-2), Dominin.target.getInventory().getItem(slot));
                        Dominin.target.getInventory().setItem(slot, null);
                    }
                }
            }, 20L, 60L);
            sender.sendMessage(String.format("Toggled drop items for %s. -> %s", Dominin.target.getName(), task == null ? "off" : "on"));
            return false;
        }
        return true;
    }
}
