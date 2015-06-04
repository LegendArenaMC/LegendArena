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

    public int amount() {
        return (int) Math.ceil(econ.getBalance(p)); //casting to int from a double. why.gif
    }

    public void take(int amount) {
        econ.withdrawPlayer(p, amount);
    }

    public void add(int amount) {
        econ.depositPlayer(p, amount);
    }

    public void reset() {
        econ.withdrawPlayer(p, amount());
    }

    public void set(int amount) {
        reset();
        add(amount);
    }

}
