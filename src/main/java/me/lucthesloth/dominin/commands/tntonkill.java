package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.EventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class tntonkill implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (me.lucthesloth.dominin.Dominin.target == null && !me.lucthesloth.dominin.Dominin.target.isOnline()){
            sender.sendMessage("No target.");
            return false;
        }
        EventListener.primedtntonkill = true;
        sender.sendMessage(String.format("Next kill from %s will spawn primed tnt.", me.lucthesloth.dominin.Dominin.target.getName()));
        return false;
    }
}
