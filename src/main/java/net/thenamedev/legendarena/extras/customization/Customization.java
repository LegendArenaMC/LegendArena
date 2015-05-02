package net.thenamedev.legendarena.extras.customization;

import net.thenamedev.legendapi.sql.SyncSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by thepixeldev on 4/29/15.
 */
public class Customization {

    private static final HashMap<UUID, CustomArmour> customizationArmour = new HashMap<>();
    //private static final SyncSQL sql = new SyncSQL(new File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath() + File.separator + "database.db"));

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

        p.getInventory().setArmorContents(new ItemStack[] { hat, shirt, pants, shoes });

        // SQL database magics

        /*int[] internalIds = parseInternalIds(hat, shirt, pants, shoes);

        try {
            sql.sqlQuery(String.format("CREATE DATABASE IF NOT EXISTS `la`; USE `la`; CREATE TABLE IF NOT EXISTS `la_custom`; INSERT OR ROLLBACK INTO la.la_custom `%s` HAT=\"%d\", SHIRT=\"%d\", PANTS=\"%d\", SHOES=\"%d\"", p.getName().toLowerCase(), internalIds[0], internalIds[1], internalIds[2], internalIds[3]));
        } catch(SQLException ex) {
            //do nothing [temporary catch]
        }*/
    }

    /*private static int[] parseInternalIds(final ItemStack hat, final ItemStack shirt, final ItemStack pants, final ItemStack shoes) {
        int[] internalIds = {0, 0, 0, 0};

        // HAT

        if(hat.getType() == Material.LEATHER_HELMET) {
            internalIds[0] = 1;
        } else if(hat.getType() == Material.IRON_HELMET) {
            internalIds[0] = 2;
        } else if(hat.getType() == Material.GOLD_HELMET) {
            internalIds[0] = 3;
        } else if(hat.getType() == Material.DIAMOND_HELMET) {
            internalIds[0] = 4;
        }

        // SHIRT

        if(hat.getType() == Material.LEATHER_CHESTPLATE) {
            internalIds[1] = 1;
        } else if(hat.getType() == Material.IRON_CHESTPLATE) {
            internalIds[1] = 2;
        } else if(hat.getType() == Material.GOLD_CHESTPLATE) {
            internalIds[1] = 3;
        } else if(hat.getType() == Material.DIAMOND_CHESTPLATE) {
            internalIds[1] = 4;
        }

        // PANTS

        if(hat.getType() == Material.LEATHER_LEGGINGS) {
            internalIds[2] = 1;
        } else if(hat.getType() == Material.IRON_LEGGINGS) {
            internalIds[2] = 2;
        } else if(hat.getType() == Material.GOLD_LEGGINGS) {
            internalIds[2] = 3;
        } else if(hat.getType() == Material.DIAMOND_LEGGINGS) {
            internalIds[2] = 4;
        }

        // SHOES

        if(hat.getType() == Material.LEATHER_BOOTS) {
            internalIds[3] = 1;
        } else if(hat.getType() == Material.IRON_BOOTS) {
            internalIds[3] = 2;
        } else if(hat.getType() == Material.GOLD_BOOTS) {
            internalIds[3] = 3;
        } else if(hat.getType() == Material.DIAMOND_BOOTS) {
            internalIds[3] = 4;
        }

        return internalIds;
    }*/

    public static void clear(Player p) {
        customizationArmour.remove(p.getUniqueId());
    }

    public static CustomArmour getPlayer(Player p) {
        return customizationArmour.get(p.getUniqueId());
    }



}
