package legendarena.chat

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.ChatColor
import org.bukkit.entity.Player

public enum class Channel {

    ADMIN(Rank.ADMIN),
    DEV(Rank.DEV),
    ALERT(Rank.HELPER),
    STAFF(Rank.HELPER),
    GLOBAL();

    private var rank: Rank

    private constructor(rank: Rank) {
        this.rank = rank
    }

    private constructor() {
        rank = Rank.MEMBER
    }

    public fun getRank(): Rank {
        return rank
    }

}