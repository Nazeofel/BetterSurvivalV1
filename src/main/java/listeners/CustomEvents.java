package listeners;

import Utils.Firework;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import org.bukkit.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.naofel.naofel.CustomPlayer;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

public class CustomEvents implements Listener {



    private enum EnchantmentsLevels {
        V,
        VI,
        VII,
        VIII,
        IX,
        X
    }
    public static Map<UUID, CustomPlayer> CustomMap = new HashMap<UUID, CustomPlayer>();
    private static Map<String, ItemStack> InventoryStack = new HashMap<>();


    // protection 6 to 10

    // sharpness 6 to  10

    // power 10

    private static final ItemStack E6 = createEnchantedBook("Efficiency VI", Enchantment.DIG_SPEED, 6);
    private static final ItemStack E7 = createEnchantedBook("Efficiency VII", Enchantment.DIG_SPEED, 7);
    private static final ItemStack E8 = createEnchantedBook("Efficiency VIII", Enchantment.DIG_SPEED, 8);
    private static final ItemStack E9 = createEnchantedBook("Efficiency IX", Enchantment.DIG_SPEED, 9);
    private static final ItemStack E10 = createEnchantedBook("Efficiency X", Enchantment.DIG_SPEED, 10);
    private static final ItemStack F5 = createEnchantedBook("Fortune V", Enchantment.LOOT_BONUS_BLOCKS, 5);
    private static final ItemStack F10 = createEnchantedBook("Fortune X", Enchantment.LOOT_BONUS_BLOCKS, 10);
    private static final ItemStack S6 = createEnchantedBook("Sharpness VI", Enchantment.DAMAGE_ALL, 6);
    private static final ItemStack S7 = createEnchantedBook("Sharpness VII", Enchantment.DAMAGE_ALL, 7);
    private static final ItemStack S8 = createEnchantedBook("Sharpness VIII", Enchantment.DAMAGE_ALL, 8);
    private static final ItemStack S9 = createEnchantedBook("Sharpness IX", Enchantment.DAMAGE_ALL, 9);
    private static final ItemStack S10 = createEnchantedBook("Sharpness X", Enchantment.DAMAGE_ALL, 10);

    private static final ItemStack P5 = createEnchantedBook("Protection V", Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    private static final ItemStack P6 = createEnchantedBook("Protection VI", Enchantment.PROTECTION_ENVIRONMENTAL, 6);
    private static final ItemStack P7 = createEnchantedBook("Protection VII", Enchantment.PROTECTION_ENVIRONMENTAL, 7);
    private static final ItemStack P8 = createEnchantedBook("Protection VIII", Enchantment.PROTECTION_ENVIRONMENTAL, 8);
    private static final ItemStack P9 = createEnchantedBook("Protection IX", Enchantment.PROTECTION_ENVIRONMENTAL, 9);
    private static final ItemStack P10 = createEnchantedBook("Protection X", Enchantment.PROTECTION_ENVIRONMENTAL, 10);
    private static final ItemStack PW10 = createEnchantedBook("Power X", Enchantment.ARROW_DAMAGE, 10);

    public static boolean isLogBreakingAllowed = false;
    private final static Inventory inventory = Bukkit.createInventory(null, 54, "Enchantments");

    private final static Inventory test_inventory = Bukkit.createInventory(null, 54, "MyInventory");


    static  {
        inventory.setItem(0, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(1, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(2, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(3, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(4, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(5, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(6, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(7, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(8, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(9, new ItemStack(Material.DIAMOND_PICKAXE));
        inventory.setItem(10, E6);
        inventory.setItem(11, E7);
        inventory.setItem(12, E8);
        inventory.setItem(13, E9);
        inventory.setItem(14, E10);
        inventory.setItem(15, F5);
        inventory.setItem(16, F10);
        inventory.setItem(17, new ItemStack(Material.DIAMOND_AXE));
        inventory.setItem(18, new ItemStack(Material.DIAMOND_SWORD));
        inventory.setItem(20, S6);
        inventory.setItem(21, S7);
        inventory.setItem(22, S8);
        inventory.setItem(23, S9);
        inventory.setItem(24, S10);
        inventory.setItem(27, new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.setItem(29, P5);
        inventory.setItem(30, P6);
        inventory.setItem(31, P7);
        inventory.setItem(32, P8);
        inventory.setItem(33, P9);
        inventory.setItem(34, P10);
        inventory.setItem(36, new ItemStack(Material.BOW));
        inventory.setItem(40, PW10);
        inventory.setItem(44, new ItemStack(Material.ARROW));
        inventory.setItem(45, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(46, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(47, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(48, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(49, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(50, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(51, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(52, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inventory.setItem(53, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));


    }


    // do not duplicate entries when deco reco

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e ) {
        CustomPlayer player = new CustomPlayer();
        Player p = e.getPlayer();

        CustomMap.putIfAbsent(p.getUniqueId(), player);

    }

    private static ItemStack createEnchantedBook(String name, Enchantment ench, int speed){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return null;
        meta.setDisplayName(ChatColor.RED + name);
        ((EnchantmentStorageMeta) meta).addStoredEnchant(ench, speed, true);
        ArrayList<String> lore = new ArrayList<String>();
        switch (speed) {
            case 5:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 45 Levels"));
                break;
            case 6:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 65 Levels"));
                break;
            case 7:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 80 Levels, 32 Fer , 32 Or and 10 Diamonds"));
                break;
            case 8:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 100 Levels, 64 Fer , 64 Or and 25 Diamonds"));
                break;
            case 9:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 125 Levels, 100 Fer , 100 Or and 64 Diamonds"));
                break;
            case 10:
                lore.add(ChatColor.translateAlternateColorCodes('&',"&eCost : 1 Nether Star, 1 Dragon Egg"));
                break;
            default:
                lore.add("");
                break;

        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    @EventHandler
    public void onPinged(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
            if (e.getMessage().equals(pl.getName())) {
                p.getWorld().playSound(pl.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2f, 1f);

            }
        }
    }



    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e){
        if (e.getView().getTitle().equalsIgnoreCase("MyInventory")) {
            // logic to retrive all items from map
        }
    }

    // check all inventory invents
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase("MyInventory")) {
            // logic to enter all items in map
        }
    }


    private int calculateExperience(int levels){
        int total_exp = 0;
        for(int i = 0; i < levels; ++i){
            if(i <= 16){
                total_exp += (int) ((Math.pow(i, 2) + 6) * i);
            }
            if(i >= 17 && i <= 31){
                total_exp += (int) ((2.5 * Math.pow(i, 2) - 40.5) * i) + 350;
            }
            if(i >= 32){
                total_exp += (int) ((4.5 * Math.pow(i, 2)  - 162.5) * i) + 2220;
            }
        }

        return total_exp;
    }


    private ItemStack getRightBook(String bookTitle){
        ItemStack item = null;

        switch (bookTitle){
            case "Efficiency VI":{
                item = E6;
                break;
            }
            case "Efficiency VII":{
                item = E7;
                break;
            }
            case "Efficiency VIII":{
                item = E8;
                break;
            }
            case "Efficiency IX":{
                item = E9;
                break;
            }
            case "Efficiency X":{
                item = E10;
                break;
            }
            case "Fortune V":{
                item = F5;
                break;
            }
            case "Fortune X":{
                item = F10;
                break;
            }
            case "Sharpness VI":{
                item = S6;
                break;
            }
            case "Sharpness VII":{
                item = S7;
                break;
            }
            case "Sharpness VIII":{
                item = S8;
                break;
            }
            case "Sharpness IX":{
                item = S9;
                break;
            }
            case "Sharpness X":{
                item = S10;
                break;
            }
            case "Protection V":{
                item = P5;
                break;
            }
            case "Protection VI":{
                item = P6;
                break;
            }
            case "Protection VII":{
                item = P7;
                break;
            }
            case "Protection VIII":{
                item = P8;
                break;
            }
            case "Protection IX":{
                item = P9;
                break;
            }
            case "Protection X": {
                item = P10;
                break;
            }
            case "Power X":{
                item = PW10;
                break;
            }
            default:
                break;
        }

        return item;
    }

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();

        float playerExp = p.getTotalExperience();
        int player_levels = p.getLevel();

        // might wanna refactor for further possibilities but prob no ngl...

        if(e.getView().getTitle().equalsIgnoreCase("Enchantments")){
            if(clicked == null) return;
            e.setCancelled(true);
            if(clicked.hasItemMeta()) {
                if(clicked.getItemMeta() != null && clicked.getItemMeta().hasDisplayName()){
                    String stripname = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
                    switch(stripname){
                        case "Protection V":
                        case "Fortune V":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(45), 45, p, getRightBook(stripname), EnchantmentsLevels.V);
                            break;

                        case "Efficiency VI":
                        case "Sharpness VI":
                        case "Protection VI":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(65), 65, p, getRightBook(stripname), EnchantmentsLevels.VI);
                            break;
                        case "Efficiency VII":
                        case "Sharpness VII":
                        case "Protection VII":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(80), 80, p, getRightBook(stripname), EnchantmentsLevels.VII);
                            break;
                        case "Efficiency VIII":
                        case "Sharpness VIII":
                        case "Protection VIII":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(100), 100, p, getRightBook(stripname), EnchantmentsLevels.VIII);
                            break;
                        case "Efficiency IX":
                        case "Sharpness IX":
                        case "Protection IX":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(125), 125, p, getRightBook(stripname), EnchantmentsLevels.IX);
                            break;
                        case "Efficiency X":
                        case "Sharpness X":
                        case "Protection X":
                        case "Power X":
                        case "Fortune X":
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(0), 0, p, getRightBook(stripname), EnchantmentsLevels.X);
                            break;
                        default:
                            System.out.println("nothinggg");
                            break;
                    }
                }
            }

        }

    }

    private boolean canPlayerBuy(PlayerInventory player_inventory, HashMap<Material, Integer> b){

        boolean isEverythingOk = false;
        HashMap<Material, Integer> op = new HashMap<>();
        HashMap<Integer, Integer> items_slots = new HashMap<>();

        ItemStack[] player_inventory_content = player_inventory.getContents();

        for(int i = 0; i < player_inventory_content.length; ++i){
            ItemStack item = player_inventory_content[i];
            if(player_inventory_content[i] != null) {
                int finalI = i;
                b.forEach((k, v) -> {
                    if(item.getType().equals(k)){
                        items_slots.put(finalI, item.getAmount());
                        if(op.containsKey(k)){
                            op.computeIfPresent(k, (key, value) -> value + item.getAmount() >= v ? v : value + item.getAmount());
                        } else {
                            op.put(k, v);
                        }
                    }
                });
            }
        }

        if(op.equals(b)){
            items_slots.forEach((k, v) -> {
                ItemStack item = player_inventory.getItem(k);
                if(item == null) return;
                Material item_mat = item.getType();
                int value_in_map = b.get(item_mat);


                if(value_in_map >= item.getAmount()){
                    b.computeIfPresent(item_mat, (key, value) -> value - item.getAmount());
                    player_inventory.removeItem(item);
                }

                if(value_in_map < item.getAmount()){
                    item.setAmount(item.getAmount() - value_in_map);
                }
            });
            isEverythingOk = true;
        }

        return isEverythingOk;
    }


    /*
     * Le 6 : 150 lvl
     * le 7 : 170 lvl 32 fer 32  or 10 diams
     * le 8 : 200 lvl 64 fer 64 or 25 diams
     * le 9 : 240 lvl 100 fer 100 or 64 diams
     * le 10 une etoile du nether et un oeuf
     * */


    private void buyBook(float player_exp, int player_levels, String success, float exp_needed, int level_needed, Player p, ItemStack item, EnchantmentsLevels el){
        HashMap<Material, Integer> local_map = new HashMap<>();
        if(player_levels >= level_needed){
            boolean purchased_successful = false;
            PlayerInventory player_inventory  = p.getInventory();

            switch(el){
                case V:
                    purchased_successful = true;
                    break;
                case VI:
                    purchased_successful = true;
                    break;
                case VII:
                    local_map.put(Material.IRON_INGOT, 32);
                    local_map.put(Material.GOLD_INGOT, 32);
                    local_map.put(Material.DIAMOND, 10);
                    purchased_successful = canPlayerBuy(player_inventory, local_map);
                    break;
                case VIII:
                    local_map.put(Material.IRON_INGOT, 64);
                    local_map.put(Material.GOLD_INGOT, 64);
                    local_map.put(Material.DIAMOND, 25);
                    purchased_successful = canPlayerBuy(player_inventory, local_map);
                    break;
                case IX:
                    local_map.put(Material.IRON_INGOT, 100);
                    local_map.put(Material.GOLD_INGOT, 100);
                    local_map.put(Material.DIAMOND, 64);
                    purchased_successful = canPlayerBuy(player_inventory, local_map);
                    break;
                case X:
                    local_map.put(Material.NETHER_STAR, 1);
                    local_map.put(Material.DRAGON_EGG, 1);
                    purchased_successful = canPlayerBuy(player_inventory, local_map);
                    break;
                default:
                    p.sendMessage(ChatColor.RED + " Oops something went wrong contact server adminastrators.");
                    break;
            }


            if(purchased_successful) {
                p.getInventory().addItem(item);

                if (level_needed != 0) {
                    int rm_exp = (int) (player_exp - exp_needed);
                    p.setTotalExperience(Math.max(rm_exp, 0));
                    p.setLevel(Math.max(player_levels - level_needed, 0));
                    p.setExp(Math.max(player_exp - exp_needed, 0));
                    new Firework(p, level_needed).launch();
                } else {
                    new Firework(p, 1000).launch();
                }

                p.sendMessage("");
                p.sendMessage(ChatColor.GREEN + success);
                p.sendMessage("");
                p.sendMessage(ChatColor.AQUA + "You still have " + ChatColor.GOLD + String.valueOf(player_levels - level_needed) + ChatColor.AQUA + " Level");
            }else {
                p.sendMessage(ChatColor.RED + "You do not have enough " + ChatColor.DARK_RED + "Material " +ChatColor.RED +"to buy this book.");
                }
        } else {
            p.sendMessage(ChatColor.RED + "You do not have enough " + ChatColor.DARK_RED + "Experience " +ChatColor.RED +"to buy this book.");
        }
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getClickedBlock() == null) return;

        if(e.getClickedBlock().getType().equals(Material.DRAGON_HEAD) && e.getAction() == Action.RIGHT_CLICK_BLOCK){
            e.setCancelled(true);
            p.openInventory(test_inventory);
        }

        if(e.getClickedBlock().getType().equals(Material.DRAGON_EGG) && e.getAction() == Action.RIGHT_CLICK_BLOCK){
            e.setCancelled(true);
            p.openInventory(inventory);
        }
    }


    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();

        boolean canPlayerBreak = CustomMap.get(p.getUniqueId()).getCanBreakLog();
        int leavesMinusZ = 1;
        int leavesPlusZ  = 1;

        int leavesMinusX = 1;
        int leavesPlusX  = 1;

        Location block  = e.getBlock().getLocation();
        World world  = e.getBlock().getWorld();

        //check if block has been generated or placed by a player
        String mat = world.getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).getType().toString();
        //world.getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).

        if(mat.contains("_LOG") && canPlayerBreak) {
            int y = 1;
            while(world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ()).getType().toString().equals(mat)){

              /*  while(world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ() + leavesPlusZ).getType().toString().contains("_LEAVES")){
                    world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ() + leavesPlusZ).breakNaturally();
                    leavesPlusZ++;
                }
                while(world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ() - leavesMinusZ).getType().toString().contains("_LEAVES")){
                    world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ() - leavesMinusZ).breakNaturally();
                    leavesMinusZ++;
                }
                while(world.getBlockAt(block.getBlockX()  + leavesPlusX, block.getBlockY() + y, block.getBlockZ()).getType().toString().contains("_LEAVES")){
                    world.getBlockAt(block.getBlockX()  + leavesPlusX, block.getBlockY() + y, block.getBlockZ()).breakNaturally();
                    leavesPlusX++;
                }
                while(world.getBlockAt(block.getBlockX() - leavesMinusX, block.getBlockY() + y, block.getBlockZ()).getType().toString().contains("_LEAVES")) {
                    world.getBlockAt(block.getBlockX() - leavesMinusX, block.getBlockY() + y, block.getBlockZ()).breakNaturally();
                    leavesMinusX++;
                }*/

                world.getBlockAt(block.getBlockX(), block.getBlockY() + y, block.getBlockZ()).breakNaturally();
                y++;
            }
        }
    }
}
