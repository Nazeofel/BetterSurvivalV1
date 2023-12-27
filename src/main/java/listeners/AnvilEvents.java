package listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EventListener;
import java.util.Map;

public class AnvilEvents implements  Listener {

    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent e){

        ItemStack main_item = e.getInventory().getContents()[0];
        ItemStack second_item = e.getInventory().getContents()[1];

        ItemStack result = e.getResult();
        if(result == null) return;

        if(e.getInventory().getRepairCost() > 40){
            e.getInventory().setRepairCost(39);
            e.getInventory().setRepairCostAmount(20);
        }

        if(main_item != null && second_item != null){

            EnchantmentStorageMeta second_item_enchantment_storage = (EnchantmentStorageMeta) second_item.getItemMeta();
            if(second_item_enchantment_storage == null) return;
            Map<Enchantment, Integer> enchantmentIntegerMap = second_item_enchantment_storage.getStoredEnchants();

            ItemStack item = recreateItem(enchantmentIntegerMap, main_item);

            e.setResult(item);

        }


    }

    private ItemStack passEnchant(Map<Enchantment, Integer> enchMap, ItemStack item){

        ItemMeta meta = item.getItemMeta();
        enchMap.forEach((k, v) -> {
            if(meta == null) return;
            String enchantment_name = String.valueOf(k.getKey());

            switch(enchantment_name){
                case "minecraft:efficiency": {
                    meta.addEnchant(Enchantment.DIG_SPEED, v, true);
                    break;
                }
                case "minecraft:power": {
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, v, true);
                    break;
                }
                case "minecraft:protection": {
                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, v, true);

                }
                case "minecraft:sharpness": {
                    meta.addEnchant(Enchantment.DAMAGE_ALL, v, true);
                }
                case "minecraft:knockback": {
                    meta.addEnchant(Enchantment.KNOCKBACK, v, true);
                }
                default:
                    break;
            }
        });


        item.setItemMeta(meta);

        return item;
    }

    private ItemStack recreateItem(Map<Enchantment, Integer> enchMap, ItemStack item){
        ItemStack new_item = new ItemStack(item.getType());
        new_item.setItemMeta(item.getItemMeta());

        return passEnchant(enchMap, new_item);
    }
}
