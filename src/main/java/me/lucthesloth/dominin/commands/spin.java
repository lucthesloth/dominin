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

public class spin implements CommandExecutor {
    private static Random rand = new Random();
    private BukkitTask task = null;
    private static Inventory inv;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (Dominin.target == null || !Dominin.target.isOnline()){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo target."));
            return false;
        }
        if (args.length > 0) {
            switch(args[0].toLowerCase()) {
                case "good":
                    _items = GoodIncentive.clone();
                    break;
                case "bad":
                    _items = BadIncentive.clone();
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid argument."));
                    return false;
            }
        }
        if (sender instanceof org.bukkit.entity.Player){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cIncentive!"));
            org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
            if (_items.length<= 0) {
                _items = GoodIncentive.clone();
            }
            resetInventory(_items);
            p.openInventory(inv);
            AtomicInteger max = new AtomicInteger(rand.nextInt(_items.length) + Math.min(_items.length*2, 10));
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
    private ItemStack chorus = createItem(new ItemStack(org.bukkit.Material.CHORUS_FRUIT), ChatColor.translateAlternateColorCodes('&', "&dChorus Fruit"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dTeleport Wilven to a random location!")});
    private ItemStack honeycomb = createItem(new ItemStack(org.bukkit.Material.HONEYCOMB), ChatColor.translateAlternateColorCodes('&', "&6Honeycomb"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawn 5 angry bees!")});
    private ItemStack carved = createItem(new ItemStack(org.bukkit.Material.CARVED_PUMPKIN), ChatColor.translateAlternateColorCodes('&', "&6Carved Pumpkin"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dForce Wilven to wear a carved pumpkin!")});
    private ItemStack cobweb = createItem(new ItemStack(org.bukkit.Material.COBWEB), ChatColor.translateAlternateColorCodes('&', "&fCobweb"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a cobweb at Wilven's location.")});
    private ItemStack chest = createItem(new ItemStack(org.bukkit.Material.CHEST), ChatColor.translateAlternateColorCodes('&', "&6Chest"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dDrops items randomly for 1 minute!")});
    private ItemStack gunpowder = createItem(new ItemStack(Material.GUNPOWDER), ChatColor.translateAlternateColorCodes('&', "&aCreeper Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPlays creeper sound for Wilven.")});
    private ItemStack creeperegg = createItem(new ItemStack(Material.CREEPER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aCreeper Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a creeper at Wilven's location.")});
    private ItemStack wardenegg = createItem(new ItemStack(Material.WARDEN_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aWarden Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a warden at Wilven's location.")});
    private ItemStack skeletonegg = createItem(new ItemStack(Material.SKELETON_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aSkeleton Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a skeleton at Wilven's location.")});
    private ItemStack fire = createItem(new ItemStack(org.bukkit.Material.FIRE_CHARGE), ChatColor.translateAlternateColorCodes('&', "&6Fire Charge"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSet Wilven on fire.")});
    private ItemStack zombieegg = createItem(new ItemStack(Material.ZOMBIE_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aZombie Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a zombie at Wilven's location.")});
    private ItemStack leatherboots = createItem(new ItemStack(org.bukkit.Material.LEATHER_BOOTS), ChatColor.translateAlternateColorCodes('&', "&6Leather Boots"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dForce Wilven to wear oven mittens IRL.")});
    private ItemStack spideregg = createItem(new ItemStack(Material.SPIDER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aSpider Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a spider at Wilven's location.")});
    private ItemStack villageregg = createItem(new ItemStack(Material.VILLAGER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aVillager Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a villager at Wilven's location.")});
    private ItemStack pigstep = createItem(new ItemStack(org.bukkit.Material.MUSIC_DISC_PIGSTEP), ChatColor.translateAlternateColorCodes('&', "&6Pigstep"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dP I G S T E P")});
    private ItemStack diamondore = createItem(new ItemStack(org.bukkit.Material.DIAMOND_ORE), ChatColor.translateAlternateColorCodes('&', "&bDiamond Ore"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dMakes next broken ore explode.")});
    private ItemStack badpotion = createItem(new ItemStack(Material.EXPERIENCE_BOTTLE), ChatColor.translateAlternateColorCodes('&', "&bPotion Effect"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBad potion effect for 30s.")});
    private ItemStack minecart = createItem(new ItemStack(org.bukkit.Material.MINECART), ChatColor.translateAlternateColorCodes('&', "&8Minecart"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFLYING JEBS.")});
    private ItemStack shield = createItem(new ItemStack(Material.SHIELD), ChatColor.translateAlternateColorCodes('&', "&8Shield"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSet Wilven to adventure mode for 15 seconds.")});

    private ItemStack goldenapple = createItem(new ItemStack(Material.GOLDEN_APPLE), ChatColor.translateAlternateColorCodes('&', "&6Golden Apple"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree golden apple")});
    private ItemStack diamondsword = createItem(new ItemStack(Material.DIAMOND_SWORD), ChatColor.translateAlternateColorCodes('&', "&bDiamond Sword"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree Sharpness V Diamond Sword")});
    private ItemStack resistance = createItem(new ItemStack(Material.IRON_CHESTPLATE), ChatColor.translateAlternateColorCodes('&', "&8Resistance"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPermanent Resistance effect.")});
    private ItemStack fireresistance = createItem(new ItemStack(Material.FIRE_CHARGE), ChatColor.translateAlternateColorCodes('&', "&6Fire Resistance"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPermanent Fire Resistance effect.")});
    private ItemStack map = createItem(new ItemStack(Material.MAP), ChatColor.translateAlternateColorCodes('&', "&8Map"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dShow nearest structure.")});
    private ItemStack bread = createItem(new ItemStack(Material.BREAD), ChatColor.translateAlternateColorCodes('&', "&6Bread"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBREAD.")});
    private ItemStack goldenhelmet = createItem(new ItemStack(Material.GOLDEN_HELMET), ChatColor.translateAlternateColorCodes('&', "&6Golden Helmet"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFree golden helmet.")});
    private ItemStack goodpotion = createItem(new ItemStack(Material.EXPERIENCE_BOTTLE), ChatColor.translateAlternateColorCodes('&', "&bPotion Effect"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dRegenration for 3 minutes.")});
    private ItemStack elytra = createItem(new ItemStack(Material.ELYTRA), ChatColor.translateAlternateColorCodes('&', "&8Elytra"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d Elytra for 30 seconds.")});
    private ItemStack totem = createItem(new ItemStack(Material.TOTEM_OF_UNDYING), ChatColor.translateAlternateColorCodes('&', "&8Totem of Undying"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dTemporary Heart Boost.")});
    private ItemStack diamonds = createItem(new ItemStack(Material.DIAMOND), ChatColor.translateAlternateColorCodes('&', "&bDiamonds"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d3 diamonds.")});
    private ItemStack goldencarrot = createItem(new ItemStack(Material.GOLDEN_CARROT), ChatColor.translateAlternateColorCodes('&', "&6Golden Carrot"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d5 GARROTS.")});
    private ItemStack enderchest = createItem(new ItemStack(Material.ENDER_CHEST), ChatColor.translateAlternateColorCodes('&', "&8Ender Chest"), new String[] {ChatColor.translateAlternateColorCodes('&', "&d1 Use of keep inventory.")});
    private ItemStack bunnyfeet = createItem(new ItemStack(Material.RABBIT_FOOT), ChatColor.translateAlternateColorCodes('&', "&8Bunny Feet"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpeed 10 for 10 seconds.")});

    private ItemStack filler = createItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), ChatColor.translateAlternateColorCodes('&', " "), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFiller.")});

    private ItemStack[] GoodIncentive = {goldenapple, diamondsword, resistance, fireresistance, map, bread, goldenhelmet, goodpotion, elytra, totem, diamonds, goldencarrot, enderchest, bunnyfeet};
    private ItemStack[] BadIncentive = {chorus, honeycomb, carved, cobweb, chest, gunpowder, diamondore, minecart, badpotion, shield, pigstep, villageregg,zombieegg, spideregg, skeletonegg, leatherboots, creeperegg, wardenegg, fire};
    private ItemStack[] _items = GoodIncentive.clone();

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
