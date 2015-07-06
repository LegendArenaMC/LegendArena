package legendarena.chat

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.ChatColor

public enum class Channel {
    ADMIN(Rank.ADMIN, "" + ChatColor.RED + "ADMIN" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + ChatUtils.chars[1] + ChatColor.RED + " {MESSAGE}"),
    ALERT(Rank.HELPER, "" + ChatColor.RED + "ALERT" + ChatColor.YELLOW + "({USERDISPLAY}" + ChatColor.YELLOW + ") " + ChatColor.GOLD + "{MESSAGE}"),
    STAFF(Rank.HELPER, "" + ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + ChatUtils.chars[1] + ChatColor.DARK_GREEN + " {MESSAGE}"),
    GLOBAL();

    private var rank: Rank
    private var format: String? = null

    private constructor(rank: Rank, format: String) {
        this.rank = rank
        this.format = format
    }

    private constructor() {
        rank = Rank.MEMBER
    }

    public fun getRank(): Rank {
        return rank
    }

    public fun getFormat(): String {
        return format!!
    }
}