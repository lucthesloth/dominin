package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.jetbrains.annotations.NotNull;

public class ride implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline())
            sender.sendMessage("No target.");
        else {
            if (args.length == 1) {
                Entity t = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.BAT);
                Entity sheep = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.SHEEP);
                sheep.setCustomName("jeb_");
                t.addPassenger(sheep);
                for (int i = 0; i < 16; i++){
                    Entity temp = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.SHEEP);
                    temp.setCustomName("jeb_");
                    sheep.addPassenger(temp);
                    sheep = temp;
                }
                sheep.addPassenger(Dominin.target);
            } else
            {
                Entity frog = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.FROG);
                Entity bat = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.BAT);
                Entity llama = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.LLAMA);
                Entity bee = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.BEE);
                Entity chicken = Dominin.target.getWorld().spawnEntity(Dominin.target.getLocation(), EntityType.CHICKEN);
                bat.addPassenger(bee);
                bee.addPassenger(frog);
                frog.addPassenger(llama);
                llama.addPassenger(chicken);
                chicken.addPassenger(Dominin.target);
            }
            return false;
        }
        return true;
    }
}
