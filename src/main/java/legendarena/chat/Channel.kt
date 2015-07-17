package legendarena.chat

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.ChatColor
import org.bukkit.entity.Player

public interface ChannelTempNameIgnoreMe {

    //BASIC STUFF

    public fun getFormat(): String;

    public fun getRank(): Rank?;

    public fun shadowMutedChannel(): Boolean;

    //PLAYERS

    public fun addPlayer(p: Player);

    public fun removePlayer(p: Player);

    public fun isPlayerInChannel(p: Player): Boolean;

    public fun getPlayerList(): List<Player>;

    //PERMISSIONS

    public fun isBehindPermissionSet(): Boolean;

    public fun getJoinPermission(): String?;

    public fun getHearPermission(): String?;

}

Deprecated
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