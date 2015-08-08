package legendapi.message

import legendapi.log.BukLog
import legendapi.utils.Rank
import legendarena.LegendArena
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Message {

    private var type = MessageType.CHAT

    private var fadeIn = 5
    private var stay = 10
    private var fadeOut = 5

    private var sound: Sound? = null
    private var pitch = 1f

    private var builder: StringBuilder? = null

    internal var reflector = MessageReflector()

    public constructor() {}

    public constructor(type: MessageType) {
        this.type = type
    }

    public fun append(msg: String): Message {
        if(builder == null)
            builder = StringBuilder()
        builder!!.append(msg)
        return this
    }

    public fun setSound(s: Sound): Message {
        sound = s
        return this
    }

    public fun setPitch(p: Float): Message {
        pitch = p
        return this
    }

    public fun send(vararg p: Player) {
        var msg = toString()

        for(p1 in p) {
            if(sound != null)
                p1.playSound(p1.getLocation(), sound, pitch, pitch)
            if(msg != "")
                _send(p1)
        }
    }

    public fun send(p: CommandSender) {
        if(sound != null)
            (p as Player).playSound(p.getLocation(), sound, pitch, pitch)
        _send(p as Player)
    }

    Deprecated public fun send(s: Sound, vararg p: Player) {
        setSound(s)

        var msg = toString()

        for(p1 in p) {
            if(sound != null)
                p1.playSound(p1.getLocation(), sound, pitch, pitch)
            if(msg != "")
                _send(p1)
        }
    }

    Deprecated public fun send(s: Sound, pi: Float, vararg p: Player) {
        setPitch(pi)
        setSound(s)

        var msg = toString()

        for(p1 in p) {
            if(sound != null)
                p1.playSound(p1.getLocation(), sound, pitch, pitch)
            if(msg != "")
                _send(p1)
        }
    }

    internal fun _send(p: Player) {
        var msg = toString()
        if((msg == "" || msg == "\n") && sound != null) return
        when(type) {
            MessageType.CHAT -> p.sendMessage(msg)
            MessageType.ACTIONBAR -> reflector.sendActionbar(p, msg)
            MessageType.TITLE -> reflector.send(1, p, msg, fadeIn, stay, fadeOut)
            MessageType.SUBTITLE -> reflector.send(0, p, msg, fadeIn, stay, fadeOut)
        }
    }

    public fun broadcast() {
        for(p in Bukkit.getOnlinePlayers())
            send(p)
    }

    public fun broadcast(r: Rank) {
        for(p in Bukkit.getOnlinePlayers()) {
            if(!r.isRanked(p)) continue
            send(p)
        }
    }

    /**
     * Even I have no idea why this function exists.
     */
    public fun broadcast(vararg r: Rank) {
        for(p in Bukkit.getOnlinePlayers())
            for(b in r) {
                if(!b.isRanked(p)) continue
                send(p)
                break
            }
    }

    override public fun toString(): String {
        if(builder == null)
            return ""
        return builder.toString()
    }

}