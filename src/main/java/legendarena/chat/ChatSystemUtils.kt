package legendarena.chat

import legendarena.api.fanciful.FancyMessage
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.api.utils.StringUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player

public class ChatSystemUtils {

    /**
     * Get a parsed chat message.
     * @param msg The message
     * @param p The player
     * @return The parsed message
     */
    private fun getParsedChatMessage(msg: String, p: Player): String {
        if(RankUtils.getDisplayRankId(p) >= 2)
            return "" + ChatColor.WHITE + parseColors(StringUtils.replace(msg, "[tm]", "™"), p)
        else
            return "" + ChatColor.GRAY + msg
    }

    /**
     * Get a formatted name
     * @param p The player
     * @return The formatted name
     */
    public fun getFormattedName(p: Player): String {
        return "" + RankUtils.getDisplayRank(p).getNameColor() + p.getName()
    }

    /**
     * Get a chat message
     * @param msg The message
     * @param p The player
     * @return The chat message
     */
    public fun getChatMessage(msg: String, p: Player): String {
        return getFormattedName(p) + " " + ChatColor.DARK_GRAY + ChatUtils.specialCharacters[1] + " " + getParsedChatMessage(msg, p)
    }

    public fun parseColors(msg: String, p: Player): String {
        if(RankUtils.getDisplayRankId(p) < 2)
            return msg
        if(StringUtils.contains(msg, "&rb&")) {
            var e = StringUtils.replace(msg, "&rb& ", "")
            if(e == msg) //in case they were smart enough to not put a space after "&rb&"
                e = StringUtils.replace(msg, "&rb&", "")

            return ChatUtils.formRainbow(e)
        }
        return ChatColor.translateAlternateColorCodes('&', msg) //we aren't doing rainbow text, call Bukkit's ChatColor.translateAlternateColorCodes()
    }

}