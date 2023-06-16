package me.lucthesloth.dominin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventListener implements org.bukkit.event.Listener{
    public static boolean explodeNextOre = false;
    @EventHandler
    public void onOreBreak(org.bukkit.event.block.BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        if (blockType == Material.DIAMOND_ORE || blockType == Material.EMERALD_ORE || blockType == Material.GOLD_ORE || blockType == Material.IRON_ORE || blockType == Material.LAPIS_ORE || blockType == Material.REDSTONE_ORE ||
        blockType == Material.DEEPSLATE_DIAMOND_ORE || blockType == Material.DEEPSLATE_EMERALD_ORE || blockType == Material.DEEPSLATE_GOLD_ORE || blockType == Material.DEEPSLATE_IRON_ORE || blockType == Material.DEEPSLATE_LAPIS_ORE || blockType == Material.DEEPSLATE_REDSTONE_ORE
        || blockType == Material.COAL_ORE || blockType == Material.DEEPSLATE_COAL_ORE || blockType == Material.NETHER_QUARTZ_ORE) {
            event.getBlock().getLocation().createExplosion(3f, false, false);
            explodeNextOre = false;
        }
    }
    @EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null) event.setCancelled(true);
    }
}
