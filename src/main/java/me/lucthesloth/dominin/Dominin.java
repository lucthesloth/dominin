package me.lucthesloth.dominin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Dominin extends JavaPlugin {
    public static Dominin instance;
    public static Player target;
    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(getCommand("target")).setExecutor(new me.lucthesloth.dominin.commands.target());
        Objects.requireNonNull(getCommand("cobwebs")).setExecutor(new me.lucthesloth.dominin.commands.cobwebs());
        Objects.requireNonNull(getCommand("explodeore")).setExecutor(new me.lucthesloth.dominin.commands.explodeOre());
        Objects.requireNonNull(getCommand("carvedpumpkin")).setExecutor(new me.lucthesloth.dominin.commands.carvedpumpkin());
        Objects.requireNonNull(getCommand("fakecreeper")).setExecutor(new me.lucthesloth.dominin.commands.fakecreeper());
        Objects.requireNonNull(getCommand("bees")).setExecutor(new me.lucthesloth.dominin.commands.bees());
        Objects.requireNonNull(getCommand("choruseffect")).setExecutor(new me.lucthesloth.dominin.commands.choruseffect());
        Objects.requireNonNull(getCommand("dropitems")).setExecutor(new me.lucthesloth.dominin.commands.dropitems());
        Objects.requireNonNull(getCommand("ride")).setExecutor(new me.lucthesloth.dominin.commands.ride());
        Objects.requireNonNull(getCommand("potion")).setExecutor(new me.lucthesloth.dominin.commands.potion());
        getServer().getPluginManager().registerEvents(new me.lucthesloth.dominin.EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
