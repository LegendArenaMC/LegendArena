package net.thenamedev.legendapi.utils;import net.thenamedev.legendapi.LegendAPI;import net.thenamedev.legendarena.extras.SwearFilter;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.Color;import org.bukkit.FireworkEffect;import org.bukkit.command.CommandSender;import org.bukkit.entity.Firework;import org.bukkit.entity.Player;import org.bukkit.inventory.meta.FireworkMeta;import java.util.Random;/** * @author ThePixelDev */public class ChatUtils {    private static final ChatColor[] chatColors = {            ChatColor.AQUA,            ChatColor.RED,            ChatColor.BLUE,            ChatColor.DARK_AQUA,            ChatColor.YELLOW,            ChatColor.LIGHT_PURPLE    };    public static String randomRainbow(String from) {        String to = "";        char[] list = from.toCharArray();        for(char a : list) {            Random r = new Random();            int b = r.nextInt(chatColors.length);            to = to + chatColors[b] + a;        }        return to;    }    private static String getParsedChatMessage(String msg, Player p) {        if(Rank.isRanked(p, Rank.VIP) && !Rank.isRanked(p, Rank.HELPER))            return ChatColor.WHITE + msg;        else if(Rank.isRanked(p, Rank.HELPER))            return ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', msg).replace("[tm]", "™");        else            return ChatColor.GRAY + SwearFilter.getFilteredMsg(msg);    }    public static String getParsedChatMessage(String msg, Rank r) {        if(r == Rank.VIP)            return ChatColor.WHITE + msg;        else if(r == Rank.HELPER || r == Rank.MOD || r == Rank.SRMOD || r == Rank.ADMIN || r == Rank.FOUNDER)            return ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', msg).replace("[tm]", "™");        else            return ChatColor.GRAY + SwearFilter.getFilteredMsg(msg);    }    public static String getFormattedChat(String msg, Player p) {        return getFormattedName(p) + " " + ChatColor.GRAY + PluginUtils.chars[1] + " " + getParsedChatMessage(msg, p);    }    public static String getFormattedChat(String msg, String p, Rank r) {        return getFormattedName(p, r) + " " + ChatColor.GRAY + PluginUtils.chars[1] + " " + getParsedChatMessage(msg, r);    }    private static String getFormattedName(String p, Rank r) {        String name = Rank.getFormattedName(p, r);        String prefix = Rank.getRankPrefix(r);        return prefix + ChatColor.DARK_GRAY + " | " + name;    }    public static String getFormattedName(Player p) {        String name = Rank.getFormattedName(p);        String prefix = Rank.getRankPrefix(Rank.getRank(p));        return prefix + ChatColor.DARK_GRAY + " | " + name;    }    public static void clearChat(String clearer, String msg) {        if(clearer == null && msg == null)            throw new NullPointerException();        for(int i = 0; i < 120; i++)            broadcast("");        if(msg != null)            broadcast(msg);        else            //noinspection deprecation            broadcast(ChatColor.BLUE + "Chat was cleared by staff member " + Rank.getFormattedName(Bukkit.getPlayer(clearer)) + ChatColor.GREEN + ".");    }    public static void broadcast(String m) {        if(m == null || m.equals("")) throw new NullPointerException("Message cannot be null or empty");        for(Player pl : Bukkit.getOnlinePlayers())            pl.sendMessage(m);        if(LegendAPI.debug)            Bukkit.getLogger().info(m);    }    public static void broadcast(String m, Rank r) {        if(r == null || m == null)            throw new NullPointerException("Message or rank cannot be null");        for(Player p : Bukkit.getOnlinePlayers())            if(Rank.isRanked(p, r))                p.sendMessage(m);        if(LegendAPI.debug)            Bukkit.getLogger().info(m);    }    public static String playerJoin(String p) {        if(p == null)            throw new NullPointerException("Player cannot be null");        String joinMsg;        joinMsg = ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;        return joinMsg;    }    public static String playerQuit(String p) {        if(p == null)            throw new NullPointerException("Player cannot be null");        String joinMsg;        joinMsg = ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;        return joinMsg;    }    public static String formatCast(String[] args) {        if(args == null || args.length == 0)            throw new NullPointerException("Arguments list cannot be null, or empty");        String cast = "";        for(String s : args) {            if(s.equals("")) continue;            cast = cast + (cast.equals("") ? "" : " ") + s; //GOD DAMNITI'M A FUCKING IDIOT        }        return cast;    }    public static String formatCast(String[] args, int... ignoreList) {        for(int i : ignoreList)            args[i] = "";        return formatCast(args);    }    public static void shootFireworks(CommandSender sender) {        if(!(sender instanceof Player))            return;        Random r = new Random();        Player p = (Player) sender;        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);        FireworkMeta fm = fw.getFireworkMeta();        fm.addEffect(FireworkEffect                .builder()                .flicker(r.nextBoolean())                .withColor(getColour(r.nextInt(17) + 1))                .withFade(getColour(r.nextInt(17) + 1))                .with(getFireworkType())                .trail(r.nextBoolean())                .build());        fm.setPower(r.nextInt(3) + 1);        fw.setFireworkMeta(fm);    }    private static FireworkEffect.Type getFireworkType() {        Random r = new Random();        FireworkEffect.Type type;        switch(r.nextInt(5) + 1) {            default:            case 1:                type = FireworkEffect.Type.BALL;                break;            case 2:                type = FireworkEffect.Type.STAR;                break;            case 3:                type = FireworkEffect.Type.BALL_LARGE;                break;            case 4:                type = FireworkEffect.Type.CREEPER;                break;            case 5:                type = FireworkEffect.Type.BURST;        }        return type;    }    private static Color getColour(int c) {        switch(c) {            case 1:                return Color.YELLOW;            case 2:                return Color.WHITE;            case 3:                return Color.TEAL;            case 4:                return Color.SILVER;            case 5:                return Color.RED;            case 6:                return Color.PURPLE;            case 7:                return Color.ORANGE;            case 8:                return Color.OLIVE;            case 9:                return Color.NAVY;            case 10:                return Color.MAROON;            case 11:                return Color.LIME;            case 12:                return Color.GREEN;            case 13:                return Color.GRAY;            case 14:                return Color.FUCHSIA;            case 15:                return Color.BLUE;            case 16:                return Color.BLACK;            case 17:                return Color.AQUA;        }        return null;    }    @Deprecated    public static String formattedCmd(String name, boolean header) {        return header ? ChatColor.LIGHT_PURPLE + "-•- [" + ChatColor.LIGHT_PURPLE + ChatColor.stripColor(name) + ChatColor.LIGHT_PURPLE + "] -•-" : ChatColor.BLUE + "-•- " + ChatColor.stripColor(name) + ChatColor.BLUE + " -•-";    }}