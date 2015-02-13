package me.thenameman.legendarena.commands;

import me.thenameman.legendarena.utils.*;
import org.bukkit.command.*;

/**
 * @author TheNameMan
 */
public class Tutorial implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TutorialUtils.hub(sender, args);
        return true;
    }

}
