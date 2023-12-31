package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class carvedpumpkin implements CommandExecutor {
    private static BukkitTask task;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0)
            task.cancel();
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else if (task != null && !task.isCancelled()) {
            sender.sendMessage("Player is already wearing a pumpkin!");
            return false;
        }
        else {
            if (Dominin.target.getInventory().getHelmet() != null)
                Dominin.target.getWorld().dropItem(Dominin.target.getLocation(), Dominin.target.getInventory().getHelmet());
            ItemStack pumpkin = new ItemStack(org.bukkit.Material.CARVED_PUMPKIN);
            pumpkin.addEnchantment(org.bukkit.enchantments.Enchantment.BINDING_CURSE, 1);
            Dominin.target.getInventory().setHelmet(pumpkin);
            task = Bukkit.getScheduler().runTaskLater(Dominin.instance, () -> {
                if (Dominin.target != null && Dominin.target.isOnline()){
                    if (Dominin.target.getInventory().getHelmet().getType() == org.bukkit.Material.CARVED_PUMPKIN)
                        Dominin.target.getInventory().setHelmet(null);
                    task = null;
                    sender.sendMessage("Removed carved pumpkin from target.");
                }
            }, 20 * 30);
            sender.sendMessage("Gave carved pumpkin to target.");
            return false;
        }
        return true;
    }
}
