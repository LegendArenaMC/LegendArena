package net.thenamedev.legendapi.core.emeralds;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

/**
 * A player's emeralds instance.
 *
 * @author ThePixelDev
 */
public class EmeraldPlayer {

    private Player p;
    private Economy econ;

    public EmeraldPlayer(Player p) {
        this.p = p;
        this.econ = EmeraldsCore.getEcon();
    }

    public double getEmeralds() {
        return Math.ceil(econ.getBalance(p));
    }

}
