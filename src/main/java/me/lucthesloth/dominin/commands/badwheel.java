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

public class badwheel implements CommandExecutor {
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
            _items = BadIncentive.clone();
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
    private final ItemStack chorus = createItem(new ItemStack(org.bukkit.Material.CHORUS_FRUIT), ChatColor.translateAlternateColorCodes('&', "&dChorus Fruit"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dTeleport Wilven to a random location!")});
    private final ItemStack honeycomb = createItem(new ItemStack(org.bukkit.Material.HONEYCOMB), ChatColor.translateAlternateColorCodes('&', "&6Honeycomb"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawn 5 angry bees!")});
    private final ItemStack carved = createItem(new ItemStack(org.bukkit.Material.CARVED_PUMPKIN), ChatColor.translateAlternateColorCodes('&', "&6Carved Pumpkin"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dForce Wilven to wear a carved pumpkin!")});
    private final ItemStack cobweb = createItem(new ItemStack(org.bukkit.Material.COBWEB), ChatColor.translateAlternateColorCodes('&', "&fCobweb"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a cobweb at Wilven's location.")});
    private final ItemStack chest = createItem(new ItemStack(org.bukkit.Material.CHEST), ChatColor.translateAlternateColorCodes('&', "&6Chest"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dDrops items randomly for 1 minute!")});
    private final ItemStack gunpowder = createItem(new ItemStack(Material.GUNPOWDER), ChatColor.translateAlternateColorCodes('&', "&aCreeper Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPlays creeper sound for Wilven.")});
    private final ItemStack creeperegg = createItem(new ItemStack(Material.CREEPER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aCreeper Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a creeper at Wilven's location.")});
    private final ItemStack wardenegg = createItem(new ItemStack(Material.WARDEN_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aWarden Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a warden at Wilven's location.")});
    private final ItemStack skeletonegg = createItem(new ItemStack(Material.SKELETON_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aSkeleton Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a skeleton at Wilven's location.")});
    private final ItemStack fire = createItem(new ItemStack(org.bukkit.Material.FIRE_CHARGE), ChatColor.translateAlternateColorCodes('&', "&6Fire Charge"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSet Wilven on fire.")});
    private final ItemStack zombieegg = createItem(new ItemStack(Material.ZOMBIE_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aZombie Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a zombie at Wilven's location.")});
    private final ItemStack leatherboots = createItem(new ItemStack(org.bukkit.Material.LEATHER_BOOTS), ChatColor.translateAlternateColorCodes('&', "&6Leather Boots"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dForce Wilven to wear oven mittens IRL.")});
    private final ItemStack spideregg = createItem(new ItemStack(Material.SPIDER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aSpider Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a spider at Wilven's location.")});
    private final ItemStack villageregg = createItem(new ItemStack(Material.VILLAGER_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aVillager Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSpawns a villager at Wilven's location.")});
    private final ItemStack pigstep = createItem(new ItemStack(org.bukkit.Material.MUSIC_DISC_PIGSTEP), ChatColor.translateAlternateColorCodes('&', "&6Pigstep"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dP I G S T E P")});
    private final ItemStack diamondore = createItem(new ItemStack(org.bukkit.Material.DIAMOND_ORE), ChatColor.translateAlternateColorCodes('&', "&bDiamond Ore"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dMakes next broken ore explode.")});
    private final ItemStack badpotion = createItem(new ItemStack(Material.EXPERIENCE_BOTTLE), ChatColor.translateAlternateColorCodes('&', "&bPotion Effect"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBad potion effect for 30s.")});
    private final ItemStack minecart = createItem(new ItemStack(org.bukkit.Material.MINECART), ChatColor.translateAlternateColorCodes('&', "&8Minecart"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFLYING JEBS.")});
    private final ItemStack shield = createItem(new ItemStack(Material.SHIELD), ChatColor.translateAlternateColorCodes('&', "&8Shield"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dSet Wilven to adventure mode for 15 seconds.")});
    private final ItemStack noteblock1 = createItem(new ItemStack(Material.NOTE_BLOCK), ChatColor.translateAlternateColorCodes('&', "&8Note Block"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dChange texturepack! Pack #1")});
    private final ItemStack noteblock2 = createItem(new ItemStack(Material.NOTE_BLOCK), ChatColor.translateAlternateColorCodes('&', "&8Note Block"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dChange texturepack! Pack #2")});
    private final ItemStack noteblock3 = createItem(new ItemStack(Material.NOTE_BLOCK), ChatColor.translateAlternateColorCodes('&', "&8Note Block"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dChange texturepack! Pack #3")});
    private final ItemStack noteblock4 = createItem(new ItemStack(Material.NOTE_BLOCK), ChatColor.translateAlternateColorCodes('&', "&8Note Block"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dChange texturepack! Pack #4")});
    private final ItemStack noteblock5 = createItem(new ItemStack(Material.NOTE_BLOCK), ChatColor.translateAlternateColorCodes('&', "&8Note Block"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dChange texturepack! Pack #5")});
    private final ItemStack chickenegg = createItem(new ItemStack(Material.CHICKEN_SPAWN_EGG), ChatColor.translateAlternateColorCodes('&', "&aChicken Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dE G G S")});
    private final ItemStack fishingrod = createItem(new ItemStack(Material.FISHING_ROD), ChatColor.translateAlternateColorCodes('&', "&aFishing Rod"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dHEAVENLY CHASES WILVEN WITH A FISHING ROD.")});
    private final ItemStack musicdisc = createItem(new ItemStack(Material.MUSIC_DISC_11), ChatColor.translateAlternateColorCodes('&', "&aMusic Disc"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBOB Can only speak german for 3 minutes")});
    private final ItemStack wool = createItem(new ItemStack(Material.WHITE_WOOL), ChatColor.translateAlternateColorCodes('&', "&aWool"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBOB pretends that he is not able to hear Wilven for 30 seconds")});
    private final ItemStack redstonelamp = createItem(new ItemStack(Material.REDSTONE_LAMP), ChatColor.translateAlternateColorCodes('&', "&aRedstone Lamp"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dBOB needs to close Wilven's stream for 3 minutes")});
    //private final ItemStack enderdragonegg = createItem(new ItemStack(Material.DRAGON_EGG), ChatColor.translateAlternateColorCodes('&', "&aDragon Egg"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dPlay end credits")});
    private final ItemStack tnt = createItem(new ItemStack(Material.TNT), ChatColor.translateAlternateColorCodes('&', "&aTNT"), new String[] {ChatColor.translateAlternateColorCodes('&', "&dNext Entity kill will spawn primed tnt")});
    private final ItemStack filler = createItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), ChatColor.translateAlternateColorCodes('&', " "), new String[] {ChatColor.translateAlternateColorCodes('&', "&dFiller.")});


    private final ItemStack[] BadIncentive = {noteblock1,noteblock2,noteblock3,noteblock4,noteblock5,chorus, honeycomb, carved, cobweb, chest, gunpowder, diamondore, minecart, badpotion, shield, pigstep, villageregg,zombieegg, spideregg, skeletonegg, leatherboots, creeperegg, wardenegg, fire, chickenegg, fishingrod, musicdisc, wool, redstonelamp, tnt};
    private ItemStack[] _items = {};

    private void shiftItemsOne(ItemStack[] c) {
        ItemStack first = c[0];
        for (int i = 0; i < c.length-1; i++){
            c[i] = c[i + 1];
        }
        c[c.length - 1] = first;
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
