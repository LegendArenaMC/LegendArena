package legendarena.commands.backends;import legendarena.api.utils.ChatUtils;import legendarena.api.utils.Cooldown;import legendarena.api.utils.Rank;import org.bukkit.ChatColor;import org.bukkit.command.CommandSender;import org.bukkit.entity.Player;/** * Clear chat staff command. * * @author ThePixelDev */public class ClearChat {    private static Cooldown c;    public static void run(CommandSender sender, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return; //Do nothing if it's not a player        }        if(!Rank.isRanked(sender, Rank.MOD)) {            sender.sendMessage(Rank.noPermissions(Rank.MOD));            return;        }        if(c != null && !c.done()) {            sender.sendMessage(String.format("%s%s (This is a GLOBAL cooldown!!)", c.getTimeRemaining(), ChatColor.RED));            return;        }        ChatUtils.clearChat(sender.getName());        c = new Cooldown(120);    }}