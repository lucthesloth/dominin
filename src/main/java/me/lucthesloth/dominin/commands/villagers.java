package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class villagers implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()) {
            sender.sendMessage("No target.");
            return false;
        }
        for (int i = 0; i < 50; i++)
            Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), org.bukkit.entity.EntityType.VILLAGER);
        sender.sendMessage(String.format("Spawned 50 villagers for %s.", Dominin.target.getName()));

        return false;
    }
}
