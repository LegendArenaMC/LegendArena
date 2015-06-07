package legendarena.api.utils;import org.bukkit.Color;import org.bukkit.FireworkEffect;import org.bukkit.command.CommandSender;import org.bukkit.entity.Firework;import org.bukkit.entity.Player;import org.bukkit.inventory.meta.FireworkMeta;import java.util.Random;/** * Various random plugin utilities that have no other home. * * @author ThePixelDev */public class PluginUtils {    @Deprecated    public static final char[] chars = ChatUtils.chars;    public static final Color[] colors = {            Color.YELLOW,            Color.WHITE,            Color.TEAL,            Color.SILVER,            Color.RED,            Color.PURPLE,            Color.ORANGE,            Color.OLIVE,            Color.NAVY,            Color.MAROON,            Color.LIME,            Color.GREEN,            Color.GRAY,            Color.FUCHSIA,            Color.BLUE,            Color.BLACK,            Color.AQUA    };    public static boolean random(int roll, int checkFor) {        return new Random().nextInt(roll) == checkFor;    }    public static void shootFireworks(CommandSender sender) {        if(!(sender instanceof Player))            return;        Random r = new Random();        Player p = (Player) sender;        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);        FireworkMeta fm = fw.getFireworkMeta();        fm.addEffect(FireworkEffect                .builder()                .flicker(r.nextBoolean())                .withColor(getColour(r.nextInt(17) + 1))                .withFade(getColour(r.nextInt(17) + 1))                .with(getFireworkType())                .trail(r.nextBoolean())                .build());        fm.setPower(r.nextInt(3) + 1);        fw.setFireworkMeta(fm);    }    private static FireworkEffect.Type getFireworkType() {        Random r = new Random();        FireworkEffect.Type type;        switch(r.nextInt(5) + 1) {            default:            case 1:                type = FireworkEffect.Type.BALL;                break;            case 2:                type = FireworkEffect.Type.STAR;                break;            case 3:                type = FireworkEffect.Type.BALL_LARGE;                break;            case 4:                type = FireworkEffect.Type.CREEPER;                break;            case 5:                type = FireworkEffect.Type.BURST;        }        return type;    }    private static Color getColour(int c) {        return colors[c - 1];    }}