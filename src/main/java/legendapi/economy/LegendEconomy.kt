/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.economy

import legendapi.sql.LegendSQL
import legendapi.utils.ConfigUtils
import legendapi.utils.LegendAPIUtils
import legendapi.utils.StringUtils
import org.bukkit.Bukkit
import java.io.File
import java.sql.ResultSet
import java.sql.SQLException

/**
 * SQL is fun. /s
 */
public class LegendEconomy {

    internal var sql: LegendSQL? = null
    internal var db = "emeralds"
    internal var table = "LA_EMERALDS"

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
        //var config = ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"))
        /*if(StringUtils.toLower(config.get("emeralds.storage") as String) == Types.MYSQL.getTypeString()) {
            var host = config.get("emeralds.mysql.host") as String
            var user = config.get("emeralds.mysql.user") as String
            var pass = config.get("emeralds.mysql.password") as String
            db = config.get("emeralds.mysql.database") as String
            table = StringUtils.toUpper(config.get("emeralds.table") as String) //insert table flip joke here
            sql = LegendSQL(host, db, user, pass)
        } else {*/
            //assume we're using sqlite
            //var dbFile = File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath() + File.separator + (config.get("emeralds.sqlite.file") as String))
            var dbFile = File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath() + File.separator + "emeralds.db")
            sql = LegendSQL(dbFile)
        //}

        setup()
    }

    internal fun setup() {
        sql!!.initialise()
        if(!sql!!.doesTableExist(table))
            sql!!.standardQuery("CREATE TABLE " + table + " ( NAME varchar(255), EMERALDS int );")
    }

    public fun getEmeralds(p: String): Int {
        var rs = sql!!.sqlQuery("SELECT EMERALDS FROM " + table + " WHERE NAME=\"" + p + "\";")
        var amount = 0
        if(rs.next())
            amount = rs.getInt("emeralds")
        rs.close()
        return amount
    }

    public fun getEmeraldsRS(): ResultSet {
        return sql!!.sqlQuery("SELECT * FROM " + table + ";")
    }

    public fun addEmeralds(p: String, a: Int) {
        var currentEmeralds = getEmeralds(p)
        setEmeralds(p, currentEmeralds + a)
    }

    public fun takeEmeralds(p: String, a: Int) {
        var currentEmeralds = getEmeralds(p)
        if(currentEmeralds - a <= -1)
            throw Exception("A player's emeralds count cannot be less than 0")
        setEmeralds(p, currentEmeralds - a)
    }

    public fun setEmeralds(p: String, a: Int) {
        if(a <= -1)
            throw Exception("A player's emeralds count cannot be less than 0")
        sql!!.standardQuery("UPDATE OR ROLLBACK " + table + " set EMERALDS = " + a + " where NAME = \"" + p + "\";")
    }

}