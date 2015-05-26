package net.thenamedev.legendapi.utils;import net.thenamedev.legendapi.LegendAPI;import net.thenamedev.legendarena.commands.staff.Troll;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.Color;import org.bukkit.FireworkEffect;import org.bukkit.command.CommandSender;import org.bukkit.entity.Firework;import org.bukkit.entity.Player;import org.bukkit.inventory.meta.FireworkMeta;import java.util.Random;/** * @author ThePixelDev */public class ChatUtils {    public static final Color[] colors = {            Color.YELLOW,            Color.WHITE,            Color.TEAL,            Color.SILVER,            Color.RED,            Color.PURPLE,            Color.ORANGE,            Color.OLIVE,            Color.NAVY,            Color.MAROON,            Color.LIME,            Color.GREEN,            Color.GRAY,            Color.FUCHSIA,            Color.BLUE,            Color.BLACK,            Color.AQUA    };    private static final ChatColor[] chatColors = {            ChatColor.AQUA,            ChatColor.RED,            ChatColor.BLUE,            ChatColor.DARK_AQUA,            ChatColor.YELLOW,            ChatColor.LIGHT_PURPLE,            ChatColor.DARK_GREEN,            ChatColor.DARK_PURPLE,            ChatColor.GOLD    };    public static String randomRainbow(String from) {        String to = "";        char[] list = from.toCharArray();        int c = 0;        for(char a : list) {            to = to + chatColors[c] + a;            if(c + 1 >= chatColors.length)                c = 0;            else                c++;        }        return to;    }    public static ChatColor getRandomColour() {        int max = chatColors.length;        return chatColors[new Random().nextInt(max)];    }    private static String getParsedChatMessage(String m, Player p) {        String msg = m;        if(Troll.sheepleTroll.contains(p.getUniqueId())) {            if(msg.endsWith("!") || msg.endsWith(".") || msg.endsWith("?"))                msg = msg + " Wake up, sheeple!";            else                msg = msg + "! Wake up, sheeple!";        }        if(Rank.isRanked(p, Rank.YOUTUBE))            return ChatColor.WHITE + msg.replaceAll("[tm]", "™");        else            return ChatColor.GRAY + msg;    }    public static String getParsedChatMessage(String msg, Rank r) {        if(r == Rank.YOUTUBE || r == Rank.HELPER || r == Rank.MOD || r == Rank.SRMOD || r == Rank.ADMIN || r == Rank.FOUNDER)            return ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg).replace("[tm]", "™");        else            return ChatColor.GRAY + msg;    }    public static String getFormattedChat(String msg, Player p) {        return getFormattedName(p) + " " + ChatColor.GRAY + PluginUtils.chars[1] + " " + getParsedChatMessage(msg, p);    }    public static String getFormattedChat(String msg, String p, Rank r) {        return getFormattedName(p, r) + " " + ChatColor.GRAY + PluginUtils.chars[1] + " " + getParsedChatMessage(msg, r);    }    private static String getFormattedName(String p, Rank r) {        return Rank.getRankPrefix(r) + p;    }    public static String getFormattedName(Player p) {        return Rank.getRankPrefix(Rank.getRank(p)) + ChatColor.RESET + " " + p.getName();    }    public static void clearChat(String clearer, String msg) {        if(clearer == null && msg == null)            throw new NullPointerException();        for(int i = 0; i < 120; i++)            broadcast(" ");        if(msg != null)            broadcast(msg);        else            broadcast(ChatColor.BLUE + "Chat was cleared by staff member " + ChatColor.YELLOW + clearer + ChatColor.BLUE + ".");    }    public static void broadcast(String m) {        for(Player pl : Bukkit.getOnlinePlayers())            pl.sendMessage(m);    }    public static void broadcast(String m, Rank r) {        if(r == null || m == null)            throw new NullPointerException("Message or rank cannot be null");        for(Player p : Bukkit.getOnlinePlayers())            if(Rank.isRanked(p, r))                p.sendMessage(m);    }    public static String formatCast(String[] args) {        if(args == null || args.length == 0)            throw new NullPointerException("Arguments list cannot be null, or empty");        String cast = "";        for(String s : args) {            if(s.equals("")) continue;            cast = cast + (cast.equals("") ? "" : " ") + s;        }        return cast;    }    public static String formatCast(String[] args, int... ignoreList) {        for(int i : ignoreList)            args[i] = "";        return formatCast(args);    }    public static void shootFireworks(CommandSender sender) {        if(!(sender instanceof Player))            return;        Random r = new Random();        Player p = (Player) sender;        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);        FireworkMeta fm = fw.getFireworkMeta();        fm.addEffect(FireworkEffect                .builder()                .flicker(r.nextBoolean())                .withColor(getColour(r.nextInt(17) + 1))                .withFade(getColour(r.nextInt(17) + 1))                .with(getFireworkType())                .trail(r.nextBoolean())                .build());        fm.setPower(r.nextInt(3) + 1);        fw.setFireworkMeta(fm);    }    private static FireworkEffect.Type getFireworkType() {        Random r = new Random();        FireworkEffect.Type type;        switch(r.nextInt(5) + 1) {            default:            case 1:                type = FireworkEffect.Type.BALL;                break;            case 2:                type = FireworkEffect.Type.STAR;                break;            case 3:                type = FireworkEffect.Type.BALL_LARGE;                break;            case 4:                type = FireworkEffect.Type.CREEPER;                break;            case 5:                type = FireworkEffect.Type.BURST;        }        return type;    }    private static Color getColour(int c) {        return colors[c - 1];    }    public static String getCustomMsg(String prefix) {        return Messages.getCustomMsg(prefix);    }    public static class Messages {        public static final String debugMsg = getCustomMsg("Debug");        public static final String warningMsg = getCustomMsg(ChatColor.YELLOW + "Warning");        public static final String errorMsg = getCustomMsg(ChatColor.RED + "Error");        public static String getCustomMsg(String prefix) {            return ChatColor.GREEN + prefix + " " + ChatColor.DARK_GRAY + PluginUtils.chars[1] + " " + ChatColor.BLUE;        }    }}