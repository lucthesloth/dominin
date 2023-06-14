package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class choruseffect implements org.bukkit.command.CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()){
            sender.sendMessage("No Target.");
        } else {
            Location l = Dominin.target.getLocation().add(Math.random() * 20 - 10, 0, Math.random() * 20 - 10);
            Dominin.target.teleport(l.getWorld().getHighestBlockAt(l).getLocation());
            Dominin.target.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.LEVITATION, 3, 1));
            sender.sendMessage("Teleported target.");
            return false;
        }
        return true;
    }
}
