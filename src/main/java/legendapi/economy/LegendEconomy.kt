/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.economy

import legendapi.log.BukLog
import legendapi.log.Level
import legendapi.sql.LegendSQL
import legendapi.utils.ConfigUtils
import legendapi.utils.StringUtils
import org.bukkit.Bukkit
import java.io.File
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

/**
 * SQL is fun. /s
 */
public class LegendEconomy {

    internal var sql: LegendSQL? = null
    internal var db = "emeralds"
    internal var p = Bukkit.getPluginManager().getPlugin("LegendArena")
    internal var table = "LA_EMERALDS"
    internal var log = BukLog(p)

    internal var cache = HashMap<UUID, Int>()

    private enum class Types {
        MYSQL("mysql"),
        SQLITE("sqlite");

        internal var type = ""

        public constructor(type: String) {
            this.type = type
        }

        public fun getTypeString(): String {
            return type
        }
    }

    public constructor() {
        var config = ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"))
        table = StringUtils.toUpper(config.get("emeralds.table") as String) //insert table flip joke here
        if(StringUtils.toLower(config.get("emeralds.storage") as String) == Types.MYSQL.getTypeString()) {
            //we're using mysql (recommended for an actual network setup, btw!), so setup host and database things
            var host = config.get("emeralds.mysql.host") as String
            var user = config.get("emeralds.mysql.user") as String
            var pass = config.get("emeralds.mysql.password") as String
            db = config.get("emeralds.mysql.database") as String
            sql = LegendSQL(host, db, user, pass)
        } else {
            //assume we're using sqlite, so setup the sqlite db file
            //by the way! sqlite is good for basic single servers (like survival servers and such) but for networks - PLEASE, PLEASE, PLEASEEE USE MYSQL!
            var dbFile = File(p.getDataFolder().getAbsolutePath() + File.separator + "emeralds.db")
            sql = LegendSQL(dbFile)
        }

        setup()
    }

    internal fun setup() {
        log.debug("Setting up database...")
        sql!!.initialise()
        if(!sql!!.doesTableExist(table))
            sql!!.standardQuery("CREATE TABLE " + table + " ( NAME varchar(255), EMERALDS int );")
    }

    public fun getEmeralds(p: String): Int {
        //if(Bukkit.getPlayer(p) != null)
            //if(cache.containsKey(Bukkit.getPlayer(p).getUniqueId())) return cache.get(Bukkit.getPlayer(p).getUniqueId())
        var rs: ResultSet?
        try {
            rs = sql!!.sqlQuery("SELECT EMERALDS FROM `" + table + "` WHERE NAME=\"" + p + "\";")
        } catch(ex: NullPointerException) {
            setup() //attempt to setup the SQL database as the connection may be broken for some reason
            return 0
        } catch(ex: SQLException) {
            log.log(Level.SEVERE, "ERROR! Attempting to get emeralds count for player " + p + " resulted in an SQLException!")
            return 0
        }
        var amount = 0
        if(rs!!.next())
            amount = rs.getInt("emeralds")
        if(Bukkit.getPlayer(p) != null)
            cache.put(Bukkit.getPlayer(p).getUniqueId(), amount)
        rs.close()
        return amount
    }

    public fun addEmeralds(p: String, a: Int) {
        var currentEmeralds = 0
        try {
            currentEmeralds = getEmeralds(p)
        } catch(ex: NullPointerException) {
            currentEmeralds = 0
        }
        setEmeralds(p, currentEmeralds + a)
    }

    public fun takeEmeralds(p: String, a: Int) {
        var currentEmeralds = 0
        try {
            currentEmeralds = getEmeralds(p)
        } catch(ex: NullPointerException) {
            currentEmeralds = 0
        }
        if(currentEmeralds - a <= -1)
            throw Exception("A player's emeralds count cannot be less than 0")
        setEmeralds(p, currentEmeralds - a)
    }

    public fun setEmeralds(p: String, a: Int) {
        if(a <= -1)
            throw Exception("A player's emeralds count cannot be less than 0")
        if(getEmeralds(p) == 0) {
            sql!!.standardQuery("INSERT INTO `" + table + "` (`NAME`, `EMERALDS`) VALUES (\"" + p + "\", " + a + "; COMMIT;")
            BukLog(Bukkit.getPluginManager().getPlugin("LegendArena")).log(Level.DEBUG, "insert")
        } else {
            sql!!.standardQuery("UPDATE OR ROLLBACK `" + table + "` SET EMERALDS = " + a + " WHERE NAME = " + p + "; COMMIT;")
            BukLog(Bukkit.getPluginManager().getPlugin("LegendArena")).log(Level.DEBUG, "update")
        }

        sql!!.closeConnection()

        if(Bukkit.getPlayer(p) != null)
            cache.put(Bukkit.getPlayer(p).getUniqueId(), a)
    }

}