package net.thenamedev.legendarena.extras.menu;

import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.jetbrains.annotations.*;

/**
 * Left for legacy purposes (and so I don't have to do a lot of monsterous code reworking).
 * @author TheNameMan
 */
public class MainMenu {

    public MainMenu(Plugin plugin) {}

    public void show(@NotNull Player p) {
        MenuInv.minimenu.show(p);
    }

}
