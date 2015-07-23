package legendarena.chat

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StringUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class ChatSystemUtils {

    /**
     * yip yip yip yip yip com-put-or com-put-or computor computor yip yip yip
     *
     * Little easter egg. Because why the fuck not. *it's not like I have anything better to do with my life anyways...*
     * ( ps: this is a reference to https://www.youtube.com/watch?v=-2ZkJd4u0Us )
     */
    private fun isYipYip(m: String): Boolean {
        return (StringUtils.toLower(m) as java.lang.String).contains("yip") && (StringUtils.toLower(m) as java.lang.String).contains("computer")
    }

    /**
     * Get a parsed chat message.
     * @param msg The message
     * @param p The player
     * @return The parsed message
     */
    private fun getParsedChatMessage(msg: String, p: Player): String {
        if(isYipYip(msg))
            return "" + (if (Rank.YOUTUBE.isRanked(p)) ChatColor.WHITE else ChatColor.GRAY) + "yip yip yip yip yip com-put-or com-put-or computor computor yip yip yip"

        if(Rank.YOUTUBE.isRanked(p))
            //ignore intellij's yelling at the use of "(msg as java.lang.String)" (blame Kotlin not having [String].replace etc)
            return "" + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', (msg as java.lang.String).replace("[tm]", "™"))
        else
            return "" + ChatColor.GRAY + msg
    }

    /**
     * Get a formatted name
     * @param p The player
     * @return The formatted name
     */
    public fun getFormattedName(p: Player): String {
        return RankUtils.getRankPrefix(RankUtils.getRank(p)) + ChatColor.RESET + (if (RankUtils.getRank(p) !== Rank.MEMBER) " " else "") + p.getName()
    }

    /**
     * Get a chat message
     * @param msg The message
     * @param p The player
     * @return The chat message
     */
    public fun getChatMessage(msg: String, p: Player): String {
        return getFormattedName(p) + " " + ChatColor.DARK_GRAY + ChatUtils.chars[1] + " " + getParsedChatMessage(msg, p)
    }

}