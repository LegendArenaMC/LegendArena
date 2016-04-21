package legendarena.api.utils

import org.bukkit.ChatColor

class Cooldown {

    private val seconds: Double
    private val start: Long

    constructor(seconds: Double) {
        this.seconds = seconds
        this.start = System.currentTimeMillis()
    }

    fun done(): Boolean {
        if(java.lang.String.format("%.1f", seconds - ((System.currentTimeMillis() - start) / 1000.0)) == "0.0") return true //fucking idiotic cooldown system bugs
        return ((System.currentTimeMillis() - start) > seconds * 1000)
    }

    fun remainingSeconds(): String {
        return java.lang.String.format("%.1f", seconds - ((System.currentTimeMillis() - start) / 1000.0))
    }

    fun getTimeRemaining(): String {
        //return ChatUtils.getCustomMsg("Cooldown") + remainingSeconds() + ChatColor.GRAY + "/" + ChatColor.BLUE + seconds + "s"
        return ChatUtils.getFormattedMsg("Remaining cooldown", remainingSeconds() + ChatColor.GRAY + "/" + ChatColor.BLUE + seconds + "s")
    }

}