package net.thenamedev.legendarena.extras.customization;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by thepixeldev on 4/29/15.
 */
public class Customization {

    private static final HashMap<UUID, CustomArmour> customizationArmour = new HashMap<>();

    public static void set(final Player p, final ItemStack hat, final ItemStack shirt, final ItemStack pants, final ItemStack shoes) {
        customizationArmour.put(p.getUniqueId(), new CustomArmour() {
            public ItemStack hat() {
                return hat;
            }

            public ItemStack shirt() {
                return shirt;
            }

            public ItemStack pants() {
                return pants;
            }

            public ItemStack shoes() {
                return shoes;
            }
        });
    }

    public static void clear(Player p) {
        customizationArmour.remove(p.getUniqueId());
    }

    public static CustomArmour getPlayer(Player p) {
        return customizationArmour.get(p.getUniqueId());
    }



}
