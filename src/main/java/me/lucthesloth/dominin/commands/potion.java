package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class potion implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()) {
            sender.sendMessage("No target.");
            return false;
        }
        double r = Math.floor(Math.random() * 6);
        PotionEffect potion = switch ((int) r) {
            case 0 -> new PotionEffect(PotionEffectType.BLINDNESS, 30, 1);
            case 1 -> new PotionEffect(PotionEffectType.CONFUSION, 30, 1);
            case 2 -> new PotionEffect(PotionEffectType.HUNGER, 30, 1);
            case 3 -> new PotionEffect(PotionEffectType.FAST_DIGGING, 30, 1);
            case 4 -> new PotionEffect(PotionEffectType.SPEED, 30, 1);
            default -> new PotionEffect(PotionEffectType.REGENERATION, 30, 1);
        };
        Dominin.target.addPotionEffect(potion);
        sender.sendMessage("Added potion effect to target > " + potion.getType().getName());
        return false;
    }
}
