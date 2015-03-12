package net.thenamedev.legendarena.commands.staff;import net.thenamedev.legendapi.utils.ChatUtils;import net.thenamedev.legendapi.utils.Cooldown;import net.thenamedev.legendapi.utils.Rank;import org.bukkit.ChatColor;import org.bukkit.command.Command;import org.bukkit.command.CommandExecutor;import org.bukkit.command.CommandSender;import org.bukkit.entity.Player;/** * @author TheNameMan */public class ClearChat implements CommandExecutor {    public Cooldown c;    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return true; //Do nothing if it's not a player        }        String cast = "";        if(args.length != 0)            cast = ChatUtils.formatCast(args);        if(!Rank.getRank(sender, Rank.Mod)) {            Rank.noPermissions(sender, Rank.Mod);            return true;        }        if(c != null && !c.done()) {            sender.sendMessage(c.getTimeRemaining() + ChatColor.RED + " (This is a GLOBAL cooldown)");            return true;        }        if(args.length == 0) {            ChatUtils.clearChat(sender.getName(), null);        } else {            ChatUtils.clearChat(null, ChatColor.translateAlternateColorCodes('&', ChatColor.BLUE + Rank.getFormattedName((Player) sender) + " » " + ChatColor.BLUE + ChatColor.translateAlternateColorCodes('&', cast)));        }        c = new Cooldown(120);        return true;    }}