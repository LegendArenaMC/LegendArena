package net.thenamedev.legendarena.commands.staff;import net.thenamedev.legendapi.utils.PluginUtils;import net.thenamedev.legendarena.commands.Help;import org.bukkit.command.Command;import org.bukkit.command.CommandExecutor;import org.bukkit.command.CommandSender;/** * @author TheNameMan */public class Chat implements CommandExecutor {    private static void run(CommandSender sender, String[] args) {        if(args.length == 0) {            sender.sendMessage(Help.getFormattedHeader("Chat System"));            sender.sendMessage(Help.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"));            sender.sendMessage(Help.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."));            sender.sendMessage(Help.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."));            return;        }        if(args[0].equalsIgnoreCase("off")) {            sender.sendMessage(PluginUtils.msgNormal + "Entered global chat.");        }        else {            sender.sendMessage(Help.getFormattedHeader("Chat System"));            sender.sendMessage(Help.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"));            sender.sendMessage(Help.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."));            sender.sendMessage(Help.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."));        }    }    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {        run(commandSender, strings);        return true;    }}