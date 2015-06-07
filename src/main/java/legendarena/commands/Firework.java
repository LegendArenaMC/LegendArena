package legendarena.commands;import legendarena.emeralds.EmeraldsCore;import legendarena.api.utils.*;import org.bukkit.command.Command;import org.bukkit.command.CommandExecutor;import org.bukkit.command.CommandSender;import org.bukkit.entity.Player;import java.util.HashMap;import java.util.UUID;/** * Firework command. * * @author ThePixelDev */public class Firework implements CommandExecutor {    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return true;        }        if(!Rank.isRanked(sender, Rank.MEMBERPLUS)) {            if(EmeraldsCore.getEmeralds((Player) sender) < 15) {                sender.sendMessage(ChatUtils.Messages.errorMsg + "You don't have enough emeralds to use this! (you require 15 emeralds to use this as a member)");                return true;            }            EmeraldsCore.removeEmeralds((Player) sender, 15, false);        }        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());            return true;        }        PluginUtils.shootFireworks(sender);        //3 second cooldown        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));        return true;    }}