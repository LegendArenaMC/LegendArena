package net.thenamedev.legendapi.utils;import org.bukkit.*;import org.bukkit.command.*;import org.bukkit.entity.*;import org.bukkit.inventory.meta.*;import java.util.*;/** * @author TheNameMan */public class ChatUtils {    private static String getParsedChatMessage(String msg, Player p) {        if(msg.contains("%"))            return ChatColor.RED + "The percent sign is temporarily blocked - sorry! -Pixel (yes I will fix this soon" + PluginUtils.chars[6] + ")";        if(Rank.getRank(p, Rank.Helper)) {            String parsedMsg = ChatColor.translateAlternateColorCodes('&', msg);            parsedMsg = ChatColor.GREEN + parsedMsg;            return parsedMsg;        } else            return msg; // No changes, just return it as is    }    public static String getFormattedChat(String msg, Player p) {        return getFormattedName(p) + " " + ChatColor.GRAY + PluginUtils.chars[1] + " " + ChatColor.GRAY + getParsedChatMessage(msg, p);    }    public static String getFormattedName(Player p) {        String name = Rank.getFormattedName(p);        String prefix = Rank.getRankPrefix(Rank.getRank(p));        return prefix + ChatColor.DARK_GRAY + " | " + name;    }    public static void clearChat(String clearer, String msg) {        if(clearer == null && msg == null)            throw new NullPointerException();        for(int i = 0; i < 120; i++)            broadcast(" ");        if(msg != null)            broadcast(msg);        else            broadcast(ChatColor.BLUE + "Chat was cleared by staff member " + Rank.getFormattedName(Bukkit.getPlayerExact(clearer)) + ChatColor.GREEN + ".");    }    public static void broadcast(String message) {        if(message == null) throw new NullPointerException("Message cannot be null");        for(Player pl : Bukkit.getOnlinePlayers())            pl.sendMessage(message);    }    public static void broadcast(String m, Rank r) {        if(r == null || m == null)            throw new NullPointerException("Message or rank cannot be null");        for(Player p : Bukkit.getOnlinePlayers())            if(Rank.getRank(p, r))                p.sendMessage(m);    }    public static String playerJoin(String p) {        if(p == null)            throw new NullPointerException("Player cannot be null");        String joinMsg;        joinMsg = ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;        return joinMsg;    }    public static String playerQuit(String p) {        if(p == null)            throw new NullPointerException("Player cannot be null");        String joinMsg;        joinMsg = ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;        return joinMsg;    }    public static String formatCast(String[] args) {        if(args == null || args.length == 0)            throw new NullPointerException("Arguments list cannot be null, or empty");        String cast = "";        for(String s : args) {            if(s.equals("") && args.length == 1) break;            else if(s.equals("")) continue;            cast = cast + s + " ";        }        return cast;    }    public static void shootFireworks(CommandSender sender) {        if(!(sender instanceof Player))            return;        Player p = (Player) sender;        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);        FireworkMeta fm = fw.getFireworkMeta();        Random r = new Random();        FireworkEffect.Type type = getFireworkType();        int c1i = r.nextInt(17) + 1;        int c2i = r.nextInt(17) + 1;        Color c1 = getColour(c1i);        Color c2 = getColour(c2i);        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();        fm.addEffect(effect);        int power = r.nextInt(3) + 1;        fm.setPower(power);        fw.setFireworkMeta(fm);    }    private static FireworkEffect.Type getFireworkType() {        Random r = new Random();        FireworkEffect.Type type;        int fType = r.nextInt(5) + 1;        switch(fType) {            default:            case 1:                type = FireworkEffect.Type.BALL;                break;            case 2:                type = FireworkEffect.Type.STAR;                break;            case 3:                type = FireworkEffect.Type.BALL_LARGE;                break;            case 4:                type = FireworkEffect.Type.CREEPER;                break;            case 5:                type = FireworkEffect.Type.BURST;        }        return type;    }    private static Color getColour(int c) {        switch(c) {            case 1:                return Color.YELLOW;            case 2:                return Color.WHITE;            case 3:                return Color.TEAL;            case 4:                return Color.SILVER;            case 5:                return Color.RED;            case 6:                return Color.PURPLE;            case 7:                return Color.ORANGE;            case 8:                return Color.OLIVE;            case 9:                return Color.NAVY;            case 10:                return Color.MAROON;            case 11:                return Color.LIME;            case 12:                return Color.GREEN;            case 13:                return Color.GRAY;            case 14:                return Color.FUCHSIA;            case 15:                return Color.BLUE;            case 16:                return Color.BLACK;            case 17:                return Color.AQUA;        }        return null;    }    public static String formattedCmd(String name, boolean header) {        return header ? ChatColor.LIGHT_PURPLE + "-•- [" + ChatColor.LIGHT_PURPLE + ChatColor.stripColor(name) + ChatColor.LIGHT_PURPLE + "] -•-" : ChatColor.BLUE + "-•- " + ChatColor.stripColor(name) + ChatColor.BLUE + " -•-";    }}