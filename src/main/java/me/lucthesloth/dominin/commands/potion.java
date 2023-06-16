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
        if (args.length != 1) {
            sender.sendMessage("/potion <effect>");
            return false;
        }
        if (args[0].equalsIgnoreCase("bad")) {
            Dominin.target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30*20, 1));
            Dominin.target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30*20, 1));
        } else if (args[0].equalsIgnoreCase("regen")){
            Dominin.target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3*60*20, 1));
        } else {
            sender.sendMessage("Invalid effect.");
            return false;
        }
        return false;
    }
}
