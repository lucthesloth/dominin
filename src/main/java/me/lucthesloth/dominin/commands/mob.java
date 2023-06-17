package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;

public class mob implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()) {
            sender.sendMessage("No target.");
            return false;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /mob <mob>");
            return false;
        }
        Location t = Dominin.target.getLocation().add(Math.random()*10 - 5, 1, Math.random()*10 - 5);
        Monster e = (Monster) Dominin.target.getWorld().spawnEntity(t, switch(args[0].toLowerCase()) {
            case "creeper" -> EntityType.CREEPER;
            case "zombie" -> EntityType.ZOMBIE;
            case "skeleton" -> EntityType.SKELETON;
            case "spider" -> EntityType.SPIDER;
            case "warden" -> EntityType.WARDEN;
            default -> EntityType.VEX;
        });
        if (e.getType() == EntityType.SPIDER)
            e.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY, 999999, 1).withParticles(false));
        if (e.getType() == EntityType.SKELETON)
            Dominin.target.getWorld().spawnEntity(t, EntityType.SKELETON);
        e.setTarget(Dominin.target);
        sender.sendMessage("Spawned " + e.getType().getName() + " at target.");
        return false;
    }
}
