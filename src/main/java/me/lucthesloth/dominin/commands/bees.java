package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bee;
import org.jetbrains.annotations.NotNull;

public class bees implements org.bukkit.command.CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()){
            sender.sendMessage("No target.");
        } else {
            for (int i = 0; i <= 5; i++){
                Bee b = (Bee) Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), org.bukkit.entity.EntityType.BEE);
                b.setAnger(100000);
                b.setTarget(Dominin.target);
            }
            sender.sendMessage("Spawned bees at target.");
            return true;
        }
        return false;
    }
}
