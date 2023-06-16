package me.lucthesloth.dominin.commands;

import me.lucthesloth.dominin.Dominin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class goodwheel implements CommandExecutor {
    private static final Random rand = new Random();
    private BukkitTask task = null;
    private static Inventory inv;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo target."));
            return false;
        }
        if (args.length > 0 || _items.length == 0) {
            _items = GoodIncentive.clone();
            shuffle(_items);
        }
        if (sender instanceof org.bukkit.entity.Player p){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cIncentive!"));
            resetInventory(_items);
            p.openInventory(inv);
            AtomicInteger max = new AtomicInteger(rand.nextInt(_items.length) + Math.max(Math.min(_items.length*2, 16), 10));
            task = Bukkit.getScheduler().runTaskTimer(Dominin.instance, () -> {
                max.getAndDecrement();
                if (max.get() > 0) {
                    shiftItemsOne(_items);
                    resetInventory(_items);
                }
                if (max.get() < -13){
                    task.cancel();
                    task = null;
                    p.closeInventory();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cChosen Incentive:"));
                    p.sendMessage(inv.getItem(13).getItemMeta().lore().get(0));
                    _items = Arrays.stream(_items).filter(k -> !k.equals(inv.getItem(13))).toArray(ItemStack[]::new);
                    p.showTitle(net.kyori.adventure.title.Title.title(net.kyori.adventure.text.Component.text("Chosen Incentive"), inv.getItem(13).getItemMeta().lore().get(0)));

                }
            }, 0, 2);

            return false;
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player to use this command."));
        }

        return false;
    }
    private static ItemStack createItem(ItemStack item, String name, String[] lore) {
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(java.util.Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    private final ItemStack goldenapple = createItem(new ItemStack(Material.GOLDEN_APPLE), ChatColor.translateAlternateColorCodes('&', "&6Golden Apple"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree golden apple")});
    private final ItemStack diamondsword = createItem(new ItemStack(Material.DIAMOND_SWORD), ChatColor.translateAlternateColorCodes('&', "&bDiamond Sword"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree Sharpness V Diamond Sword")});
    private final ItemStack resistance = createItem(new ItemStack(Material.IRON_CHESTPLATE), ChatColor.translateAlternateColorCodes('&', "&8Resistance"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPermanent Resistance effect.")});
    private final ItemStack fireresistance = createItem(new ItemStack(Material.FIRE_CHARGE), ChatColor.translateAlternateColorCodes('&', "&6Fire Resistance"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPermanent Fire Resistance effect.")});
    private final ItemStack map = createItem(new ItemStack(Material.MAP), ChatColor.translateAlternateColorCodes('&', "&8Map"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dShow nearest structure.")});
    private final ItemStack bread = createItem(new ItemStack(Material.BREAD), ChatColor.translateAlternateColorCodes('&', "&6Bread"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBREAD.")});
    private final ItemStack goldenhelmet = createItem(new ItemStack(Material.GOLDEN_HELMET), ChatColor.translateAlternateColorCodes('&', "&6Golden Helmet"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree golden helmet.")});
    private final ItemStack goodpotion = createItem(new ItemStack(Material.EXPERIENCE_BOTTLE), ChatColor.translateAlternateColorCodes('&', "&bPotion Effect"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dRegenration for 3 minutes.")});
    private final ItemStack elytra = createItem(new ItemStack(Material.ELYTRA), ChatColor.translateAlternateColorCodes('&', "&8Elytra"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d Elytra for 30 seconds.")});
    private final ItemStack totem = createItem(new ItemStack(Material.TOTEM_OF_UNDYING), ChatColor.translateAlternateColorCodes('&', "&8Totem of Undying"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dTemporary Heart Boost.")});
    private final ItemStack diamonds = createItem(new ItemStack(Material.DIAMOND), ChatColor.translateAlternateColorCodes('&', "&bDiamonds"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d3 diamonds.")});
    private final ItemStack goldencarrot = createItem(new ItemStack(Material.GOLDEN_CARROT), ChatColor.translateAlternateColorCodes('&', "&6Golden Carrot"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d5 GARROTS.")});
    private final ItemStack enderchest = createItem(new ItemStack(Material.ENDER_CHEST), ChatColor.translateAlternateColorCodes('&', "&8Ender Chest"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d1 Use of keep inventory.")});
    private final ItemStack bunnyfeet = createItem(new ItemStack(Material.RABBIT_FOOT), ChatColor.translateAlternateColorCodes('&', "&8Bunny Feet"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpeed 10 for 10 seconds.")});

    private final ItemStack filler = createItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), ChatColor.translateAlternateColorCodes('&', " "), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFiller.")});

    private final ItemStack[] GoodIncentive = {goldenapple, diamondsword, resistance, fireresistance, map, bread, goldenhelmet, goodpotion, elytra, totem, diamonds, goldencarrot, enderchest, bunnyfeet};
    private ItemStack[] _items = {};

    private ItemStack[] shiftItemsOne(ItemStack[] c) {
        ItemStack first = c[0];
        for (int i = 0; i < c.length-1; i++){
            c[i] = c[i + 1];
        }
        c[c.length - 1] = first;
        return c;
    }
    private void resetInventory(ItemStack[] c) {
        inv.clear();
        for (int i = 0; i < 9; i++){
            inv.setItem(i, filler);
        }
        for (int i = 9; i < 18; i++){
            inv.setItem(i, c[c.length < 9 ? (i-9) %c.length : i-9]);
        }
        for (int i = 18; i < 27; i++){
            if (i == 22)
                inv.setItem(i, createItem(new ItemStack(Material.FIREWORK_ROCKET), "^", new String[]{}));
            else
                inv.setItem(i, filler);
        }
    }
    private void shuffle(ItemStack[] c){
        for (int i = 0; i < c.length; i++){
            int random = (int) (Math.random() * c.length);
            ItemStack temp = c[i];
            c[i] = c[random];
            c[random] = temp;
        }
    }
}
