package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.naofel.naofel.CustomPlayer;

import static listeners.CustomEvents.CustomMap;
public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args){
        if(cmd.getName().equalsIgnoreCase("break")){

            Player p = (Player) sender;
            CustomPlayer cp = CustomMap.get(p.getUniqueId());

            if(!cp.getCanBreakLog()){
                p.sendMessage(ChatColor.GREEN + "Vein Breaking has been turned on");
            } else {
                p.sendMessage(ChatColor.RED + "Vein Breaking has been turned off");
            }
            cp.setCanBreakLog();
        }
        return true;
    }
}
