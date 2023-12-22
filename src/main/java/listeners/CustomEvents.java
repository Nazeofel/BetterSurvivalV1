package listeners;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.naofel.naofel.CustomPlayer;

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

    public static boolean isLogBreakingAllowed = false;
    private final static Inventory inventory = Bukkit.createInventory(null, 54, "Enchantments");

    private final static Inventory test_inventory = Bukkit.createInventory(null, 54, "MyInventory");


    static  {
        inventory.setItem(2, E6);
        inventory.setItem(11, E7);
        inventory.setItem(4, S6);
        inventory.setItem(13, S7);
        inventory.setItem(22, S8);
        inventory.setItem(31, S9);
        inventory.setItem(40, S10);
        inventory.setItem(6, P6);
        inventory.setItem(15, P6);
        inventory.setItem(24, P7);
        inventory.setItem(33, P8);
        inventory.setItem(42, P9);
        inventory.setItem(51, P10);

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
        meta.setDisplayName(name);
        ((EnchantmentStorageMeta) meta).addStoredEnchant(ench, speed, true);
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
            case "Protection X":{
                item = P10;
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
                    switch(clicked.getItemMeta().getDisplayName()){
                        case "Protection V": {
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(45), 45, p, P5, EnchantmentsLevels.V);
                            break;
                        }
                        case "Efficiency VI":
                        case "Sharpness VI":
                        case "Protection VI":
                            p.sendMessage(String.valueOf(player_levels));
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(65), 65, p, getRightBook(clicked.getItemMeta().getDisplayName()), EnchantmentsLevels.VI);
                            break;
                        case "Efficiency VII":
                        case "Sharpness VII":
                        case "Protection VII":
                            p.sendMessage(String.valueOf(player_levels));
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(80), 80, p, getRightBook(clicked.getItemMeta().getDisplayName()), EnchantmentsLevels.VII);
                            break;
                        case "Sharpness VIII":
                        case "Protection VIII":
                            p.sendMessage(String.valueOf(player_levels));
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(100), 100, p, getRightBook(clicked.getItemMeta().getDisplayName()), EnchantmentsLevels.VIII);
                            break;
                        case "Sharpness IX":
                        case "Protection IX":
                            p.sendMessage(String.valueOf(player_levels));
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(125), 125, p, getRightBook(clicked.getItemMeta().getDisplayName()), EnchantmentsLevels.IX);
                            break;
                        case "Sharpness X":
                        case "Protection X":
                            p.sendMessage(String.valueOf(player_levels));
                            buyBook(playerExp, player_levels, "You successfully bought an "+ clicked.getItemMeta().getDisplayName() + " ", calculateExperience(0), 0, p, getRightBook(clicked.getItemMeta().getDisplayName()), EnchantmentsLevels.X);
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


            if(purchased_successful){
                if(player_levels != 0){
                    p.getInventory().addItem(item);
                    int rm_exp = (int) (player_exp - exp_needed);

                    p.setTotalExperience(Math.max(rm_exp, 0));
                    p.setLevel(Math.max(player_levels - level_needed, 0));
                    p.setExp(Math.max(player_exp - exp_needed, 0));
                }
                p.sendMessage(ChatColor.GREEN + success);
            }
        } else {
            p.sendMessage(ChatColor.RED + "You do not have enough experience to buy this book.");
        }
    }

    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent e){

        // If we ever make books with multiple enchantments
        // We will need to rework the forEach and turn it into a method

        ItemStack first = e.getInventory().getContents()[0];
        ItemStack second = e.getInventory().getContents()[1];

        EnchantmentStorageMeta x = (EnchantmentStorageMeta) second.getItemMeta();
        if(x == null) return;
        Map<Enchantment, Integer> enchantmentIntegerMap = x.getStoredEnchants();

        ItemStack result = e.getResult();
        if(result == null) return;
        ItemMeta result_meta = result.getItemMeta();

        // only check if name contains efficiency or whatever
        String item_name = second.getItemMeta().getDisplayName();

        if(item_name.contains("Efficiency")){
            enchantmentIntegerMap.forEach((k, v) -> {
                if(result_meta == null) return;
                result_meta.addEnchant(Enchantment.DIG_SPEED, v, true);

            });
        } else if(item_name.contains("Sharpness")){
            enchantmentIntegerMap.forEach((k, v) -> {
                if(result_meta == null) return;
                result_meta.addEnchant(Enchantment.DAMAGE_ALL, v, true);

            });
        } else if(item_name.contains("Protection")){
            enchantmentIntegerMap.forEach((k, v) -> {
                if(result_meta == null) return;
                result_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, v, true);

            });
        }

        result.setItemMeta(result_meta);

    }


    // for later use, also put a switch in it
    private void passEnchant(Map<Enchantment, Integer> enchMap, ItemMeta result_meta){

        // check if string "getDisplayName()" contains any of the enchantements..

        enchMap.forEach((k, v) -> result_meta.addEnchant(Enchantment.DIG_SPEED, v, true));
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
