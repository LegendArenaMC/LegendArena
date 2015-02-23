package net.thenamedev.legendarena.extras.menu.core;

import net.thenamedev.legendarena.extras.menu.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

/**
 * @author TheNameMan
 */
public class MenuInv implements Listener {

    private static final Plugin p = Bukkit.getPluginManager().getPlugin("LegendArena");

    public static final Menu tokenMenu = new TokenMenu();

}
