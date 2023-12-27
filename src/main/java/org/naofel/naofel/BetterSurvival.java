package org.naofel.naofel;

import commands.Commands;
import listeners.AnvilEvents;
import listeners.CustomEvents;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BetterSurvival extends JavaPlugin implements Listener {


    FileConfiguration config = getConfig();


    @Override
    public void onEnable() {

        if(Objects.equals(config.getString("dragon_egg_location"), "")) {
            config.set("dragon_egg_location", "\n==: org.bukkit.Location\n" + "  world: world\n" + "  x: 0.0\n" + "  y: 0.0\n" + "  z: 0.0\n" + "  pitch: 0.0\n" + "  yaw: 0.0");
            saveConfig();
        }

        System.out.println("Plug-in is starting up");
        getServer().getPluginManager().registerEvents(new CustomEvents(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new AnvilEvents(), this);
        getCommand("break").setExecutor(new Commands());

    }

    @Override
    public void onDisable() {
        System.out.println("Plug-in is closing up");
    }



    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract_Lowest(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getClickedBlock() == null) return;

        Location loc = (Location) config.get("dragon_egg_location");
        if(loc != null) {
            if(loc.getX() == e.getClickedBlock().getLocation().getX() && loc.getY() == e.getClickedBlock().getLocation().getY() && loc.getZ() == e.getClickedBlock().getLocation().getZ() && e.getAction() == Action.LEFT_CLICK_BLOCK) {
                e.setCancelled(true);
            }
        }

        if(e.getClickedBlock().getType().equals(Material.DRAGON_EGG) && e.getAction() == Action.LEFT_CLICK_BLOCK && p.getInventory().getItemInMainHand().getType().equals(Material.DRAGON_HEAD)){
            config.set("dragon_egg_location", e.getClickedBlock().getLocation());
            saveConfig();
            e.setCancelled(true);

        }

    }

}