package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.tokens.*;
import org.bukkit.command.*;

/**
 * @author TheNameMan
 */
public class Token implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TokenCore.init(); //make sure the token core is indeed initalized
        //
        return true;
    }

}
