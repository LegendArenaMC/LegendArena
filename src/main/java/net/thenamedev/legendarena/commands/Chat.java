package net.thenamedev.legendarena.commands;import net.thenamedev.legendarena.extras.menu.*;import org.bukkit.command.*;import org.bukkit.entity.*;/** * @author TheNameMan */public class Chat implements CommandExecutor {    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return true;        }        MenuInv.chatmenu.show((Player) sender);        return true;    }}