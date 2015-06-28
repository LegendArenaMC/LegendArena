package legendarena.utils

import legendapi.message.Message
import legendapi.utils.ChatUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class StaffUtils {

    fun info(p: Player): String {
        var builtInfo: String;

        if(!p.isOnline())
            throw NullPointerException()

        val health = p.getHealth()


        builtInfo = Message().append("" + ChatColor.GREEN + "Health " + ChatColor.GRAY + ChatUtils.chars[1] + ChatColor.BLUE + " " + health + '\n')
                .append("")
                .toString()

        return builtInfo
    }

}