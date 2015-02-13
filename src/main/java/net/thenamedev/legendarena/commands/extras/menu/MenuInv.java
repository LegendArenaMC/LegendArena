package net.thenamedev.legendarena.commands.extras.menu;

import net.thenamedev.legendarena.commands.extras.menu.staffmenu.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

/**
 * @author TheNameMan
 */
public class MenuInv implements Listener {

    private static final Plugin p = Bukkit.getPluginManager().getPlugin("LegendArena");
    public static final MainMenu menu = new MainMenu(p);
    public static final MinigameMenu minimenu = new MinigameMenu(p);
    public static final ChatMenu chatmenu = new ChatMenu(p);

}
