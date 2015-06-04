package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.Cooldown;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.menu.ParticleMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Particle selector command... of magic. (this seems to be a recuring theme of me saying "[x] of [y]" in javadocs)
 *
 * @author ThePixelDev
 */
public class Particle implements CommandExecutor {

    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.MEMBERPLUS)) {
            sender.sendMessage(Rank.noPermissions(Rank.MEMBERPLUS));
            return true;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return true;
        }
        ParticleMenu.show((Player) sender);
        //2 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(2));
        return true;
    }

}
