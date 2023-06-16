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
    private static BukkitTask disabler = null;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            if (task != null) {
                task.cancel();
                task = null;
            }
            else {
                task = Bukkit.getScheduler().runTaskTimer(Dominin.instance, new Runnable() {
                    @Override
                    public void run() {
                        if (random.nextInt(5) == 1){
                            Dominin.target.getWorld().dropItem(Dominin.target.getLocation().add(Math.random() * 5-2, 0, Math.random()*5-2), Dominin.target.getInventory().getItemInMainHand());
                            Dominin.target.getInventory().setItemInMainHand(null);
                        }
                    }
                }, 20L, 60L);
                disabler = Bukkit.getScheduler().runTaskLater(Dominin.instance, new Runnable() {
                    @Override
                    public void run() {
                        if (task == null) return;
                        task.cancel();
                        task = null;
                    }
                }, 20L * 60);
            }
            sender.sendMessage(String.format("Toggled drop items for %s. -> %s", Dominin.target.getName(), task == null ? "off" : "on (AUTO DISABLE IN 1 MINUTE)"));
            return false;
        }
        return true;
    }
}
