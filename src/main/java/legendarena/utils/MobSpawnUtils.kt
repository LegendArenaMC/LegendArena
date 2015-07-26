/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.utils

import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Creeper
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

public class MobSpawnUtils {

    public fun spawn(e: EntityType, l: Location): Entity? {
        return l.getWorld().spawnEntity(l, e)
    }

}