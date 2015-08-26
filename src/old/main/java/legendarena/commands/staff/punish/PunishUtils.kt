/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff.punish

import org.bukkit.BanList
import org.bukkit.Bukkit
import java.util.*

public class PunishUtils {

    /**
     * Currently calls Bukkit.getBanList() as a temporary thing.
     *
     * I will eventually set this up to use SQL, but we all know how good I am at SQL syntax. (spoiler alert: I suck at it)
     */
    public fun isBanned(p: String): Boolean {
        return Bukkit.getBanList(BanList.Type.NAME).isBanned(p)
    }

    public fun ban(p: String, expiry: Date?, staff: String) {
        //Bukkit.getBanList(BanList.Type.NAME).addBan(p, )
    }

}