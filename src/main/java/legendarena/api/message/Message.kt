package legendarena.api.message

import legendarena.api.fanciful.FancyMessage
import legendarena.api.utils.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Message {

    private var type = MessageType.CHAT

    private var fanciful: FancyMessage? = null

    private var fadeIn = 5
    private var stay = 20
    private var fadeOut = 5

    private var sound: Sound? = null
    private var pitch1 = 1f
    private var pitch2 = 1f

    private var builder: StringBuilder? = null

    private var reflector = MessageReflector()

    constructor() {}

    constructor(type: MessageType) {
        this.type = type
    }

    fun append(msg: String): Message {
        if(type == MessageType.FANCIFUL)
            throw ClassCastException("You can't append a String onto a FancyMessage!")
        if(builder == null)
            builder = StringBuilder()
        builder!!.append(msg)
        return this
    }

    fun append(color: ChatColor): Message {
        if(type == MessageType.FANCIFUL)
            throw ClassCastException("You can't append a ChatColor onto a FancyMessage!")
        if(builder == null)
            builder = StringBuilder()
        builder!!.append("" + color)
        return this
    }

    fun append(msg: FancyMessage): Message {
        if(type != MessageType.FANCIFUL) {
            if(builder != null)
                builder = null
            type = MessageType.FANCIFUL
        }
        this.fanciful = msg
        return this
    }

    fun setSound(s: Sound): Message {
        sound = s
        return this
    }

    fun setPitch(p1: Float, p2: Float): Message {
        pitch1 = p1
        pitch2 = p2
        return this
    }

    fun send(vararg p: Player) {
        var msg = toString()

        for(p1 in p) {
            if(sound != null)
                p1.playSound(p1.location, sound, pitch1, pitch2)
            if(msg != "")
                _send(p1)
        }
    }

    fun send(p: CommandSender) {
        if(sound != null)
            (p as Player).playSound(p.location, sound, pitch1, pitch2)
        _send(p as Player)
    }

    private fun _send(p: Player) {
        var msg = toString()
        if((msg == "" || msg == "\n") && sound != null) return
        when(type) {
            MessageType.CHAT -> p.sendMessage(msg)
            MessageType.ACTIONBAR -> sendActionbar(p)
            MessageType.TITLE -> sendTitle(p)
            MessageType.SUBTITLE -> sendSubtitle(p)
            MessageType.FANCIFUL -> fanciful!!.send(p)
        }
    }

    fun broadcast() {
        for(p in Bukkit.getOnlinePlayers())
            send(p)
    }

    fun broadcast(r: Rank) {
        for(p in Bukkit.getOnlinePlayers()) {
            if(!r.isRanked(p)) continue
            send(p)
        }
    }

    /**
     * Even I have no idea why this function exists.
     */
    fun broadcast(vararg r: Rank) {
        for(p in Bukkit.getOnlinePlayers())
            for(b in r) {
                if(!b.isRanked(p)) continue
                send(p)
                break
            }
    }

    fun networkBroadcast() {
        throw UnsupportedOperationException("Soon[tm]")
    }

    override fun toString(): String {
        if(builder == null)
            return ""
        return builder.toString()
    }

    private fun sendTitle(p: Player) {
        reflector.send(0, p, toString(), fadeIn, stay, fadeOut)
    }

    private fun sendSubtitle(p: Player) {
        reflector.send(1, p, toString(), fadeIn, stay, fadeOut)
    }

    private fun sendActionbar(p: Player) {
        reflector.sendActionbar(p, toString())
    }

}