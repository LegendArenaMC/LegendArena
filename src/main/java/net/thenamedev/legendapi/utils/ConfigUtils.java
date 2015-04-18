package net.thenamedev.legendapi.utils;

import org.bukkit.Bukkit;

import java.util.List;

/**
 * Created on 4/15/2015
 *
 * @author ThePixelDev
 */
public class ConfigUtils {

    public enum Config {
        LIVESERVER("liveServer", Type.BOOLEAN),
        UPDATECHECK("updateCheck", Type.BOOLEAN),
        MOTDNOTICE("motdNotice", Type.STRING),
        ANNOUNCELIST("annnoticeList", Type.STRINGLIST),
        FOUNDERS("founders", Type.STRINGLIST);

        private String path;
        private Type type;

        Config(String path, Type type) {
            this.path = path;
        }

        public Type getType() {
            return type;
        }

        public String getPath() {
            return path;
        }
    }

    public enum Type {
        STRINGLIST,
        STRING,
        BOOLEAN,
        DOUBLE
    }

    public static void init() {
        Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().options().copyDefaults(true);
        save();
    }

    public static void save() {
        Bukkit.getPluginManager().getPlugin("LegendArena").saveConfig();
    }

    public static Object get(Config setting) {
        switch(setting.getType()) {
            case STRING:
                return Getters.getString(setting);
            case STRINGLIST:
                return Getters.getStringList(setting);
            case BOOLEAN:
                return Getters.getBoolean(setting);
            case DOUBLE:
                return Getters.getDouble(setting);

            default:
                return null;
        }
    }

    public static void set(Config setting, Object set) {
        switch(setting.getType()) {
            case STRING:
                Setters.setString(setting, (String) set);
            case STRINGLIST:
                //thank IntelliJ for the following comment... >.>
                //noinspection unchecked,ConstantConditions
                Setters.setStringList(setting, (List<String>) set);
            case BOOLEAN:
                //noinspection ConstantConditions
                Setters.setBoolean(setting, Boolean.parseBoolean((String) set));
            case DOUBLE:
                //noinspection ConstantConditions
                Setters.setDouble(setting, Double.parseDouble((String) set));
        }
    }

    private static class Getters {

        public static boolean getBoolean(Config setting) {
            return Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().getBoolean(setting.getPath());
        }

        public static String getString(Config setting) {
            return Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().getString(setting.getPath());
        }

        public static List<String> getStringList(Config setting) {
            return Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().getStringList(setting.getPath());
        }

        public static double getDouble(Config setting) {
            return Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().getDouble(setting.getPath());
        }

    }

    /**
     * Yes I realize half of this is just copy/paste/edit from the Getters class, but hey, it works, might as well keep it working... right?
     */
    private static class Setters {

        public static void setBoolean(Config setting, boolean set) {
            if(Getters.getBoolean(setting) == set)
                return;
            Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set(setting.getPath(), set);
        }

        public static void setString(Config setting, String set) {
            if(Getters.getString(setting).equals(set))
                return;
            Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set(setting.getPath(), set);
        }

        public static void setStringList(Config setting, List<String> set) {
            if(Getters.getStringList(setting).equals(set))
                return;
            Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set(setting.getPath(), set);
        }

        public static void setDouble(Config setting, double set) {
            if(Getters.getDouble(setting) == set)
                return;
            Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set(setting.getPath(), set);
        }

    }

}
