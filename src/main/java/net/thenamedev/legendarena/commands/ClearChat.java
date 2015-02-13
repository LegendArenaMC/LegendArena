package net.thenamedev.legendarena.commands;import net.thenamedev.legendarena.core.*;import net.thenamedev.legendarena.utils.*;import org.bukkit.*;import org.bukkit.command.*;import org.bukkit.entity.*;/** * @author TheNameMan */public class ClearChat implements CommandExecutor {    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return true; //Do nothing if it's not a player        }        String cast = ChatUtils.formatCast(args);        if(!Rank.getRank(sender, Rank.Mod)) {            Rank.noPermissions(sender, Rank.Mod);            return true;        }        if(args.length == 0) {            ChatUtils.clearChat(sender.getName(), null);        } else {            ChatUtils.clearChat(null, ChatColor.translateAlternateColorCodes('&', ChatColor.BLUE + Rank.getFormattedName((Player) sender) + " » " + ChatColor.BLUE + ChatColor.translateAlternateColorCodes('&', cast)));        }        return true;    }}