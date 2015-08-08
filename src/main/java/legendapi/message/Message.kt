package legendapi.message

import legendapi.utils.Rank
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player

class Message {

    private var type = MessageType.CHAT

    private var fadeIn = 5
    private var stay = 10
    private var fadeOut = 5

    private var sound : Sound? = null
    private var pitch = 1f

    private var builder : StringBuilder? = null

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

        if(msg != "")
            for(p1 in p) {
                if(sound != null) {
                    p1.playSound(p1.getLocation(), sound, pitch, pitch)
                }
                if(type == MessageType.CHAT) {
                    p1.sendMessage(msg)
                    continue
                }
                if(type == MessageType.TITLE) {
                    reflector.send(1, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.SUBTITLE) {
                    reflector.send(0, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.ACTIONBAR) {
                    reflector.sendActionbar(p1, msg)
                    continue
                }
            }
    }

    Deprecated public fun send(s: Sound, vararg p: Player) {
        setSound(s)

        //Terrible hack for temporary backwards compatibility

        var msg = toString()

        if(msg != "")
            for(p1 in p) {
                if(sound != null) {
                    p1.playSound(p1.getLocation(), sound, pitch, pitch)
                }
                if(type == MessageType.CHAT) {
                    p1.sendMessage(msg)
                    continue
                }
                if(type == MessageType.TITLE) {
                    reflector.send(1, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.SUBTITLE) {
                    reflector.send(0, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.ACTIONBAR) {
                    reflector.sendActionbar(p1, msg)
                    continue
                }
            }
    }

    Deprecated public fun send(s: Sound, pi: Float, vararg p: Player) {
        setPitch(pi)
        setSound(s)

        //Terrible hack for temporary backwards compatibility

        var msg = toString()

        if(msg != "")
            for(p1 in p) {
                if(sound != null) {
                    p1.playSound(p1.getLocation(), sound, pitch, pitch)
                }
                if(type == MessageType.CHAT) {
                    p1.sendMessage(msg)
                    continue
                }
                if(type == MessageType.TITLE) {
                    reflector.send(1, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.SUBTITLE) {
                    reflector.send(0, p1, msg, fadeIn, stay, fadeOut)
                    continue
                }
                if(type == MessageType.ACTIONBAR) {
                    reflector.sendActionbar(p1, msg)
                    continue
                }
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

    public fun broadcast(vararg r: Rank) {
        for(p in Bukkit.getOnlinePlayers())
            for(b in r) {
                if(!b.isRanked(p)) continue
                send(p)
                break
            }
    }

    Deprecated public fun broadcast(r: Rank, s: Sound) {
        for(p in Bukkit.getOnlinePlayers()) {
            if(!r.isRanked(p)) continue
            send(s, p)
        }
    }

    Deprecated public fun broadcast(s: Sound) {
        for(p in Bukkit.getOnlinePlayers())
            send(s, p)
    }

    override public fun toString(): String {
        if(builder == null)
            return ""
        return builder.toString()
    }

}