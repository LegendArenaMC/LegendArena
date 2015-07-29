/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.punish

import org.bukkit.entity.Player

interface PunishCmd {
    public fun run(p: Player, staff: Player);
}